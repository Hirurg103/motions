package gui.actions;

import gui.GUIUtils;
import gui.MainPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomAction extends JButton implements ActionListener {
	/** 
	 * 	Base class for handle actions on tool bar.
	 */
	private static final long serialVersionUID = 1L;

	public CustomAction(String name) { 
		super(); 
		setName(name);
		setToolTipText(name);
		setSelected(false); 
	}

	@Override
	public void setSelected(boolean isSelected) {
		super.setSelected(isSelected);
		if(isSelected) {
			setIcon(new ImageIcon("../images/".concat(getName().toLowerCase().replaceAll("\\s", "_")).concat("_selected_icon.jpeg")));
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		} else {
			setIcon(new ImageIcon("../images/".concat(getName().toLowerCase().replaceAll("\\s", "_")).concat("_unselected_icon.jpeg")));
			setBorder(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setSelected(true);
		for(Component component : getParent().getComponents()) {
			if(component instanceof CustomAction && component != this) {
				CustomAction customAction = (CustomAction)component;
				customAction.setSelected(false);
			}
		}
		MainPanel mainPanel = GUIUtils.getApplicationFrame(this).getMainPanel();
		if(mainPanel != null) mainPanel.getHumanCanvas().setMouseAdapter(getName());
	}
}
