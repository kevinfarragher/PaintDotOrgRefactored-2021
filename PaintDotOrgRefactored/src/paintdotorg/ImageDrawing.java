package paintdotorg;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Used to create an image, draw the image, and check if it intersects the mouse point. 
 * @author Kevin Farragher
 */
public class ImageDrawing extends Drawing {

	public BufferedImage image; //image's source
	public boolean isDraggable; //image's draggable status
	public DrawingArea drawingArea; //link to the drawing area
	
	/**
	 * Creates an image given its source, coordinates, and its draggable status. 
	 * @param image - the image's source
	 * @param startX - the image's starting x-coordinate
	 * @param startY - the image's starting y-coordinate
	 * @param endX - the image's ending x-coordinate
	 * @param endY - the image's ending y-coordinate
	 * @param draggable - true if the image is draggable, false otherwise. 
	 * @param drawingArea TODO
	 */
	public ImageDrawing(BufferedImage image, int startX, int startY, int endX, int endY, boolean draggable, DrawingArea drawingArea) {
		this.image=image;
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.isDraggable=draggable;
		this.drawingArea=drawingArea;
	}
	
	/**
	 * Draws the image.
	 * @param g - Graphics object used to draw the image.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, startX, startY, endX-startX, endY-startY, drawingArea);
	}
	
	/**
	 * Checks if the image intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the image intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		if(new Rectangle2D.Double(startX,startY,endX-startX,endY-startY).contains(me.getPoint()) && isDraggable==true) {
    		return true;
    	}
		return false;
	}
}
