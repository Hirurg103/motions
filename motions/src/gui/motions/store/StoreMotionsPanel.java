package gui.motions.store;

import gui.dimensions.MotionDimension;
import gui.motions.store.tree.MotionTreeNode;
import gui.motions.store.tree.MotionsTree;
import gui.tree.BaseTreeNode;
import gui.tree.CategoryTreeNode;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private static ArrayList<MotionDimension> motionDimensions = null;
	private static JPanel storeMotionsControlPanel = null;
	private static JTextField textFieldNewMotionName;
	
	public StoreMotionsPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		textFieldNewCategoryName = new JTextField(26);
		textFieldNewCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonAddNewCategory = new JButton("Add category");
		buttonAddNewCategory.addMouseListener(new ButtonAddNewCategoryMouseListener());
		textFieldNewCategoryName.setPreferredSize(new Dimension(textFieldNewCategoryName.getPreferredSize().width, buttonAddNewCategory.getPreferredSize().height));
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("New category name:"));
		topPanel.add(textFieldNewCategoryName);
		topPanel.add(buttonAddNewCategory);
		add(topPanel);
		add(new JScrollPane(motionsTree = new MotionsTree()));
		
		storeMotionsControlPanel = new JPanel();
		storeMotionsControlPanel.add(new JLabel("New motion name"));
		textFieldNewMotionName = new JTextField(26);
		textFieldNewMotionName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		storeMotionsControlPanel.add(textFieldNewMotionName);
		JButton buttonSaveNewMotion = new JButton("Save motion");
		buttonSaveNewMotion.addMouseListener(new ButtonSaveNewMotionMouseListener());
		storeMotionsControlPanel.add(buttonSaveNewMotion);
		textFieldNewMotionName.setPreferredSize(new Dimension(textFieldNewMotionName.getPreferredSize().width, buttonSaveNewMotion.getPreferredSize().height));
		storeMotionsControlPanel.setVisible(false);
		add(storeMotionsControlPanel);
	}
	
	public static ArrayList<MotionDimension> getMotionDimensions() {
		return motionDimensions;
	}

	public static void setMotionDimensions(ArrayList<MotionDimension> motionDimensions) {
		StoreMotionsPanel.motionDimensions = motionDimensions;
	}

	public static JPanel getStoreMotionsControlPanel() {
		return storeMotionsControlPanel;
	}

	public static void setStoreMotionsControlPanel(JPanel storeMotionsControlPanel) { StoreMotionsPanel.storeMotionsControlPanel = storeMotionsControlPanel; }
	
	public static JTextField getTextFieldNewMotionName() { return StoreMotionsPanel.textFieldNewMotionName; }

	class ButtonAddNewCategoryMouseListener extends MouseAdapter {
		@Override
		@SuppressWarnings({ "unchecked", "serial" })
		public void mouseClicked(MouseEvent e) {
			final String categoryName = new String(textFieldNewCategoryName.getText());
			if(categoryName.isEmpty()) { JOptionPane.showMessageDialog(StoreMotionsPanel.this, "Cannot create empty category!", "Category name violation!", JOptionPane.ERROR_MESSAGE); return; }
			final CategoryTreeNode rootCategory = findSelectedCategory();
			if(rootCategory == null) return;
			CategoryTreeNode newChildCategory = new CategoryTreeNode(categoryName, true);
			int position = 0;
			BaseTreeNode childCategory;
			for(Enumeration<DefaultMutableTreeNode> childCategories = rootCategory.children(); childCategories.hasMoreElements(); ) {
				childCategory = (BaseTreeNode)childCategories.nextElement();
				if(!childCategory.getAllowsChildren()) break;
				if(childCategory.compareTo(newChildCategory) >= 0) { break; } else { position++; }
			}
			List<Map<String, Object>> createdCategory = DatabaseUtils.execute("insert into categories (name, category_type_id, parent_id) values (?, ?, ?)", new ArrayList<Object>() {{ add(categoryName); add(MotionsTree.getCategoryTypeId()); add(rootCategory.getId()); }}, new String[] {"id"});
			if(createdCategory.isEmpty()) return;
			
			newChildCategory.setId(createdCategory.get(0).get("ID"));
			motionsTree.getMotionsTreeModel().insertNodeInto(newChildCategory, rootCategory, position);
			motionsTree.scrollPathToVisible(new TreePath(newChildCategory.getPath()));
			motionsTree.setSelectionPath(new TreePath(newChildCategory.getPath()));
		}
	}
	
	class ButtonSaveNewMotionMouseListener extends MouseAdapter {
		@Override
		@SuppressWarnings({"serial", "unchecked"})
		public void mouseClicked(MouseEvent e) {
			if(motionDimensions.isEmpty()) { JOptionPane.showMessageDialog(StoreMotionsPanel.this, "New motion has no dimensions!", "Motion violation!", JOptionPane.ERROR_MESSAGE); return; }
			final String motionName = new String(textFieldNewMotionName.getText());
			if(motionName.isEmpty()) { JOptionPane.showMessageDialog(StoreMotionsPanel.this, "New motion name is not present!", "Motion name violation!", JOptionPane.ERROR_MESSAGE); return; }
			final CategoryTreeNode rootCategory = findSelectedCategory();
			if(rootCategory == null) return;
			MotionTreeNode newMotion = new MotionTreeNode(motionName, false);
			int position = 0;
			BaseTreeNode childCategory;
			for(Enumeration<DefaultMutableTreeNode> childCategories = rootCategory.children(); childCategories.hasMoreElements(); ) {
				childCategory = (BaseTreeNode)childCategories.nextElement();
				if(childCategory.getAllowsChildren()) { position++; continue; };
				if(childCategory.compareTo(newMotion) >= 0) { break; } else { position++; }
			}
			List<Map<String, Object>> createdMotion = DatabaseUtils.execute("insert into motions (name, category_id) values (?, ?)", new ArrayList<Object>() {{ add(motionName); add(rootCategory.getId()); }}, new String[] { "id" });
			if(createdMotion.isEmpty()) { JOptionPane.showMessageDialog(StoreMotionsPanel.this, "Cannot create new motion!", "Motion violation!", JOptionPane.ERROR_MESSAGE); return; }
			
			final Integer createdMotionId = (Integer)createdMotion.get(0).get("ID"); 
			newMotion.setId(createdMotionId);
			motionsTree.getMotionsTreeModel().insertNodeInto(newMotion, rootCategory, position);
			motionsTree.scrollPathToVisible(new TreePath(newMotion.getPath()));
			motionsTree.setSelectionPath(new TreePath(newMotion.getPath()));
			for(final MotionDimension motionDimension : motionDimensions) {
				DatabaseUtils.execute("insert into motion_dimensions (motion_id, dimension_id, from_f, to_f, initial_f, is_bound) values (?, ?, ?, ?, ?, ?)", new ArrayList<Object>() {{ add(createdMotionId); add(motionDimension.getId()); add(motionDimension.getFrom()); add(motionDimension.getTo()); add(motionDimension.getConvertedValue()); add(motionDimension.getIsSynchronized() ? 1 : 0); }});
			}
			storeMotionsControlPanel.setVisible(false);
		}
	}
	
	private CategoryTreeNode findSelectedCategory() {
		TreePath rootCategoryPath = motionsTree.getSelectionPath();
		if(rootCategoryPath == null) {
			JOptionPane.showMessageDialog(StoreMotionsPanel.this, "Category is not selected!", "Category selection violation!", JOptionPane.ERROR_MESSAGE); return null;
		} else {
			DefaultMutableTreeNode selected = (DefaultMutableTreeNode)rootCategoryPath.getLastPathComponent();
			if(!(selected instanceof CategoryTreeNode)) { 
				JOptionPane.showMessageDialog(StoreMotionsPanel.this, "Selected item is not category!", "Category selection violation!", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			return (CategoryTreeNode)selected;
		}
	}
}
