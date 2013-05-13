package gui.timeline;
import gui.dimensions.MotionDimension;

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

import javax.imageio.ImageIO;

public class TimelineMotionDimension extends MotionDimension implements MouseMotionListener, MouseListener {
	/**
	 * 	An element that represents the position of the skeleton part dimension on the time line
	 */
	private static final long serialVersionUID = 596920859582333727L;
	private boolean isSliderChanging = false;
	private boolean isSliderView = false;
	private Image normalView;  
	public static final int NORMAL_WIDTH = 14;
	public static final int NORMAL_HEIGHT = 16;
	public static final int SLIDER_WIDTH = 110;
	public static final int SLIDER_HEIGHT = 28;
	private static final TimelineSkeletonPartsSettingScrollPane timelineSkeletonPartsSettingScrollPane = TimelinePanel.timelineSkeletonPartsSettingScrollPane;
	private TimelineMotion timelineMotion = null;

	public TimelineMotionDimension(MotionDimension motionDimension, float from, float to, float initial) {
		super(motionDimension.getName(), from, to, initial);
		setPossibleTickLengths(motionDimension.getPossibleTickLengths());
		setUnitSign(motionDimension.getUnitSign());
		setMultiplier(motionDimension.getMultiplier());
		setSkeletonPartId(motionDimension.getSkeletonPartId());

		setBounds(0, 0, NORMAL_WIDTH, NORMAL_HEIGHT);
		setPreferredSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
		setMinimumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
		setMaximumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));

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
		
			int mousePositionRelativeToParent = currentPosition.x + currentMousePosition.x; 
			
			// Reset scroll bar position
			if(timelineSkeletonPartsSettingScrollPane != null) {
				timelineSkeletonPartsSettingScrollPane.resetHorisontalScrollBarPosition(mousePositionRelativeToParent);
			}
		
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
	public void mousePressed(MouseEvent e) {	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Rectangle currentBounds = getBounds();
		if(isSliderView && (currentBounds.width < e.getPoint().x || e.getPoint().x < 0 || currentBounds.height < e.getPoint().y || e.getPoint().y < 0)) {
			setNormalView();
		}
		isSliderChanging = false;
		repaint();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		if(!isSliderView && !isSliderChanging) {
			x = Math.max(horizontalSliderOffset(), x);
		} else {
			x = Math.max(0, x);
		}
		super.setBounds(x, y, width, height);
	};

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
		setBounds(r.x + horizontalSliderOffset(), r.y, NORMAL_WIDTH, NORMAL_HEIGHT);
		setMinimumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
		setPreferredSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
		setMaximumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
	}

	private void setSliderView() {
		isSliderView = true;
		Rectangle r = getBounds();
		setBounds(r.x - horizontalSliderOffset(), r.y, SLIDER_WIDTH, SLIDER_HEIGHT);
		setMinimumSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
		setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
		setMaximumSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
	}

	public static int horizontalSliderOffset() {
		return (SLIDER_WIDTH - NORMAL_WIDTH)/2;
	}

	public TimelineMotion getTimelineMotion() { return timelineMotion; }

	public void setTimelineMotion(TimelineMotion timelineMotion) { this.timelineMotion = timelineMotion; }
}
