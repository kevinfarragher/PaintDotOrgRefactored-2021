package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 * Used to create a path, draw the path, and check if it intersects the mouse point. 
 * @author Kevin Farragher
 */
public class PathDrawing extends Drawing {
	
	private GeneralPath path; //the path's source
	
	/**
	 * Creates a path given its source, color, and thickness.
	 * @param path - the path's source
	 * @param color - the path's color
	 * @param thickness - the path's thickness
	 */
	public PathDrawing(GeneralPath path, Color color, int thickness) {
		this.path=path;
		this.color=color;
		this.thickness=thickness;
	}
	
	/**
	 * Draws the path.
	 * @param g - Graphics object used to draw the path.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(thickness));
    	g.setColor(color); 
    	g.draw(path); 
	}
	
	/**
	 * Checks if the path intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the path intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		if(path.contains(me.getPoint())) {
    		return true;
    	}
		return false;
	}
}
