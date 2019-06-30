package cj.netos.shunter;

import cj.studio.ecm.net.CircuitException;

/**
 * 在shunter插件中实现输出端子
 * @author caroceanjofers
 *
 */
public interface IInputShunterPlugin {
	/**
	 * 接受输入
	 * @param labelFullName 标签全路径名
	 * @param label 标签配置
	 * @param message 消息
	 * @throws CircuitException
	 */
	void input(String labelFullName,Label label,Message message) throws CircuitException;
}
