import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.ApplicationFrame;
import gui.motions.store.StoreMotionsPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;


import db.BaseConnection;
import db.DatabaseUtils;
import db.FirebirdConnection;

@SuppressWarnings("serial")
public class Main {
	/** The entry main() method to setup the top-level container and canvas */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				List<Map<String, Object>> result;
//				FirebirdConnection fbcon = new FirebirdConnection();
//				result = fbcon.query("select * from employees");
//				System.out.println(result);
				new ApplicationFrame();
//				JFrame frame = new JFrame();
//				frame.setSize(400, 300);
//				frame.getContentPane().add(comp);
//				JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//				JPanel panel = new JPanel();
//				pane.setViewportView(panel);
//				pane.setPreferredSize(new Dimension(400, 300));
//				pane.setLayout(new ScrollPaneLayout());
//				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//				JPanel panel1 = new JPanel();
//				JPanel panel2 = new JPanel();
//				panel.add(panel1);
//				panel.add(panel2);
//				
//				for(int i = 0; i < 10; i++) {
//					panel1.add(new JCheckBox());
//				}
//				for(int i = 0; i < 10; i++) {
//					panel2.add(new JCheckBox());
//				}
//				panel.add(Box.createVerticalStrut(30 - panel.getPreferredSize().height));
//				frame.getContentPane().add(pane);
//				frame.pack();
//				frame.setVisible(true);
				//System.out.println("\u00D7");
         	}
		});
	}	
}