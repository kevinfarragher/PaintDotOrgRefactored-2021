package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Used to create a line, draw the line, and check if it intersects the mouse point. 
 * @author Kevin Farragher
 */
public class LineDrawing extends Drawing {
	
	/**
	 * Creates a line given its coordinates, color and thickness.
	 * @param startX - the line's starting x-coordinate
	 * @param startY - the line's starting y-coordinate
	 * @param endX - the line's ending x-coordinate
	 * @param endY - the line's ending y-coordinate
	 * @param color - the line's color
	 * @param thickness - the line's thickness 
	 */
	public LineDrawing(int startX, int startY, int endX, int endY, Color color, int thickness) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.color=color;
		this.thickness=thickness;
	}
	
	/**
	 * Draws the line.
	 * @param g - Graphics object used to draw the line.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(thickness));
		g.setColor(color); 
		g.drawLine(startX,startY,endX,endY); 
		
	}
	
	/**
	 * Checks if the line intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the line intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		if(new Line2D.Double(startX,startY,endX,endY).ptLineDist(me.getPoint())<1) {
    		return true;
    	}
		return false;
	}
}
