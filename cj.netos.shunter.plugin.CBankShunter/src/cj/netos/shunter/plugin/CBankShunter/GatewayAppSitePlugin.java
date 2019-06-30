package cj.netos.shunter.plugin.CBankShunter;

import cj.netos.inform.Informer;
import cj.netos.shunter.IInputShunterPlugin;
import cj.netos.shunter.Label;
import cj.netos.shunter.Message;
import cj.netos.shunter.ShunterGatewayAppSitePlugin;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;

@CjService(name = "$.studio.gateway.app.plugin", isExoteric = true)
public class GatewayAppSitePlugin extends ShunterGatewayAppSitePlugin {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@Override
	protected IInputShunterPlugin createInputShunterPlugin() {
		// TODO Auto-generated method stub
		return new IInputShunterPlugin() {

			@Override
			public void input(String labelFullName, Label label, Message message)
					throws CircuitException {
				System.out.println("....这是CBankShunter在处理流....." + labelFullName);
				//通过informer向后继节点发出通知，informer 的uri方法会自动添加到路由表
				
			}
		};
	}

}
