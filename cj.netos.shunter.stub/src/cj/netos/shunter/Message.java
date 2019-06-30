package cj.netos.shunter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Message {
	Map<String, String> parameters;
	String content;

	public Message() {
		parameters = new HashMap<String, String>();
	}

	public void parameter(String key, String value) {
		parameters.put(key, value);
	}

	public String parameter(String key) {
		return parameters.get(key);
	}

	public Set<String> enumParamterKey() {
		return parameters.keySet();
	}

	public int count() {
		return parameters.size();
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
