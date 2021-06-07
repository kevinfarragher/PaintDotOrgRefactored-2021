package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Used to create a circle, draw the circle, and check if it intersects the mouse point. 
 * @author Kevin Farragher
 */
public class CircleDrawing extends Drawing {
	
	/**
	 * Creates a circle given its coordinates, color, thickness, and its fill status. 
	 * @param startX - the circle's starting x-coordinate
	 * @param startY - the circle's starting y-coordinate
	 * @param endX - the circle's ending x-coordinate
	 * @param endY - the circle's ending y-coordinate
	 * @param color - the circle's color
	 * @param thickness - the thickness of the circle's border
	 * @param filled - true if the circle is filled, false otherwise. By default, the circle is not filled.
	 */
	public CircleDrawing(int startX, int startY, int endX, int endY, Color color, int thickness, boolean filled) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.color=color;
		this.thickness=thickness;
		this.filled=filled;
	}
	
	/**
	 * Draws the circle.
	 * @param g - Graphics object used to draw the circle.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(thickness));
		g.setColor(color); 
		if(filled==false) {
    		g.drawOval(startX,startY,endX-startX,endY-startY); 
		}
		else {
			g.fillOval(startX,startY,endX-startX,endY-startY);
		}
	}
	
	/**
	 * Checks if the circle intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the circle intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		if(new Ellipse2D.Double(startX,startY,endX-startX,endY-startY).contains(me.getPoint())) {
    		return true;
    	}
		return false;
	}
}
