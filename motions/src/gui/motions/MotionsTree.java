package gui.motions;

import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import db.DatabaseUtils;

public class MotionsTree extends JTree {
	/**
	 * 	Keep motions hierarchy.
	 */
	
	private static final long serialVersionUID = 1L;
	private CategoryTreeNode motionsRoot;
	private DefaultTreeModel motionsTreeModel;
	
	public MotionsTree() {
		super();
		List<Map<String, Object>> queryNodes = DatabaseUtils.query("select * from motion_categories order by name"); 
		motionsRoot = TreeUtils.loadCategoryHierarchy(queryNodes, "parent_id", "name", "id");
		motionsRoot.setUserObject("Motions");
		queryNodes = DatabaseUtils.query("select * from motions order by name");
		motionsRoot.attachTreeLeafs(queryNodes, "category_id", "name", "id");
		motionsTreeModel = new DefaultTreeModel(motionsRoot, true);
		setModel(motionsTreeModel);
		setEditable(true);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);
	}
	
	public DefaultMutableTreeNode getMotionsRoot() { return motionsRoot; }
	
	public DefaultTreeModel getMotionsTreeModel() { return motionsTreeModel; }
	
	class MotionsTreeModelListener implements TreeModelListener {
		@Override
		public void treeNodesChanged(TreeModelEvent e) { }

		@Override
		public void treeNodesInserted(TreeModelEvent e) { }

		@Override
		public void treeNodesRemoved(TreeModelEvent e) { }

		@Override
		public void treeStructureChanged(TreeModelEvent e) { }
		
	}
}
