package cj.netos.shunter.program.reactor.valve;

import cj.netos.shunter.IInputShunterPlugin;
import cj.netos.shunter.IShunterLabelBS;
import cj.netos.shunter.Label;
import cj.netos.shunter.Message;
import cj.netos.shunter.util.LabelPathUtil;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "shunter.input")
public class ShunterInputValve implements IValve {
	@CjServiceSite
	IServiceSite site;
	@CjServiceRef(refByName = "LabelEngine.shunterLabelBS")
	IShunterLabelBS shunterLabelBS;
	
	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String labelFullName=e.getKey();
		Message message=(Message)e.getParameters().get("message");
		String pluginid=LabelPathUtil.getExtName(labelFullName);
		IInputShunterPlugin input=(IInputShunterPlugin)site.getService(String.format("%s.$.shunter.input",pluginid));
		Label label=shunterLabelBS.readLabel(labelFullName);
		input.input(labelFullName,label, message);
	}

}
