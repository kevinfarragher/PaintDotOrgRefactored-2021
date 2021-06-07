package paintdotorg;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Used when the user is attempting to select a drawing (rectangle, circle, line, or image) and move it.
 * Kevin Farragher
 *
 */
public class SelectAction{
	
	private Drawing drawingSelected=null; //stores the selected drawing
	
	/**
	 * Determines the drawing (if any) selected by the user when the mouse is clicked and 
	 * the user is attempting to select a drawing. If multiple drawings contain the mouse point, 
	 * the most recent drawing is determined to be the selected drawing.  
	 * @param me - reference to mouse for its coordinates/point when the mouse is pressed while the user is attempting to
	 * select a drawing.
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public void determineSelectedDrawing(MouseEvent me, DrawingArea drawingArea) {
		drawingSelected=null;
		for(int i=drawingArea.drawings.size()-1;i>=0;i--) {
			try {
				if(drawingArea.drawings.get(i) instanceof RectangleDrawing || drawingArea.drawings.get(i) instanceof CircleDrawing 
						|| drawingArea.drawings.get(i) instanceof LineDrawing || drawingArea.drawings.get(i) instanceof ImageDrawing) {
					if(drawingArea.drawings.get(i).checkForIntersectionWithMousePoint(me, drawingArea)==true) { //the most recent drawing having the mouse point/click is determined to be the drawing selected for moving
						drawingSelected=drawingArea.drawings.get(i);
						break;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Moves the selected drawing by the change in the mouse's x and y-coordinates as the mouse is dragged/released.
	 * @param me - reference to mouse for its coordinates/point when the mouse is dragged/released while the user is attempting to
	 * select a drawing.
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public void moveSelectedDrawing(MouseEvent me, DrawingArea drawingArea) {
		drawingArea.endX=me.getX();
		drawingArea.endY=me.getY();
 		int changeX=drawingArea.endX-drawingArea.startX;
 		int changeY=drawingArea.endY-drawingArea.startY;
 		
 		try {
	 		drawingSelected.startX+=changeX;
	 		drawingSelected.endX+=changeX;
	 		drawingSelected.startY+=changeY;
	 		drawingSelected.endY+=changeY;
 		}catch(Exception e){}
 		
 		drawingArea.startX=drawingArea.endX;
		drawingArea.startY=drawingArea.endY;
	}
}
