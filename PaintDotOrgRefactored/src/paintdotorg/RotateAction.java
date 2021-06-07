package paintdotorg;

import java.awt.event.MouseEvent;

/**
 * Used when the user is attempting to rotate a drawing (rectangle, circle, line, or image).
 * NOT IMPLEMENTED. 
 * Kevin Farragher
 *
 */
public class RotateAction {

	/**
	 * Determines the drawing (if any) selected by the user when the mouse is clicked and 
	 * the user is attempting to rotate a drawing. If multiple drawings contain the mouse point, 
	 * the most recent drawing is determined to be the selected drawing.  
	 * @param me - reference to mouse for its coordinates/point when the mouse is pressed while the user is attempting to
	 * rotate a drawing.
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public void determineSelectedDrawing(MouseEvent me, DrawingArea drawingArea) {
		
	}

	/**
	 * Rotates the selected drawing by the change in the mouse's x and y-coordinates as the mouse is dragged/released.
	 * @param me - reference to mouse for its coordinates/point when the mouse is dragged/released while the user is attempting to
	 * rotate a drawing.
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public void rotateSelectedDrawing(MouseEvent me, DrawingArea drawingArea) {

	}
}
