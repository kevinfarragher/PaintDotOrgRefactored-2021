package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The program's drawing area, containing variables related to its status, the mouse coordinates, and 
 * the different types of drawings, and methods related to its initialization, drawing its current drawings, 
 * handling mouse events (when the mouse  is pressed, dragged, or released), and updating its current state 
 * (the current/selected feature).
 * @author Kevin Farragher
 *
 */
public class DrawingArea extends JPanel implements MouseListener, MouseMotionListener{

	//variables to store the mouse's start and end coordinates when performing certain actions
	public int startX=0;
	public int startY=0;
	public int endX=0;
	public int endY=0;

	public int lineThickness=1; //line thickness when drawing, erasing, and for the thickness of shape's borders

	//store's current fill, point, and eraser color
	public Color fillColor=Color.BLACK; 
	public Color pointColor=Color.BLACK; 
	public Color eraserColor=Color.WHITE;	

	//variables to store current text settings upon creation of pieces of text 
	//(text to display and its name, style, size, and color)
	public String textToDisplay="default";
	public String textName="Arial";
	public int textStyle=Font.PLAIN;
	public int textSize=12;
	public Color textColor=Color.BLACK;

	public ArrayList<Drawing>drawings=new ArrayList<Drawing>(); //stores drawing's area current drawings

	//instances used when the user is attempting to fill a shape or select and move a drawing
	private FillAction fill=new FillAction();
	private SelectAction select=new SelectAction();

	//instance used for performing I/O actions 
	//(saving, saving as, opening, opening recent, starting new project, or importing an image)
	public IOActions userIOActions=new IOActions(this);

	//instance used to help draw drawings (rectangles, circles, lines, pieces of text, paths, or images)
	private DrawerHelper drawerHelper=new DrawerHelper(this);

	//variable to keep track of whether mouse is being dragged or not on the drawing area
	private boolean dragging=false; 

	//variables to keep track of current feature selected by user 
	public boolean drawingRectangle=false;
	public boolean drawingCircle=false;
	public boolean drawingLine=false;
	public boolean usingPencil=false; 
	public boolean usingEraser=false;
	public boolean drawingText=false;
	public boolean filling=false;
	public boolean selecting=false;

	//variable used for path drawing
	//For some reason, without this, the program is laggy when using the pencil or eraser feature
	public GeneralPath p=new GeneralPath();

	/**
	 * Creates a drawing area, adding its mouse and mouse motion listeners and initializing it. 
	 */
	public DrawingArea() {
		// mouse interaction:
		addMouseListener(this);
		addMouseMotionListener(this);

		//When program is initiated, a white rectangle is drawn that covers the drawing area for background.
		//The pencil feature is enabled by default.
		initialize();
	}

	/**
	 * Draws the drawing's current drawings and draws temporary shapes while they are being
	 * drawn by the user. 
	 * @param gfx - Graphics instance used to draw the drawing area's drawings and temporary shapes
	 */
	@Override
	public void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		Graphics2D g = (Graphics2D) gfx;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		try { //for some reason, wihtout this try catch block, errors will occur 
			drawDrawings(g); //draws the drawing's current drawings
		}catch(Exception e){
			e.printStackTrace();
		}

		if(usingPencil==true || usingEraser==true) { //without this, the program is laggy when using the pencil or eraser feature for some odd reason
			g.draw(p);
		}

		if(drawingRectangle==true) { //draws a temporary rectangle if the user is currently in the process of drawing a rectangle
			drawerHelper.drawRectangle(true, g);
		}
		else if(drawingCircle==true) { //draws a temporary circle if the user is currently in the process of drawing a circle
			drawerHelper.drawCircle(true, g);
		}
		else if(drawingLine==true) { //draws a temporary line if the user is currently in the process of drawing a line
			drawerHelper.drawLine(true, g);
		}
	}

	/**
	 * When the mouse is pressed, the appropriate event is performed based on the current selected action by the user.
	 * @param me - reference to mouse for its coordinates/point when the mouse is pressed. 
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		//starting mouse coordinates and dragging status are updated
		startX=me.getX(); 
		startY=me.getY(); 
		dragging=false;

		if(usingPencil==true || usingEraser==true){ //when the mouse is clicked and user is drawing with pencil or erasing, a path is drawn
			drawerHelper.drawPath();
			setPath(new GeneralPath());
			p.moveTo(me.getX(), me.getY());
			repaint();
		}
		else if(drawingText==true){ //when the mouse is clicked and user is drawing text, a piece of text is drawn with the current text settings
			drawerHelper.drawText();
			repaint();
		}
		else if(filling==true) { //when the mouse is clicked and user is attempting to fill a drawing with a color, the clicked drawing (if one is clicked) is filled
			fill.fillDrawing(me, this); 
			repaint();
		}
		else if(selecting==true) { //when the mouse is clicked and user is attempting to select a drawing, the drawing selected (if any) is determined 
			select.determineSelectedDrawing(me, this); 
			repaint();
		}
	}

	/**
	 * When the mouse is dragged, the appropriate event is performed based on the current selected action by the user.
	 * @param me - reference to mouse for its coordinates/point when the mouse is dragged. 
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		//ending mouse coordinates and dragging status are updated
		endX=me.getX();
		endY=me.getY();
		dragging=true; 

		if(usingPencil==true || usingEraser==true){ //when the mouse is being dragged and the user is drawing with pencil or erasing, a conneciton (line) is made to each path?
			p.lineTo(me.getX(), me.getY());
		}
		else if(selecting==true) { //when mouse is being dragged and the user is selecting a drawing, the selected drawing is moved
			select.moveSelectedDrawing(me, this);
		}
		repaint();
	}

	/**
	 * When the mouse is released, the appropriate event is performed based on the current selected action by the user.
	 * @param me - reference to mouse for its coordinates/point when the mouse is released. 
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		dragging=false;
		endX=me.getX();
		endY=me.getY();

		//when mouse is released and the user is drawing with a pencil or erasing, a path is drawn
		if(usingPencil==true || usingEraser==true){
			drawerHelper.drawPath();
			setPath(new GeneralPath());
			p.moveTo(me.getX(), me.getY());
		}
		else if(drawingRectangle==true){ //when the mouse is released and the user is drawing a rectangle, a rectangle is drawn
			drawerHelper.drawRectangle(false, null);
		}
		else if(drawingCircle==true){ //when the mouse is released and the user is drawing a circle, a circle is drawn
			drawerHelper.drawCircle(false, null);
		}
		else if(drawingLine==true){ //when the mouse is released and the user is drawing a line, a line is drawn
			drawerHelper.drawLine(false, null);
		}
		else if(selecting==true) { //when the mouse is released and user is selecting a drawing, the selected drawing is finished moving
			select.moveSelectedDrawing(me, this);
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent me) {}
	@Override
	public void mouseEntered(MouseEvent me) {}
	@Override
	public void mouseExited(MouseEvent me) {}
	@Override
	public void mouseMoved(MouseEvent me) {}

	/**
	 * Initializes/resets the drawing screen when the program is booted up or a new project is created.
	 * A white rectangle is drawn that covers the drawing area for background. The pencil feature is enabled.
	 */
	public void initialize() {
		disableAllFeatures();
		Drawing rectangle=new RectangleDrawing(0, 0, getWidth(), getHeight(), Color.WHITE, 1, false, true);
		drawings.add(rectangle);
		usingPencil=true;
		repaint();
	}

	/**
	 * Draws existing drawings on the drawing area. 
	 * @param g - Graphics component used to draw existing drawings on drawing area. 
	 */
	public void drawDrawings(Graphics2D g) {
		for(int j=0;j<drawings.size();j++) {
			drawings.get(j).draw(g);
		}
	}

	/**
	 * Disables all features. 
	 */
	public void disableAllFeatures() {
		drawingRectangle=false; 
		drawingCircle=false;
		drawingLine=false;
		usingPencil=false;
		usingEraser=false;
		drawingText=false;
		filling=false;
		selecting=false;
	}

	/**
	 * Enables the feature for drawing with pencil and disables everything else.
	 */
	public void drawPencil() { 
		disableAllFeatures();
		usingPencil=true;
	}

	/**
	 * Enables the feature for erasing and disables everything else.
	 */
	public void drawEraser() { 
		disableAllFeatures();
		usingEraser=true;
	}

	/**
	 * Enables the feature for drawing a rectangle and disables everything else. 
	 */
	public void drawRectangle() { 
		disableAllFeatures();
		drawingRectangle=true; 
	}

	/**
	 * Enables the feature for drawing a circle and disables everything else.
	 */
	public void drawCircle() { 
		disableAllFeatures();
		drawingCircle=true;
	}

	/**
	 * Enables the feature for drawing a line and disables everything else. 
	 */
	public void drawLine() {
		disableAllFeatures();
		drawingLine=true;
	}

	/**
	 * Enables the feature for drawing text and disables everything else.
	 */
	public void drawText() {
		disableAllFeatures();
		drawingText=true;
	}

	/**
	 * Enables the feature for filling clicked shapes and disables everything else.
	 */
	public void drawFill() {
		disableAllFeatures();
		filling=true;
	}

	/**
	 * Enables the feature for selecting a shape to move and disables everything else.
	 */
	public void drawSelect() {
		disableAllFeatures();
		selecting=true;
	}

	/**
	 * Adds a new path when drawing with the pencil or eraser. 
	 * @param newPath - the new path added when drawing with the pencil or eraser.  
	 */
	public void setPath(GeneralPath newPath) {
		p=newPath;
	}

}