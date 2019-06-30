package cj.netos.shunter.plugin.LabelEngine.db;

import cj.lns.chip.sos.cube.framework.FileSystem;
import cj.lns.chip.sos.cube.framework.ICube;

public interface IDBStore {

	ICube home();
	FileSystem fs();
}