package cj.netos.shunter.program.stub;

import java.util.List;

import cj.netos.shunter.IShunterLabelBS;
import cj.netos.shunter.Label;
import cj.netos.shunter.ShunterOutputNode;
import cj.netos.shunter.stub.IShunterLabelStub;
import cj.netos.shunter.stub.IShunterPluginStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.ultimate.util.StringUtil;

@CjService(name = "/label.service")
public class ShunterLabelStub extends GatewayAppSiteRestStub implements IShunterLabelStub {
	@CjServiceRef(refByName = "LabelEngine.shunterLabelBS")
	IShunterLabelBS shunterLabelBS;
	@CjServiceRef(refByName = "/plugin.service")
	IShunterPluginStub pluginStub;

	@Override
	public void mkdirs(String path) {
		shunterLabelBS.mkdirs(path);
	}

	@Override
	public boolean existsDir(String path) {
		return shunterLabelBS.existsDir(path);
	}

	@Override
	public boolean existsLabel(String fullName) throws CircuitException {
		checkLabelName(fullName);
		return shunterLabelBS.existsLabel(fullName);
	}

	@Override
	public void deleteDir(String path) {
		shunterLabelBS.deleteDir(path);
	}

	@Override
	public List<String> listDir(String parent) {
		return shunterLabelBS.listDir(parent);
	}

	@Override
	public List<String> listLabelShortName(String parent) {
		return shunterLabelBS.listLabelShortName(parent);
	}

	@Override
	public void createLabel(String fullName) throws CircuitException {
		checkLabelName(fullName);
		shunterLabelBS.createLabel(fullName);
	}

	@Override
	public Label readLabel(String fullName) throws CircuitException {
		checkLabelName(fullName);
		return shunterLabelBS.readLabel(fullName);
	}

	@Override
	public void writeLabelProperty(String fullName, String key, String name) throws CircuitException {
		shunterLabelBS.writeLabelProperty(fullName, key, name);
	}

	@Override
	public void writeLabelOutputNode(String fullName, ShunterOutputNode outputNode) throws CircuitException {
		checkLabelName(fullName);
		shunterLabelBS.writeLabelOutputNode(fullName, outputNode);
	}

	@Override
	public String getLabelParent(String fullName) throws CircuitException {
		checkLabelName(fullName);
		return shunterLabelBS.getLabelParent(fullName);
	}

	@Override
	public void removeLabelOutputNode(String fullName, String destName) throws CircuitException {
		checkLabelName(fullName);
		shunterLabelBS.removeLabelOutputNode(fullName, destName);
	}

	@Override
	public void removeLabelProperty(String fullName, String key) throws CircuitException {
		checkLabelName(fullName);
		shunterLabelBS.removeLabelProperty(fullName, key);
	}
	@Override
	public void deleteLabel(String fullName) throws CircuitException {
		checkLabelName(fullName);
		shunterLabelBS.deleteLabel(fullName);
	}
	private void checkLabelName(String fullName) throws CircuitException {
		if (StringUtil.isEmpty(fullName)) {
			throw new CircuitException("404", "参数为空");
		}
		int pos = fullName.lastIndexOf("/");
		String shortName = fullName.substring(pos + 1, fullName.length());
		if (StringUtil.isEmpty(shortName)) {
			throw new CircuitException("404", "缺少标签名:" + shortName);
		}
		pos = shortName.lastIndexOf(".");
		if (pos < 2) {
			throw new CircuitException("404", "缺少扩展名:" + fullName);
		}
		String pluginid = shortName.substring(pos + 1, shortName.length());
		if (!pluginStub.existsPluginid(pluginid)) {
			throw new CircuitException("404", String.format("不存在扩展名为:%s 的插件", pluginid));
		}
	}
}
