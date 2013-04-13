package gui;

import java.awt.Component;
import java.awt.Container;

public class GUIUtils {
	public static ApplicationFrame getApplicationFrame(Component component) {
		Container parent = component.getParent();
		while(!(parent instanceof ApplicationFrame || parent == null)) parent = parent.getParent();
		return (ApplicationFrame)parent;
	}
}
