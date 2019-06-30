package cj.netos.shunter.plugin.LabelEngine.db;

import cj.lns.chip.sos.cube.framework.FileSystem;
import cj.lns.chip.sos.cube.framework.ICube;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;

@CjService(name = "dbstore")
public class Dbstore implements IDBStore {
	@CjServiceSite
	IServiceSite site;
	@CjServiceRef(refByName = "mongodb.shunter.home")
	ICube home;
	FileSystem fs;

	@Override
	public synchronized ICube home() {
		return home;
	}

	@Override
	public synchronized FileSystem fs() {
		if (fs != null)
			return fs;
		fs = home.fileSystem();
		return fs;
	}
}
