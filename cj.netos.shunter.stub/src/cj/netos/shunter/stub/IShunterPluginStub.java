package cj.netos.shunter.stub;

import cj.netos.shunter.ShunterPlugin;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/plugin.service", usage = "分流器插件信息查询")
public interface IShunterPluginStub {
	@CjStubMethod(usage = "枚举所有插件的id")
	String[] enumPluginid();

	@CjStubMethod(usage = "获取分流器物理插件信息")
	ShunterPlugin getPlugin(@CjStubInParameter(key = "id", usage = "标识，即对应插件的assemblyTitle") String id);

	@CjStubMethod(usage = "分流器是否存在该插件")
	boolean existsPluginid(@CjStubInParameter(key = "id", usage = "标识，即对应插件的assemblyTitle") String id);
}
