package gui.timeline;
import figures.HumanSkeleton;
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
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import timeline.computing.TimelineSplinesManager;

public class TimelineMotionDimension extends MotionDimension implements MouseMotionListener, MouseListener {
	/**
	 * 	An element that represents the position of the skeleton part dimension on the time line
	 */
	private static final long serialVersionUID = 596920859582333727L;
	private boolean isSliderChanging = false;
	private boolean isSliderView = false;
	private boolean isDragging = false;
	private Image normalView;  
	public static final int NORMAL_WIDTH = 14;
	public static final int NORMAL_HEIGHT = 16;
	public static final int SLIDER_WIDTH = 110;
	public static final int SLIDER_HEIGHT = 28;
	private static final TimelineSkeletonPartsSettingScrollPane timelineSkeletonPartsSettingScrollPane = TimelinePanel.timelineSkeletonPartsSettingScrollPane;
	private TimelineMotion timelineMotion = null;
	private float time;
	private Object motionDimensionId;
	private TimelineMotionDimension timelineMotionDimension;

	public TimelineMotionDimension(Map<String, Object> queryMotionDimension) {
		super(HumanSkeleton.motionDimensions.get(queryMotionDimension.get("DIMENSION_ID")).getName(), new Float(queryMotionDimension.get("FROM_F").toString()), new Float(queryMotionDimension.get("TO_F").toString()), new Float(queryMotionDimension.get("INITIAL_F").toString()));
		MotionDimension motionDimension = HumanSkeleton.motionDimensions.get(queryMotionDimension.get("DIMENSION_ID"));
		setPossibleTickLengths(motionDimension.getPossibleTickLengths());
		setUnitSign(motionDimension.getUnitSign());
		setMultiplier(motionDimension.getMultiplier());
		setSkeletonPartId(motionDimension.getSkeletonPartId());

		setIsSynchronized((int)queryMotionDimension.get("IS_SYNCHRONIZED") == 1);
		setMotionId(queryMotionDimension.get("MOTION_ID"));
		setId(queryMotionDimension.get("ID"));
		setMotionDimensionId(motionDimension.getId());

		setBounds(TimelineDimensionSettingPanel.getCursorPosition() - NORMAL_WIDTH/2, 0, NORMAL_WIDTH, NORMAL_HEIGHT);
		this.time = calculateTime();
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
		TimelineSplinesManager.add(this);
		setTimelineMotionDimension(this);
		addChangeListener(new TimelineMotionDimensionChangeListener());
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
			if(!isDragging()) setDraggingForMotion(true);
			Point currentMousePosition = e.getPoint();
			Rectangle currentPosition = getBounds();

			int mousePositionRelativeToParent = currentPosition.x + currentMousePosition.x; 

			// Reset scroll bar position
			if(timelineSkeletonPartsSettingScrollPane != null) {
				timelineSkeletonPartsSettingScrollPane.resetHorisontalScrollBarPosition(mousePositionRelativeToParent);
			}

			currentPosition.x += currentMousePosition.x - currentPosition.width/2;
			setBoundsForMotion(currentPosition);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(isInSliderViewArea(e.getPoint()) && !isSliderView) {
			setSliderViewForMotion();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(isSliderView && !isSliderChanging) {
			setNormalViewForMotion();
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
		if(isDragging()) {
			setDraggingForMotion(false);
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

	public void setBoundsForMotion(Rectangle currentPosition) {
		if(getTimelineMotion() != null) {
			for(TimelineMotionDimension timelineMotionDimension : getTimelineMotion().getTimelineMotionDimensions()) {
				timelineMotionDimension.setBounds(currentPosition);
			}
		} else {
			setBounds(currentPosition);
		}
	}

	private boolean isInSliderViewArea(Point p) {
		if(p.y < 12) return true;
		return false;
	}

	private boolean isInDragArea(Point p) {
		return !isInSliderViewArea(p);
	}

	public void setNormalView() {
		if(isSliderView) {
			isSliderView = false;
			Rectangle r = getBounds();
			setBounds(r.x + horizontalSliderOffset(), r.y, NORMAL_WIDTH, NORMAL_HEIGHT);
			setMinimumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
			setPreferredSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
			setMaximumSize(new Dimension(NORMAL_WIDTH, NORMAL_HEIGHT));
		}
	}

	public void setNormalViewForMotion() {
		if(getTimelineMotion() != null) {
			getTimelineMotion().setNormalView();
		} else {
			setNormalView();
		}
	}

	public void setSliderView() {
		if(!isSliderView) {
			isSliderView = true;
			Rectangle r = getBounds();
			setBounds(r.x - horizontalSliderOffset(), r.y, SLIDER_WIDTH, SLIDER_HEIGHT);
			setMinimumSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
			setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
			setMaximumSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
		}
	}

	public void setSliderViewForMotion() {
		if(getTimelineMotion() != null) {
			getTimelineMotion().setSliderView();
		} else {
			setSliderView();
		}
	}

	public static int horizontalSliderOffset() {
		return (SLIDER_WIDTH - NORMAL_WIDTH)/2;
	}

	public TimelineMotion getTimelineMotion() { return timelineMotion; }

	public void setTimelineMotion(TimelineMotion timelineMotion) { this.timelineMotion = timelineMotion; }

	public float getTime() { return time; }

	public void resetTime() {
		float oldTime = this.time;
		this.time = calculateTime();
		TimelineSplinesManager.resetTimeFor(this, oldTime);
	}

	public float calculateTime() {
		Rectangle currentBounds = getBounds();
		return ((float)currentBounds.x  + currentBounds.width/2 - TimelineDimensionSettingPanel.minLeftCursorPossition)/TimelineDimensionSettingPanel.PIXELS_ON_SECOND;
	}

	public boolean isDragging() { return isDragging; }

	public void setDragging(boolean isDragging) {
		if(isDragging == false) resetTime();
		this.isDragging = isDragging;
	}

	public void setDraggingForMotion(boolean isDragging) {
		if(getTimelineMotion() != null) {
			getTimelineMotion().setDragging(isDragging);
		} else {
			setDragging(isDragging);
		}
	}

	public Object getMotionDimensionId() { return motionDimensionId; }

	public void setMotionDimensionId(Object motionDimensionId) { this.motionDimensionId = motionDimensionId; }

	public TimelineMotionDimension getTimelineMotionDimension() { return timelineMotionDimension; }

	public void setTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) { this.timelineMotionDimension = timelineMotionDimension; }

	class TimelineMotionDimensionChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			TimelineSplinesManager.resetValueFor(getTimelineMotionDimension());
		}
	}
}
