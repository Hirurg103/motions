package gui.timeline;
import gui.dimensions.MotionDimension;
import gui.dimensions.MotionDimensionLabel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JSlider;



public class TimelineMotionDimension extends JSlider implements MouseMotionListener, MouseListener {
	/**
	 * 	An element that represents the position of the skeleton part dimension on the time line
	 */
	private static final long serialVersionUID = 596920859582333727L;
	private boolean isSliderChanging = false;
	private boolean isSliderView = false;
	private Image normalView;  
	
	@SuppressWarnings("serial")
	public TimelineMotionDimension() {
		super(0, 10);
		setPreferredSize(new Dimension(14, 16));
		setMinimumSize(new Dimension(14, 16));
		setMaximumSize(new Dimension(14, 16));
		
		setPaintLabels(true);
		setLabelTable(new Hashtable<Integer, JLabel>() {{ put(0, new MotionDimensionLabel("0")); put(5, new MotionDimensionLabel("5")); put(10, new MotionDimensionLabel("10")); }});
		
		try {
			normalView = ImageIO.read(new File("../images/motion_anchor.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		if(g == null) return;
		Rectangle r = getBounds();
		
		g.clearRect(0, 0, r.width, r.height);
		Point p = getMousePosition();
		if(p == null) isSliderView = false;
		if(isSliderView || isSliderChanging) {
			super.paint(g);
		} else {
			g.drawImage(normalView, 0, 0, null);
		}
	}
	
	@Override
	public void repaint() {
		paint(getGraphics());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isSliderView || isSliderChanging) isSliderChanging = true;
		if(isInDragArea(e.getPoint()) && !isSliderView && !isSliderChanging) { 
			Point currentMousePosition = e.getPoint();
			Rectangle currentPosition = getBounds();
			currentPosition.x += currentMousePosition.x - currentPosition.width/2;
			setBounds(currentPosition);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(isInSliderViewArea(e.getPoint()) && !isSliderView) {
			setSliderView();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(isSliderView && !isSliderChanging) {
			setNormalView();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(!isSliderView && isSliderChanging) {
			setNormalView();
		}
		isSliderChanging = false;
		repaint();
	}
	
	private boolean isInSliderViewArea(Point p) {
		if(p.y < 12) return true;
		return false;
	}
	
	private boolean isInDragArea(Point p) {
		return !isInSliderViewArea(p);
	}
	
	private void setNormalView() {
		isSliderView = false;
		Rectangle r = getBounds();
		setBounds(r.x + 48, r.y, 14, 16);
		setMinimumSize(new Dimension(14, 16));
		setPreferredSize(new Dimension(14, 16));
		setMaximumSize(new Dimension(14, 16));
	}
	
	private void setSliderView() {
		isSliderView = true;
		Rectangle r = getBounds();
		setBounds(r.x - 48, r.y, 110, 20);
		setMinimumSize(new Dimension(110, 20));
		setPreferredSize(new Dimension(110, 20));
		setMaximumSize(new Dimension(110, 20));
	}
}
