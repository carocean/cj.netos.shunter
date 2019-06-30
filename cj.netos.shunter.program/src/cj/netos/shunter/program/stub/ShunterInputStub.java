package cj.netos.shunter.program.stub;

import cj.netos.shunter.Message;
import cj.netos.shunter.stub.IShunterInputStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;
@CjService(name="/input.service")
public class ShunterInputStub extends GatewayAppSiteRestStub implements IShunterInputStub {
	@CjServiceSite
	IServiceSite site;
	IReactor reactor;

	protected IReactor getReactor() {
		if (reactor == null) {
			reactor = (IReactor) site.getService("$.reactor");
		}
		return reactor;
	}
	@Override
	public void input(String labelFullName, Message message) throws CircuitException {
		IReactor reactor = getReactor();
		Event e = new Event(labelFullName, "shunter.input");
		e.getParameters().put("message", message);
		reactor.input(e);

	}

}
