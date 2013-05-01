package gui.motions.store.tree;

import gui.tree.BaseTreeNode;

public class MotionTreeNode extends BaseTreeNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5415745134981567380L;
	
	public MotionTreeNode() {
		super();
	}
	
	public MotionTreeNode(Object userObject) {
		super(userObject);
	}
	
	public MotionTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}
}
