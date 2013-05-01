package gui.motions.store.tree.builder;

import java.util.Map;

import gui.motions.store.tree.MotionTreeNode;
import gui.tree.BaseTreeNode;
import gui.tree.builder.BaseTreeNodeBuilder;

public class MotionTreeNodeBuilder extends BaseTreeNodeBuilder {
	public BaseTreeNode buildNode(Map<String, Object> queryNode) {
		BaseTreeNode node = new MotionTreeNode(queryNode.get("NAME"), true);
		node.setId(queryNode.get("ID"));
		node.setAllowsChildren(false);
		return node;
	}
}
