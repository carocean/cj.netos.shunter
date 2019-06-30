package cj.netos.shunter;

import java.util.List;

import cj.studio.ecm.net.CircuitException;

public interface IShunterLabelBS {

	public void mkdirs(String path);

	public boolean existsDir(String path);

	public boolean existsLabel(String path) throws CircuitException;

	void deleteDir(String path);

	List<String> listDir(String parent);

	List<String> listLabelShortName(String parent);

	void createLabel(String fullName) throws CircuitException;

	Label readLabel(String fullName) throws CircuitException;

	void writeLabelProperty(String fullName, String key, String name) throws CircuitException;

	void writeLabelOutputNode(String fullName, ShunterOutputNode outputNode) throws CircuitException;

	String getLabelParent(String fullName) throws CircuitException;

	public void removeLabelOutputNode(String fullName, String destName)throws CircuitException;

	public void removeLabelProperty(String fullName, String key)throws CircuitException;

	void deleteLabel(String fullName) throws CircuitException;

}
