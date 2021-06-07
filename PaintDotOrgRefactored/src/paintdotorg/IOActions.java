package paintdotorg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Contains methods for performing a vriety of I/O actions, such as saving, saving as, 
 * opening, opening recent, starting new project, or importing an image into the drawing area. 
 * @author Kevin Farragher
 *
 */
public class IOActions {
	
	//variables to store the save path and image to load into the drawing area
	private Path savePath=null;
	private File saveFile=null;
	public BufferedImage loadedImage;
	private boolean openRecent=false;
		
	//boolean to determine whether the image to be loaded into the drawing area is draggable or not
	public boolean loadedImageDraggable=true; //ADDED
		
	private DrawingArea drawingArea; //link to the drawing area
	
	/**
	 * Constructs an IOActions instance with a reference to the drawing area
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public IOActions(DrawingArea drawingArea) {
		this.drawingArea=drawingArea;
	}

	/**
	 * When saving, the user can choose where to save a JPEG file of their drawings/drawing area
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown 
	 */
    public void saveAs(DrawingArea drawingArea) {
    	
    	BufferedImage image = new BufferedImage(drawingArea.getWidth(),drawingArea.getHeight(), BufferedImage.TYPE_INT_RGB);
    	try { 
	    	JFileChooser jFile = new JFileChooser();
	        jFile.showSaveDialog(null);
	        savePath=jFile.getSelectedFile().toPath();
	        JOptionPane.showMessageDialog(null, savePath.toString());
	    	Graphics2D g2 = image.createGraphics();
			drawingArea.paint(g2);
			ImageIO.write(image, "JPEG", new File(savePath.toString()+".jpg"));
			JOptionPane.showMessageDialog(null, "The file was saved to: " +  savePath.toString(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
			saveFile=new File(savePath.toString()+".jpg");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Save path invalid/No save path chosen", "Save Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	
    }
    
  /**
   * When saving, the drawings/drawing area is saved to an existing JPEG file path. If there is no existing JPEG file path,
   * the saveAs() method is called. 
   * @param drawingArea - the program's drawing area, where the drawings are stored/shown
   */
    public void save(DrawingArea drawingArea) {
    	
    	BufferedImage image = new BufferedImage(drawingArea.getWidth(),drawingArea.getHeight(), BufferedImage.TYPE_INT_RGB);
    	if(savePath==null) { //If there is no existing JPEG file path, the saveAs() method is called
    		saveAs(drawingArea);
    	}
    	Graphics2D g2 = image.createGraphics();
		drawingArea.paint(g2);
		try{
			ImageIO.write(image, "JPEG", saveFile);
			JOptionPane.showMessageDialog(null, "The file was saved to: " +  savePath.toString(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Save path invalid/No save path chosen", "Save Error", JOptionPane.INFORMATION_MESSAGE);
		}
		
    }
    
  /**
   * When a picture file is opened, its loaded in as an image on the drawing area. The picture
   * file is added to the picture files that have been recently opened. 
   * @param drawingArea - the program's drawing area, where the drawings are stored/shown. The picture
   * file is loaded in to the drawing area as an image. 
   */
    public void open(DrawingArea drawingArea) {
    	drawingArea.drawings.clear();
    	
    	loadedImageDraggable=false;
    	drawingArea.disableAllFeatures();
    	
    	try {
	    	JFileChooser jFile = new JFileChooser();
	    	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","bmp");
	        jFile.addChoosableFileFilter(filter);
	        jFile.showOpenDialog(null);
	        Path loadPath = jFile.getSelectedFile().toPath();
			loadedImage=ImageIO.read(new File(loadPath.toString()));
			savePath=loadPath;
			saveFile=new File(loadPath.toString());
			addLoadedImageToDrawingArea();
			drawingArea.repaint();
			JOptionPane.showMessageDialog(null,"The file " + loadPath.toString() + " has been opened","Open Project", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	
    }
    
    /**
     * Opens a recent picture file opened previously. When a picture file is opened, its loaded in as an image on the drawing area. The picture
     * file is added to the picture files that have been recently opened. 
     * @param drawingArea - the program's drawing area, where the drawings are stored/shown. The picture
     * file is loaded in to the drawing area as an image. 
     * @param loadFilePath - the path of the picture file attempting to be loaded
     */
    public void openRecent(DrawingArea drawingArea,Path loadFilePath) {
    	
    	if(openRecent==false) {
    		openRecent=true;
    		return;
    	}
    	
    	drawingArea.drawings.clear();
    	
    	loadedImageDraggable=false;
    	
    	try{
			loadedImage=ImageIO.read(new File(loadFilePath.toString()));
			savePath=loadFilePath;
			saveFile=new File(loadFilePath.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	addLoadedImageToDrawingArea();
		drawingArea.repaint();
		JOptionPane.showMessageDialog(null,"The file " + loadFilePath.toString() + " has been opened","Open Project", JOptionPane.INFORMATION_MESSAGE);

    }
    
    /**
     * Starts a new project by resetting the drawing area to its default state. 
     * @param drawingArea - the program's drawing area, where the drawings are stored/shown. It is reset/set to its default state
     */
    public void newProject(DrawingArea drawingArea) {
    	
    	savePath=null;
    	saveFile=null;
    	drawingArea.drawings.clear();
    	
    	loadedImageDraggable=false;
    	
    	drawingArea.initialize();
		JOptionPane.showMessageDialog(null,"A new project has been started","New Project", JOptionPane.INFORMATION_MESSAGE);
    }
    
  /**
   * Imports a draggable image from a file and inserts it into the drawing area. 
   * @param drawingArea - the program's drawing area, where the drawings are stored/shown.
   */
    public void importImage(DrawingArea drawingArea) {    	
    	loadedImageDraggable=true;

    	try {
	    	
    		JFileChooser file = new JFileChooser();
	        
    		//filter the files to only show picture files
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","bmp");
	        file.addChoosableFileFilter(filter);
	        int result = file.showOpenDialog(null);
	        
	        //if the user clicks on a save file in Jfilechooser, the image of the file is imported and inserted into the drawing area
	        if(result == JFileChooser.APPROVE_OPTION){
	            File selectedFile = file.getSelectedFile();
	            String path = selectedFile.getAbsolutePath();
				loadedImage=ImageIO.read(new File(path));
			}     
        }catch (IOException e1) {
    		e1.printStackTrace();
    		JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
    	}   
    	addLoadedImageToDrawingArea();
		drawingArea.repaint(); 
    }
    
    /**
     * Adds a loaded image to the drawing area. 
     */
    public void addLoadedImageToDrawingArea(){
    	Drawing image;
    	if(loadedImageDraggable==true) {
    		image=new ImageDrawing(loadedImage,0,0,loadedImage.getWidth(),loadedImage.getHeight(),true, drawingArea);
    	}
    	else {
    		image=new ImageDrawing(loadedImage,0,0,loadedImage.getWidth(),loadedImage.getHeight(),false, drawingArea);
    	}
    	drawingArea.drawings.add(image);
      	drawingArea.usingPencil=true;
    }
	
    //SETTERS
    public void setSavePath(Path newSavePath) {
    	savePath=newSavePath;
    }
    
    public void setSaveFile(File newSaveFile) {
    	saveFile=newSaveFile;
    }
    
    public void setLoadedImage(BufferedImage newLoadedImage) {
    	loadedImage=newLoadedImage;
    }
    
    public void setOpenRecent(boolean newOpenRecent) {
    	openRecent=newOpenRecent;
    }
    
    public void setLoadedImageDraggable(boolean newLoadedImageDraggable) {
    	loadedImageDraggable=newLoadedImageDraggable;
    }
    
    //GETTERS

	public Path getSavePath() {
		return savePath;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public BufferedImage getLoadedImage() {
		return loadedImage;
	}

	public boolean isOpenRecent() {
		return openRecent;
	}

	public boolean isLoadedImageDraggable() {
		return loadedImageDraggable;
	}
    
}
