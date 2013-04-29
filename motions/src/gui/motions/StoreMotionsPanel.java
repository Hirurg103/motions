package gui.motions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import db.DatabaseUtils;

public class StoreMotionsPanel extends JPanel {

	/**
	 * 	This class allows to save motion into motions tree.
	 */
	
	private static final long serialVersionUID = -5064535218293659180L;
	private JTextField textFieldNewCategoryName;
	private JButton buttonAddNewCategory;
	private MotionsTree motionsTree;
	
	public StoreMotionsPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		textFieldNewCategoryName = new JTextField(32);
		textFieldNewCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonAddNewCategory = new JButton("Add category");
		buttonAddNewCategory.addMouseListener(new ButtonAddNewCategoryMouseListener());
		textFieldNewCategoryName.setPreferredSize(new Dimension(textFieldNewCategoryName.getPreferredSize().width, buttonAddNewCategory.getPreferredSize().height));
		JPanel topPanel = new JPanel();
		topPanel.add(textFieldNewCategoryName);
		topPanel.add(buttonAddNewCategory);
		add(topPanel);
		add(new JScrollPane(motionsTree = new MotionsTree()));
	}
	
	class ButtonAddNewCategoryMouseListener extends MouseAdapter {
		@Override
		@SuppressWarnings({ "unchecked", "serial" })
		public void mouseClicked(MouseEvent e) {
			final CategoryTreeNode rootCategory;
			TreePath rootCategoryPath = motionsTree.getSelectionPath();
			if(rootCategoryPath == null) {
				rootCategory = (CategoryTreeNode)motionsTree.getMotionsRoot();
			} else {
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)rootCategoryPath.getLastPathComponent();
				while(!root.getAllowsChildren()) root = (DefaultMutableTreeNode)root.getParent();
				rootCategory = (CategoryTreeNode)root;
			}
			final String categoryName = new String(textFieldNewCategoryName.getText());
			CategoryTreeNode newChildCategory = new CategoryTreeNode(categoryName, true);
			int position = 0;
			CategoryTreeNode childCategory;
			for(Enumeration<DefaultMutableTreeNode> childCategories = rootCategory.children(); childCategories.hasMoreElements(); ) {
				childCategory = (CategoryTreeNode)childCategories.nextElement();
				if(!childCategory.getAllowsChildren()) break;
				if(childCategory.compareTo(newChildCategory) >= 0) { break; } else { position++; }
			}
			motionsTree.getMotionsTreeModel().insertNodeInto(newChildCategory, rootCategory, position);
			List<Map<String, Object>> createdCategory = DatabaseUtils.execute("insert into motion_categories (name, parent_id) values (?, ?)", new ArrayList<Object>() {{ add(categoryName); add(rootCategory.getID()); }}, new String[] {"id"});
			if(createdCategory.size() > 0) newChildCategory.setID(createdCategory.get(0).get("ID"));
			motionsTree.scrollPathToVisible(new TreePath(newChildCategory.getPath()));
			motionsTree.setSelectionPath(new TreePath(newChildCategory.getPath()));
		}
	}
}
