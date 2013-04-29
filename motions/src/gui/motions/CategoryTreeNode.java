package gui.motions;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

public class CategoryTreeNode extends DefaultMutableTreeNode implements Comparable<DefaultMutableTreeNode> {
	/**
	 * 	Class represents node in motions tree hierarchy.
	 */
	private static final long serialVersionUID = 5139428464107592792L;
	private Object id = null;
	
	public CategoryTreeNode() {
		super();
	}
	
	public CategoryTreeNode(Object userObject) {
		super(userObject);
	}
	
	public CategoryTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}
	
	@SuppressWarnings("unchecked")
	public void attachTreeLeafs(List<Map<String, Object>> queryNodes, String parentColumnName, String userObjectColumnName, String idColumnName) {
		parentColumnName = parentColumnName.toUpperCase(); userObjectColumnName = userObjectColumnName.toUpperCase(); idColumnName = idColumnName.toUpperCase();
		for(Enumeration<DefaultMutableTreeNode> children = this.children(); children.hasMoreElements();) {
			CategoryTreeNode category = (CategoryTreeNode)children.nextElement();
			category.attachTreeLeafs(queryNodes, parentColumnName, userObjectColumnName, idColumnName);
		}
		
		for(Map<String, Object> queryNode : queryNodes) {
			if((getID() == null && queryNode.get(parentColumnName) == null) || getID() != null && getID().equals(queryNode.get(parentColumnName))) {
				CategoryTreeNode leaf = new CategoryTreeNode(queryNode.get(userObjectColumnName));
				leaf.setAllowsChildren(false);
				leaf.setID(queryNode.get(idColumnName));
				add(leaf);
			}
		}
	}
	
	public Object getID() { return id; }

	public void setID(Object id) { this.id = id; }

	@Override
	public int compareTo(DefaultMutableTreeNode node) {
		return getUserObject().toString().compareTo(node.getUserObject().toString());
	}
	
}
