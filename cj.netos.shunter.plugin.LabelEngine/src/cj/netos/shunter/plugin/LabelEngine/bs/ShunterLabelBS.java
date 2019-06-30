package cj.netos.shunter.plugin.LabelEngine.bs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.DirectoryInfo;
import cj.lns.chip.sos.cube.framework.FileInfo;
import cj.lns.chip.sos.cube.framework.IReader;
import cj.lns.chip.sos.cube.framework.IWriter;
import cj.lns.chip.sos.cube.framework.OpenMode;
import cj.lns.chip.sos.cube.framework.TooLongException;
import cj.lns.chip.sos.cube.framework.lock.FileLockException;
import cj.netos.shunter.IShunterLabelBS;
import cj.netos.shunter.Label;
import cj.netos.shunter.ShunterOutputNode;
import cj.netos.shunter.plugin.LabelEngine.db.IDBStore;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "shunterLabelBS")
public class ShunterLabelBS implements IShunterLabelBS {
	@CjServiceRef
	IDBStore dbstore;

	@Override
	public void mkdirs(String path) {
		DirectoryInfo dir = dbstore.fs().dir(path);
		if (!dir.exists()) {
			dir.mkdir(null);
		}
	}

	@Override
	public boolean existsDir(String path) {
		DirectoryInfo dir = dbstore.fs().dir(path);
		return dir.exists();
	}

	@Override
	public boolean existsLabel(String fullName) throws CircuitException {
		return dbstore.fs().existsFile(fullName);
	}

	@Override
	public void deleteDir(String path) {
		DirectoryInfo dir = dbstore.fs().dir(path);
		if (dir.exists()) {
			dir.delete();
		}
	}

	@Override
	public List<String> listDir(String parent) {
		DirectoryInfo dir = dbstore.fs().dir(parent);
		List<DirectoryInfo> list = dir.listDirs();
		List<String> names = new ArrayList<String>();
		for (DirectoryInfo info : list) {
			names.add(String.format("%s", info.name()));
		}
		return names;
	}

	@Override
	public List<String> listLabelShortName(String parent) {
		DirectoryInfo dir = dbstore.fs().dir(parent);
		List<String> list = dir.listFileNames();
		return list;
	}

	@Override
	public String getLabelParent(String fullName) throws CircuitException {
		if (!dbstore.fs().existsFile(fullName)) {
			throw new CircuitException("404", "label不存在：" + fullName);
		}
		try {
			return dbstore.fs().openFile(fullName, OpenMode.onlyOpen).parent().path();
		} catch (FileNotFoundException | FileLockException e) {
			throw new CircuitException("500", e);
		}
	}

	@Override
	public void createLabel(String fullName) throws CircuitException {
		boolean exists = dbstore.fs().existsFile(fullName);
		if (!exists) {
			try {
				dbstore.fs().openFile(fullName, OpenMode.createNew);
			} catch (FileNotFoundException | FileLockException e) {
				throw new CircuitException("500", e);
			}
		}
	}
	@Override
	public void deleteLabel(String fullName) throws CircuitException {
		FileInfo fi;
		try {
			fi = dbstore.fs().openFile(fullName, OpenMode.onlyOpen);
			fi.delete();
		} catch (FileNotFoundException | FileLockException e) {
			throw new CircuitException("500", e);
		}
	}
	@Override
	public Label readLabel(String fullName) throws CircuitException {
		IReader reader = null;
		try {
			FileInfo fi = dbstore.fs().openFile(fullName, OpenMode.onlyOpen);
			reader = fi.reader(0);
			byte[] b = reader.readFully();
			reader.close();
			if(b.length<1) {
				return new Label();
			}
			return new Gson().fromJson(new String(b), Label.class);
		} catch (FileNotFoundException e) {
			throw new CircuitException("500", e);
		} catch (FileLockException e) {
			throw new CircuitException("500", e);
		} catch (TooLongException e) {
			throw new CircuitException("500", e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	@Override
	public void writeLabelProperty(String fullName, String key, String name) throws CircuitException {
		Label label = readLabel(fullName);
		if (label == null)
			return;
		if (label.getProps().containsKey(key)) {
			throw new CircuitException("500", "已存在key=" + key);
		}
		label.getProps().put(key, name);
		updateLabel(fullName, label);
	}

	@Override
	public void writeLabelOutputNode(String fullName, ShunterOutputNode outputNode) throws CircuitException {
		Label label = readLabel(fullName);
		if (label == null)
			return;
		for (ShunterOutputNode n : label.getNodes()) {
			if (n.getDestName().equals(outputNode.getDestName())) {
				throw new CircuitException("500", "已存在outputNode destName :" + n.getDestName());
			}
		}
		label.getNodes().add(outputNode);
		updateLabel(fullName, label);
	}

	@Override
	public void removeLabelOutputNode(String fullName, String destName) throws CircuitException {
		Label label = readLabel(fullName);
		if (label == null)
			return;
		for (int i = 0; i < label.getNodes().size(); i++) {
			ShunterOutputNode n = label.getNodes().get(i);
			if (n.getDestName().equals(destName)) {
				label.getNodes().remove(i);
			}
		}
		updateLabel(fullName, label);
	}

	@Override
	public void removeLabelProperty(String fullName, String key) throws CircuitException {
		Label label = readLabel(fullName);
		if (label == null)
			return;
		if (!label.getProps().containsKey(key)) {
			return;
		}
		label.getProps().remove(key);
		updateLabel(fullName, label);

	}

	private void updateLabel(String fullName, Label label) throws CircuitException {
		byte[] b = new Gson().toJson(label).getBytes();
		IWriter writer = null;
		try {
			FileInfo fi = dbstore.fs().openFile(fullName, OpenMode.onlyOpen);
			fi.setSpaceLength(0);
			writer = fi.writer(0);
			writer.write(b);
		} catch (FileNotFoundException | FileLockException e) {
			throw new CircuitException("500", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}


}
