package paintdotorg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Contains methods and variables for drawings. A drawing can be a shape (rectangle, circle, line)
 * or a non-shape (path, text, image).
 * @author Kevin Farragher
 * 
 */
public abstract class Drawing {
	
	/**
	 * Drawing's coordinates (if applicable)
	 */
	protected int startX;
	protected int startY;
	protected int endX;
	protected int endY;
	
	/**
	 * Drawing's thickness, fill status, and color (if applicable)
	 */
	protected int thickness;
	protected boolean filled;
	protected Color color;
	
	/**
	 * Draws a drawing.
	 * @param g - Graphics object used to draw the program's drawings
	 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * Checks if a drawing intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if a drawing intersects the mouse point, false otherwise
	 */
	public abstract boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea);
	
	
}
