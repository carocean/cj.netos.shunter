package cj.netos.shunter;

import cj.studio.ecm.IChip;
import cj.studio.ecm.IChipInfo;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.socket.app.IGatewayAppSitePlugin;

/**
 * 分流器插件，分流器的插件必须实现该方法
 * 
 * @author caroceanjofers
 *
 */
public abstract class ShunterGatewayAppSitePlugin implements IGatewayAppSitePlugin {
	@CjServiceSite
	protected IServiceSite site;
	private IInputShunterPlugin __inputShunterPlugin;
	@Override
	public Object getService(String name) {
		if ("$.shunter.chipinfo".equals(name)) {
			ShunterPlugin plugin = new ShunterPlugin();
			IChip chip = (IChip) site.getService(IChip.class.getName());
			IChipInfo info = chip.info();
			plugin.setCompany(info.getCompany());
			plugin.setCopyright(info.getCopyright());
			plugin.setDescription(info.getDescription());
			plugin.setDeveloperHome(info.getDeveloperHome());
			plugin.setIconFileName(info.getIconFileName());
			plugin.setUuid(info.getId());
			plugin.setId(info.getName());// 将name作为插件id
			plugin.setProduct(info.getProduct());
			plugin.setVersion(info.getVersion());
			plugin.setResource(info.getResource());
			return plugin;
		}
		if("$.shunter.input".equals(name)) {
			if(__inputShunterPlugin!=null) {
				return __inputShunterPlugin;
			}
			IInputShunterPlugin input=createInputShunterPlugin();
			__inputShunterPlugin=input;
			return input;
		}
		return site.getService(name);
	}

	protected abstract IInputShunterPlugin createInputShunterPlugin();
}
