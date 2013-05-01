package gui.tree.builder;

import java.util.Map;

import gui.tree.BaseTreeNode;

public interface TreeNodeBuilder {
	public BaseTreeNode buildNode(Map<String, Object> queryNode);
}
