package cj.netos.shunter.stub;

import java.util.List;

import cj.netos.shunter.Label;
import cj.netos.shunter.ShunterOutputNode;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/label.service", usage = "分流器标签管理")
public interface IShunterLabelStub {
	@CjStubMethod(usage = "创建目标")
	void mkdirs(@CjStubInParameter(key = "path", usage = "目录路径") String path);

	@CjStubMethod(usage = "目录是否存在")
	boolean existsDir(@CjStubInParameter(key = "path", usage = "目录路径") String path);

	@CjStubMethod(usage = "删除目录")
	void deleteDir(@CjStubInParameter(key = "path", usage = "目录路径") String path);

	@CjStubMethod(usage = "列出子目录")
	List<String> listDir(@CjStubInParameter(key = "parent", usage = "父路径") String parent);

	@CjStubMethod(usage = "获取指定目录下的标签短名")
	List<String> listLabelShortName(@CjStubInParameter(key = "parent", usage = "父路径") String parent);

	@CjStubMethod(usage = "标签文件是否存在")
	boolean existsLabel(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName)
			throws CircuitException;

	@CjStubMethod(usage = "创建标识。格式：/dir1/dir2/labelshortname.pluginid")
	void createLabel(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName) throws CircuitException;

	@CjStubMethod(usage = "读取标签")
	Label readLabel(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName) throws CircuitException;

	@CjStubMethod(usage = "写入标签属性")
	void writeLabelProperty(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInParameter(key = "value", usage = "值") String value) throws CircuitException;

	@CjStubMethod(usage = "写入标签的后继输出节点", command = "post")
	void writeLabelOutputNode(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName,
			@CjStubInContentKey(key = "outputNode", usage = "后继输出节点") ShunterOutputNode outputNode)
			throws CircuitException;

	@CjStubMethod(usage = "获取标签所在父目录")
	String getLabelParent(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName)
			throws CircuitException;

	@CjStubMethod(usage = "移除指定的标签后继节点")
	void removeLabelOutputNode(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName,
			@CjStubInParameter(key = "destName", usage = "输出节点的目标名") String destName) throws CircuitException;

	@CjStubMethod(usage = "移除指定的标签属性")
	void removeLabelProperty(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName,
			@CjStubInParameter(key = "key", usage = "键") String key) throws CircuitException;

	@CjStubMethod(usage = "删除标签")
	void deleteLabel(@CjStubInParameter(key = "fullName", usage = "Label全路径名") String fullName) throws CircuitException;
}
