package gui.tree;

public class CategoryTreeNode extends BaseTreeNode {
	/**
	 * 	Class represents node in motions tree hierarchy.
	 */
	private static final long serialVersionUID = 5139428464107592792L;
	
	public CategoryTreeNode() {
		super();
	}
	
	public CategoryTreeNode(Object userObject) {
		super(userObject);
	}
	
	public CategoryTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}
}
