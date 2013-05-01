package gui.tree.builder;

import java.util.Map;

import gui.tree.BaseTreeNode;

public class BaseTreeNodeBuilder implements TreeNodeBuilder {
	public BaseTreeNode buildNode(Map<String, Object> queryNode) {
		BaseTreeNode node = new BaseTreeNode(queryNode.get("NAME"), true);
		node.setId(queryNode.get("ID"));
		return node;
	}
}
