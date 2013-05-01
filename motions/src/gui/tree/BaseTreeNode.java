package gui.tree;

import gui.tree.builder.BaseTreeNodeBuilder;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

public class BaseTreeNode extends DefaultMutableTreeNode implements Comparable<DefaultMutableTreeNode> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8806703709751791282L;
	protected Object id = null;
	
	public BaseTreeNode() {
		super();
	}
	
	public BaseTreeNode(Object userObject) {
		super(userObject);
	}
	
	public BaseTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}
	
	@SuppressWarnings("unchecked")
	public void attachTreeLeafs(List<Map<String, Object>> queryNodes, String parentColumnName, String idColumnName, BaseTreeNodeBuilder builder) {
		parentColumnName = parentColumnName.toUpperCase(); idColumnName = idColumnName.toUpperCase();
		for(Enumeration<DefaultMutableTreeNode> children = this.children(); children.hasMoreElements();) {
			BaseTreeNode category = (BaseTreeNode)children.nextElement();
			category.attachTreeLeafs(queryNodes, parentColumnName, idColumnName, builder);
		}
		
		for(Map<String, Object> queryNode : queryNodes) {
			if((getId() == null && queryNode.get(parentColumnName) == null) || getId() != null && getId().equals(queryNode.get(parentColumnName))) {
				BaseTreeNode leaf = builder.buildNode(queryNode);
				add(leaf);
			}
		}
	}
	
	public Object getId() { return id; }
	
	public void setId(Object id) { this.id = id; }
	
	@Override
	public int compareTo(DefaultMutableTreeNode node) {
		return getUserObject().toString().compareTo(node.getUserObject().toString());
	}
}
