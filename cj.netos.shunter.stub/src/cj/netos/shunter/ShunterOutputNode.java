package cj.netos.shunter;

import java.util.ArrayList;
import java.util.List;

public class ShunterOutputNode {
	String destName;
	String host;
	List<String> outputUrls;

	public ShunterOutputNode() {
		outputUrls = new ArrayList<String>();
	}

	public ShunterOutputNode(String destName, String host) {
		this();
		this.destName = destName;
		this.host = host;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<String> getOutputUrls() {
		return outputUrls;
	}

	/**
	 * 通知地址，informer会根据此地址将之添加到路由表。<br>
	 * 该地址对应的是远程的站点的首页，格式如：https://host:port/website/<br>
	 * <b>依赖：informer</b>
	 * 
	 * <pre>
	 *  \\@CjServiceRef(refByName = "$.netos.informer")
	 * Informer informer;
	 * </pre>
	 * 
	 * @return
	 */
	public String getInformAddress() {
		String h = host;
		if (h.endsWith("/")) {
			h = h.substring(0, h.length() - 1);
		}
		String d = destName;
		if (d.startsWith("/")) {
			d = d.substring(1, d.length());
		}
		return String.format("%s/%s", h, d);
	}

	/**
	 * 带有相对路径的通知地址，informer会向该相对地址发送请求<br>
	 * 格式如：https://host:port/website/url <br>
	 * <b>依赖：informer</b>
	 * 
	 * <pre>
	 *  \\@CjServiceRef(refByName = "$.netos.informer")
	 * Informer informer;
	 * </pre>
	 * 
	 * @param url 相对地址
	 * @return
	 */
	public String getInformAddress(String url) {
		String h = getInformAddress();
		if (h.endsWith("/")) {
			h = h.substring(0, h.length() - 1);
		}
		String d = url;
		if (d.startsWith("/")) {
			d = d.substring(1, d.length());
		}
		return String.format("%s/%s", h, d);
	}
}
