package gui.motions.store.tree.builder;

import java.util.Map;

import gui.motions.store.tree.MotionCategoryTreeNode;
import gui.tree.BaseTreeNode;
import gui.tree.builder.CategoryTreeNodeBuilder;

public class MotionCategoryTreeNodeBuilder extends CategoryTreeNodeBuilder {
	@Override
	public BaseTreeNode buildNode(Map<String, Object> queryNode) {
		MotionCategoryTreeNode node = new MotionCategoryTreeNode(queryNode.get("NAME"), true);
		node.setId(queryNode.get("ID"));
		return node;
	}
}
