package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Used to create a rectangle, draw the rectangle, and check if it intersects the mouse point. 
 * @author Kevin Farragher
 */
public class RectangleDrawing extends Drawing {
	
	public boolean draggable; //rectangle's draggable status

	/**
	 * Creates a rectangle given its coordinates, color, thickness, and its fill status. 
	 * @param startX - the rectangle's starting x-coordinate
	 * @param startY - the rectangle's starting y-coordinate
	 * @param endX - the rectangle's ending x-coordinate
	 * @param endY - the rectangle's ending y-coordinate
	 * @param color - the rectangle's color
	 * @param thickness - the thickness of the rectangle's border
	 * @param draggable - true if the rectangle is draggable, false otherwise. 
	 * @param filled - true if the rectangle is filled, false otherwise. By default, the rectangle is not filled.
	 */
	public RectangleDrawing(int startX, int startY, int endX, int endY, Color color, int thickness, boolean draggable, boolean filled) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.color=color;
		this.thickness=thickness;
		this.draggable=draggable;
		this.filled=filled;
	}
	
	
	/**
	 * Draws the rectangle.
	 * @param g - Graphics object used to draw the rectangle.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(thickness));
		g.setColor(color); 
		if(filled==false) {
    		g.drawRect(startX,startY,endX-startX,endY-startY); 
		}
		else {
			g.fillRect(startX,startY,endX-startX,endY-startY);
		}
	}
	
	/**
	 * Checks if the rectangle intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the rectangle intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		if(drawingArea.filling==true) {
			if(new Rectangle2D.Double(startX,startY,endX-startX,endY-startY).contains(me.getPoint())) {
	    		return true;
	    	}
		}
		else if(drawingArea.selecting==true) {
			if(new Rectangle2D.Double(startX,startY,endX-startX,endY-startY).contains(me.getPoint()) && draggable==true) {
		    	return true;
		    }
		}
		return false;
	}
}
