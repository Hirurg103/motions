package gui.timeline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gui.HumanCanvas;
import gui.RightPane;
import gui.dimensions.MotionDimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import timeline.computing.TimelineSplinesManager;

public class TimelineDimensionSettingPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 *  This class holds dimensions on time line.
	 */
	private static final long serialVersionUID = -7379485747325143076L;
	private JLabel dimensionLabel = new JLabel();
	private final int TIMELINE_DIMENSION_SETTING_PANEL_HEIGHT = 28;
	private final int Y_TIME_TICK_BOTTOM = 15;
	private final int TIME_TICK_HEIGHT = 3;
	private final int TIME_TICK_MEDIUM_HEIGHT = 2;
	private final int TIME_TICK_SMALL_HEIGHT = 1;
	private final int TIME_TICK_OFFSET = 2;
	private final int VATERLINE_LEVEL = 14;
	public static final int PIXELS_ON_SECOND = 50;
	private final int Y_DIMENSION_LABEL_POSITION = VATERLINE_LEVEL + 2;
	private final int DIMENSION_LABEL_WIDTH;
	private final int DIMENSION_LABEL_HEIGHT;
	private static TimelineDimensionSettingPanel currentTimelineDimensionSettingPanel = null;
	public static final int minLeftCursorPossition = TimelineMotionDimension.horizontalSliderOffset() + TimelineMotionDimension.NORMAL_WIDTH/2;
	private static int cursorPosition = minLeftCursorPossition;
	private boolean isMarker = false;

	public static int timelineDimensionSettingPanelWidth = HumanCanvas.HUMAN_CANVAS_WIDTH + RightPane.RIGHT_PANE_WIDTH; 

	public TimelineDimensionSettingPanel(MotionDimension motionDimension, boolean isMarker) {
		if(motionDimension != null) {
			setDimensionLabel(new TimelineDimensionLabel(motionDimension.getName()));
		} else {
			setDimensionLabel(new TimelineDimensionLabel(""));
		}
		add(getDimensionLabel());
		this.setMarker(isMarker);
		Dimension dimensionLabelSize = getDimensionLabel().getPreferredSize();
		getDimensionLabel().setBounds(0, VATERLINE_LEVEL + 2, DIMENSION_LABEL_WIDTH = dimensionLabelSize.width, DIMENSION_LABEL_HEIGHT = dimensionLabelSize.height);
		setPreferredSize(new Dimension(timelineDimensionSettingPanelWidth, TIMELINE_DIMENSION_SETTING_PANEL_HEIGHT));
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public TimelineDimensionSettingPanel(MotionDimension motionDimension) { this(motionDimension, false); }

	public TimelineDimensionSettingPanel(boolean isMarker) { this(null, isMarker); }

	public TimelineDimensionSettingPanel() { this(null, false); }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		dimensionLabel.setBounds(getVisibleRect().x, Y_DIMENSION_LABEL_POSITION, DIMENSION_LABEL_WIDTH, DIMENSION_LABEL_HEIGHT);
		setPreferredSize(new Dimension(timelineDimensionSettingPanelWidth, TIMELINE_DIMENSION_SETTING_PANEL_HEIGHT));
		g.setColor(Color.BLUE);
		if(currentTimelineDimensionSettingPanel == this || isMarker()) {
			String timeValue;
			Font timeLabelFont = new Font("Geneva", Font.PLAIN, 10);
			JLabel timeLabel; 
			int x = minLeftCursorPossition;
			while(x < timelineDimensionSettingPanelWidth) {
				g.drawLine(x, Y_TIME_TICK_BOTTOM - TIME_TICK_HEIGHT, x, Y_TIME_TICK_BOTTOM);
				g.setFont(timeLabelFont);
				timeValue = new Integer((x - minLeftCursorPossition)/PIXELS_ON_SECOND).toString();
				timeLabel = new JLabel(timeValue);
				timeLabel.setFont(timeLabelFont);
				g.drawString(timeValue, x - timeLabel.getPreferredSize().width/2, Y_TIME_TICK_BOTTOM - TIME_TICK_HEIGHT - TIME_TICK_OFFSET);
				for(int i = x; i < x + PIXELS_ON_SECOND; i += PIXELS_ON_SECOND/10) {
					if(i == x + PIXELS_ON_SECOND/2) { 
						g.drawLine(i, Y_TIME_TICK_BOTTOM - TIME_TICK_MEDIUM_HEIGHT, i, Y_TIME_TICK_BOTTOM); 
						continue; 
					}
					g.drawLine(i, Y_TIME_TICK_BOTTOM - TIME_TICK_SMALL_HEIGHT, i, Y_TIME_TICK_BOTTOM);
				}
				x += PIXELS_ON_SECOND;
			}
		} else {
			g.drawLine(0, VATERLINE_LEVEL, timelineDimensionSettingPanelWidth, VATERLINE_LEVEL);
		}

		g.setColor(Color.RED);
		g.drawLine(getCursorPosition() - 1, 1, getCursorPosition() - 1, Y_TIME_TICK_BOTTOM + 1);
		g.drawLine(getCursorPosition() + 1, 1, getCursorPosition() + 1, Y_TIME_TICK_BOTTOM + 1);
	}

	public JLabel getDimensionLabel() { return dimensionLabel; }

	public void setDimensionLabel(JLabel dimensionLabel) { this.dimensionLabel = dimensionLabel; }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) {
		TimelineDimensionSettingPanel preventTimelineDimensionSettingPanel = currentTimelineDimensionSettingPanel;
		currentTimelineDimensionSettingPanel = this; 
		if(preventTimelineDimensionSettingPanel != null) preventTimelineDimensionSettingPanel.repaint();
		repaint(); 
	}

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { 
		setCursorPosition(e.getPoint().x);
		getParent().getParent().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) { }

	public static int getCursorPosition() { return cursorPosition; }

	public static void setCursorPosition(int cursorPosition) {
		if(cursorPosition < minLeftCursorPossition) return;
		TimelineDimensionSettingPanel.cursorPosition = cursorPosition;
		TimelineSplinesManager.resetMotionDimensions();
		TimelinePanel.timelineSkeletonPartsSettingScrollPane.resetHorisontalScrollBarPosition(cursorPosition);
		TimelinePanel.timelineSkeletonPartsSettingPanel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		setCursorPosition(e.getPoint().x);
	}

	@Override
	public void mouseMoved(MouseEvent e) { }

	public boolean isMarker() { return isMarker; }

	public void setMarker(boolean isMarker) { this.isMarker = isMarker; }

	public static float getTime() { return ((float)getCursorPosition() - minLeftCursorPossition)/PIXELS_ON_SECOND; }

	public static float getMaxTime() { return ((float)timelineDimensionSettingPanelWidth - 2*minLeftCursorPossition)/PIXELS_ON_SECOND; }

	public static void setTime(float time) { setCursorPosition((int)(minLeftCursorPossition + time*PIXELS_ON_SECOND)); }
}
