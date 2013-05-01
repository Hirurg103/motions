package gui.tree.builder;

import gui.tree.BaseTreeNode;
import gui.tree.CategoryTreeNode;

import java.util.Map;

public class CategoryTreeNodeBuilder extends BaseTreeNodeBuilder {
	public BaseTreeNode buildNode(Map<String, Object> queryNode) {
		CategoryTreeNode node = new CategoryTreeNode(queryNode.get("NAME"), true);
		node.setId(queryNode.get("ID"));
		return node;
	}
}
