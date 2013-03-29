package actions;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.HumanCanvas;

public class CustomAction extends JButton implements ActionListener {
	/** Base class for handle actions on tool bar.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static HumanCanvas humanCanvas;
	

	public CustomAction(String name) { 
		super(); 
		setName(name);
		setToolTipText(name);
		setUnselected(); 
	}
	
	public void setSelected() {
		setIcon(new ImageIcon("../images/".concat(getName().toLowerCase().replaceAll("\\s", "_")).concat("_selected_icon.jpeg")));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

	public void setUnselected() {
		setIcon(new ImageIcon("../images/".concat(getName().toLowerCase().replaceAll("\\s", "_")).concat("_unselected_icon.jpeg")));
		setBorder(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setSelected();
		for(Component component : getParent().getComponents()) {
			if(component instanceof CustomAction && component != this) {
				CustomAction customAction = (CustomAction)component;
				customAction.setUnselected();
			}
		}
		humanCanvas.setMouseAdapter(getName());
	}
}
