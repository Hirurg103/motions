package gui.motions;

import gui.MainPanel;
import gui.RightPane;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import db.DatabaseUtils;
import db.FirebirdConnection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateMotionPanel extends JPanel {
	/**
	 * 	This class represents custom motion creation.
	 */
	private static final long serialVersionUID = 6559571653667296936L;
	public static SkeletonPartsSettingPanel skeletonPartsSettingPanel = new SkeletonPartsSettingPanel();

	public CreateMotionPanel() { 
		super();
		JScrollPane skeletonPartsSettingScrollPane = new JScrollPane();
		skeletonPartsSettingScrollPane.setViewportView(skeletonPartsSettingPanel);
		skeletonPartsSettingScrollPane.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, MainPanel.MAIN_PANEL_HEIGHT - 39 - 35));
		add(skeletonPartsSettingScrollPane);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createHorizontalStrut(12));
		buttonsPanel.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, 25));
		JButton buttonSave = new JButton("Save");
		buttonSave.addMouseListener(new SaveButtonMouseListener());
		buttonsPanel.add(buttonSave);
		add(buttonsPanel);
	}
	
	public class SaveButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
//			try {
//				java.sql.Statement st = FirebirdConnection.getInstance().createStatement();
//				st.execute("insert into motions(name) values ('move forward')", new String[] {"id"});
//				ResultSet rs = st.getGeneratedKeys();
//				List<Map<String, Object>> res = DatabaseUtils.map(rs);
//				System.out.println(res);
//			} catch(SQLException ex) {
//				System.out.println("Error!!!!!!!!!!!!!!!!!");
//				ex.printStackTrace();
//			}
			List<Map<String, Object>> res = null;
			ArrayList<Object> params=new ArrayList<Object>();
			//params.add("dina");
			//DatabaseUtils.execute("insert into motion_categories (name, parent_id) values ('a', null)");
			res = DatabaseUtils.execute(FirebirdConnection.getInstance(), "select * from motion_categories mc left outer join motions m on mc.id = m.category_id");
			System.out.println(res.get(1).get("CREATED_AT"));
			System.out.println(res);
			
			//System.out.println(res);
		}
	}
}
