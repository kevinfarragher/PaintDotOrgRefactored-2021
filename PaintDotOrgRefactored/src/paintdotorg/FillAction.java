package paintdotorg;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Used when the user is attempting to fill a drawing (rectangle, circle, or line).
 * Kevin Farragher
 *
 */
public class FillAction {
	
	/**
	 * Fills the drawing (rectangle, circle, or line) that was clicked. If multiple drawings
	 * contain the mouse point, the most recent drawing is filled.  
	 * @param me - reference to mouse for its coordinates/point when the mouse is pressed while the user is attempting to
	 * fill a drawing. 
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public void fillDrawing(MouseEvent me, DrawingArea drawingArea) {
		for(int i=drawingArea.drawings.size()-1;i>=0;i--) {
			try {
				if(drawingArea.drawings.get(i) instanceof RectangleDrawing || drawingArea.drawings.get(i) instanceof CircleDrawing || drawingArea.drawings.get(i) instanceof LineDrawing) {
					if(drawingArea.drawings.get(i).checkForIntersectionWithMousePoint(me, drawingArea)==true) { //the most recent drawing having the mouse point/click is filled with the current fill color
						drawingArea.drawings.get(i).color=drawingArea.fillColor;
						drawingArea.drawings.get(i).filled=true;
						break;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
