package paintdotorg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Used to create a piece of text and draw the text.
 * @author Kevin Farragher
 */
public class TextDrawing extends Drawing {
	
	/**
	 * Text's string, name (font), style (bold, italic, or underlined), and size
	 */
	private String textToDisplay;
	private String name;
	private int style;
	private int size;
	
	/**
	 * Creates a piece of text given its coordinates, color, 
	 * string, name (font), style (bold, italic, or underlined), and size. 
	 * @param startX - the text's starting x-coordinate
	 * @param startY - the text's starting y-coordinate
	 * @param color - the text's color
	 * @param textToDisplay - the text's string
	 * @param name - the text's name (font)
	 * @param style - the text's style (bold, italic, or underlined)
	 * @param size - the text's size
	 */
	public TextDrawing(int startX, int startY, Color color,String textToDisplay, String name, int style, int size) {
		this.startX=startX;
		this.startY=startY;
		this.color=color;
		this.textToDisplay=textToDisplay;
		this.name=name;
		this.style=style;
		this.size=size;
		//this.drawingNumber=drawingNumber;
	}
	
	/**
	 * Draws the piece of text.
	 * @param g - Graphics object used to draw the piece of text.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color); 
		Font textFontLocal=new Font(name,style,size);
		g.setFont(textFontLocal);
		g.drawString(textToDisplay, startX, startY);
	}
	
	/**
	 * Not used. Would somehow check if a piece of text intersects the mouse's point.
	 * @param me - reference to mouse for its coordinates/point when a mouse event is triggered (either 
	 * when the mouse is pressed, dragged, or released)
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 * @return - true if the text intersects the mouse point, false otherwise
	 */
	@Override
	public boolean checkForIntersectionWithMousePoint(MouseEvent me, DrawingArea drawingArea) {
		return false;
	}
}
