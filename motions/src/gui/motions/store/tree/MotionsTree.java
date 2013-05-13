package gui.motions.store.tree;

import gui.motions.store.tree.builder.MotionCategoryTreeNodeBuilder;
import gui.motions.store.tree.builder.MotionTreeNodeBuilder;
import gui.timeline.TimelineMotion;
import gui.timeline.TimelinePanel;
import gui.tree.BaseTreeNode;
import gui.tree.CategoryTreeNode;
import gui.tree.TreeUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import db.DatabaseUtils;

@SuppressWarnings("serial")
public class MotionsTree extends JTree {
	/**
	 * 	Keep motions hierarchy.
	 */
	
	private static final long serialVersionUID = 1L;
	private CategoryTreeNode motionsRoot;
	private DefaultTreeModel motionsTreeModel;
	private static final String categoryTypeName = "motions";
	private static Object categoryTypeId;
	
	static {
		List<Map<String, Object>> queryCategoryType = DatabaseUtils.query("select * from category_types where name = ?", new ArrayList<Object>() {{ add(categoryTypeName); }});
		if(queryCategoryType.isEmpty()) queryCategoryType = DatabaseUtils.execute("insert into category_types (name) values (?)", new ArrayList<Object>() {{ add(categoryTypeName); }}, new String[] { "id" });
		if(queryCategoryType.isEmpty()) { 
			System.out.println("Unable to create motion category"); System.exit(0); 
		} else {
			setCategoryTypeId(queryCategoryType.get(0).get("ID"));
		}
	}
	
	public MotionsTree() {
		super();
		List<Map<String, Object>> queryNodes = DatabaseUtils.query("select * from categories where category_type_id = ? order by name", new ArrayList<Object>() {{ add(getCategoryTypeId()); }});
		motionsRoot = new MotionCategoryTreeNode("Motions", true);
		TreeUtils.loadCategoryHierarchy(motionsRoot, queryNodes, "parent_id",  "id", new MotionCategoryTreeNodeBuilder());
		queryNodes = DatabaseUtils.query("select * from motions where name is not null order by name");
		motionsRoot.attachTreeLeafs(queryNodes, "category_id", "id", new MotionTreeNodeBuilder());
		motionsTreeModel = new DefaultTreeModel(motionsRoot, true);
		motionsTreeModel.addTreeModelListener(new MotionsTreeModelListener());
		setModel(motionsTreeModel);
		// not editable yet
		// setEditable(true);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);
		addMouseListener(new MotionsTreeMouseListener());
	}
	
	public DefaultMutableTreeNode getMotionsRoot() { return motionsRoot; }
	
	public DefaultTreeModel getMotionsTreeModel() { return motionsTreeModel; }
	
	public static Object getCategoryTypeId() { return MotionsTree.categoryTypeId; }

	public static void setCategoryTypeId(Object categoryTypeId) { MotionsTree.categoryTypeId = categoryTypeId; }
	
	public static String getCategoryTypeName() { return MotionsTree.categoryTypeName; }

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
	
	class MotionsTreeMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() != 2) return;
			BaseTreeNode selectedNode = (BaseTreeNode)getPathForLocation(e.getPoint().x, e.getPoint().y).getLastPathComponent();
			if(!(selectedNode instanceof MotionTreeNode)) return;
			MotionTreeNode selectedMotion = (MotionTreeNode)selectedNode;
			TimelinePanel.timelineSkeletonPartsSettingPanel.addTimelineMotion(new TimelineMotion(selectedMotion.getId()));
		}
	}
}
