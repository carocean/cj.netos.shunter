package cj.netos.shunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Label {
	List<ShunterOutputNode> nodes;
	Map<String, String> props;
	public Label() {
		this.nodes=new ArrayList<>();
		this.props=new HashMap<>();
	}
	public List<ShunterOutputNode> getNodes() {
		return nodes;
	}
	public Map<String, String> getProps() {
		return props;
	}
}
