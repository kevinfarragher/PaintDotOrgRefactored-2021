package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Used to draw each type of drawing (rectangle, circle, line, text, path, or image).
 * @author Kevin Farragher
 *
 */
public class DrawerHelper {
	private DrawingArea drawingArea; //referecne to the drawing area
	
	/**
	 * Constructs a drawer helper with a link to the drawing area. 
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown/drawn 
	 */
	public DrawerHelper(DrawingArea drawingArea) {
		this.drawingArea=drawingArea;
	}
	
	/**
	 * Draws a rectangle given its coordinates, color, thickness, and its fill status. 
	 * @param isTemp - true if the rectangle is temporary (user is in the process of drawing a rectangle), false otherwise 
	 * @param g - Graphics instance used to draw the rectangle 
	 */
	public void drawRectangle(boolean isTemp, Graphics2D g) {
		DrawingArea da=drawingArea;
		int startX=da.startX;
		int startY=da.startY;
		int endX=da.endX;
		int endY=da.endY;
		Color fillColor=da.fillColor;
		int lineThickness=da.lineThickness;
		
		if(startX!=endX && startY!=endY) {
			if(isTemp==true) { //draws a temporary rectangle in the drawing area (user is in the process of drawing a rectangle)
				g.setColor(fillColor);
				g.setStroke(new BasicStroke(lineThickness));
				if(startX<endX && startY<endY) {
	        		g.drawRect(startX, startY, endX-startX, endY-startY);
	        	}
	        	else if(startX>endX && startY>endY) {
	        		g.drawRect(endX, endY, startX-endX, startY-endY);
	        	}
	        	else if(startX>endX && startY<endY) {
	        		g.drawRect(endX, startY, startX-endX, endY-startY);
	        	}
	        	else if(startX<endX && startY>endY) {
	        		g.drawRect(startX, endY, endX-startX, startY-endY);
	        	}
			}
			else if(isTemp==false) { //draws a permanent rectangle that is added to the drawing area's drawings
				Drawing rectangle=null;
	    		if(startX<endX && startY<endY) {
	    			rectangle=new RectangleDrawing(startX, startY, endX, endY, fillColor, lineThickness, true, false);
	    		}
	    		else if(startX>endX && startY>endY) {
	    			rectangle=new RectangleDrawing(endX, endY, startX, startY, fillColor, lineThickness, true, false);
	    		}
	    		else if(startX>endX && startY<endY) {
	    			rectangle=new RectangleDrawing(endX, startY, startX, endY, fillColor, lineThickness, true, false);
	    		}
	    		else if(startX<endX && startY>endY) {
	    			rectangle=new RectangleDrawing(startX, endY, endX, startY, fillColor, lineThickness, true, false);
	    		}
	    		da.drawings.add(rectangle);
			}
		}
	}
	
	/**
	 * Draws a circle given its coordinates, color, thickness, and its fill status. 
	 * @param isTemp - true if the circle is temporary (user is in the process of drawing a circle), false otherwise 
	 * @param g - Graphics instance used to draw the circle 
	 */
	public void drawCircle(boolean isTemp, Graphics2D g) {
		DrawingArea da=drawingArea;
		int startX=da.startX;
		int startY=da.startY;
		int endX=da.endX;
		int endY=da.endY;
		Color fillColor=da.fillColor;
		int lineThickness=da.lineThickness;
		
		if(startX!=endX && startY!=endY) {
			if(isTemp==true) { //draws a temporary circle in the drawing area (user is in the process of drawing a circle)
				g.setColor(fillColor);
				g.setStroke(new BasicStroke(lineThickness));
				if(startX<endX && startY<endY) {
	        		g.drawOval(startX, startY, endX-startX, endY-startY);
	        	}
	        	else if(startX>endX && startY>endY) {
	        		g.drawOval(endX, endY, startX-endX, startY-endY);
	        	}
	        	else if(startX>endX && startY<endY) {
	        		g.drawOval(endX, startY, startX-endX, endY-startY);
	        	}
	        	else if(startX<endX && startY>endY) {
	        		g.drawOval(startX, endY, endX-startX, startY-endY);
	        	}
			}
			else if(isTemp==false) { //draws a permanent circle that is added to the drawing area's drawings
				Drawing circle=null;
	    		if(startX<endX && startY<endY) {
	    			circle=new CircleDrawing(startX, startY, endX, endY, fillColor, lineThickness, false);
	    		}
	    		else if(startX>endX && startY>endY) {
	    			circle=new CircleDrawing(endX, endY, startX, startY, fillColor, lineThickness, false);
	    		}
	    		else if(startX>endX && startY<endY) {
	    			circle=new CircleDrawing(endX, startY, startX, endY, fillColor, lineThickness, false);
	    		}
	    		else if(startX<endX && startY>endY) {
	    			circle=new CircleDrawing(startX, endY, endX, startY, fillColor, lineThickness, false);
	    		}
	    		drawingArea.drawings.add(circle);
			}
		}
	}
	
	/**
	 * Draws a line given its coordinates, color and thickness.
	 * @param isTemp - true if the line is temporary (user is in the process of drawing a line), false otherwise 
	 * @param g - Graphics instance used to draw the line
	 */
	public void drawLine(boolean isTemp, Graphics2D g) {
		DrawingArea da=drawingArea;
		int startX=da.startX;
		int startY=da.startY;
		int endX=da.endX;
		int endY=da.endY;
		Color fillColor=da.fillColor;
		int lineThickness=da.lineThickness;
		
		if(startX!=endX && startY!=endY) {
			if(isTemp==true) { //draws a temporary line in the drawing area (user is in the process of drawing a line)
				g.setColor(fillColor);
				g.setStroke(new BasicStroke(lineThickness));
				g.drawLine(startX, startY, endX, endY);
			}
			else if(isTemp==false) { 
				Drawing line=new LineDrawing(startX,startY,endX,endY,fillColor,lineThickness); //draws a permanent line that is added to the drawing area's drawings
				da.drawings.add(line);
			}
		}
	}
	
	/**
	 * Draws a permanent path given its source, color, and thickness.
	 * The path is added to the drawing area's drawings 
	 * @param g - Graphics instance used to draw the path
	 */
	public void drawPath() {
		DrawingArea da=drawingArea;
		Drawing path=null;
		if(da.usingEraser==true) {
			path=new PathDrawing(da.generalPath,da.eraserColor,da.lineThickness);
		}
		else if(da.usingPencil==true) {
			path=new PathDrawing(da.generalPath,da.pointColor,da.lineThickness);
		}
		da.drawings.add(path);
	}
	
	/**
	 * Draws a permanent piece of text given its coordinates, color, string, name (font), 
	 * style (bold, italic, or underlined), and size. The text is added to the drawing area's
	 * drawings.
	 * @param g - Graphics instance used to draw the text
	 */
	public void drawText() {
		DrawingArea da=drawingArea;
		Drawing text=new TextDrawing(da.startX,da.startY,da.textColor,da.textToDisplay,da.textName,da.textStyle,da.textSize);
		da.drawings.add(text);
	}
	
	/**
	 * Draws a permanent image given its source, coordinates, and its draggable status.
	 * @param g - Graphics instance used to draw the image
	 */
	public void drawImage() {
		DrawingArea da=drawingArea;
		BufferedImage loadedImage=da.userIOActions.loadedImage;
		Drawing image;
    	if(da.userIOActions.loadedImageDraggable==true) {
    		image=new ImageDrawing(loadedImage,0,0,loadedImage.getWidth(),loadedImage.getHeight(),true, drawingArea);
    	}
    	else {
    		image=new ImageDrawing(loadedImage,0,0,loadedImage.getWidth(),loadedImage.getHeight(),false, drawingArea);
    	}
    	drawingArea.drawings.add(image);
	}
}
