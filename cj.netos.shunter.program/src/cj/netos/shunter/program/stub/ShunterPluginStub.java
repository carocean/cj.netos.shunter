package cj.netos.shunter.program.stub;

import java.util.ArrayList;
import java.util.List;

import cj.netos.shunter.ShunterPlugin;
import cj.netos.shunter.stub.IShunterPluginStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
@CjService(name="/plugin.service")
public class ShunterPluginStub extends GatewayAppSiteRestStub implements IShunterPluginStub {
	@CjServiceSite
	IServiceSite site;
	@Override
	public ShunterPlugin getPlugin(String id) {
		return (ShunterPlugin) site.getService(String.format("%s.$.shunter.chipinfo",id));
	}

	@Override
	public String[] enumPluginid() {
		List<String> list=new ArrayList<String>();
		String[] ids= (String[])site.getService("$.gateway.app.pluginid");
		for(String id:ids) {
			ShunterPlugin p=getPlugin(id);
			if(p==null)continue;
			list.add(id);
		}
		return list.toArray(new String[0]);
	}

	@Override
	public boolean existsPluginid(String id) {
		String[] ids=enumPluginid();
		for(String a:ids) {
			if(a.equals(id)) {
				return true;
			}
		}
		return false;
	}

}
