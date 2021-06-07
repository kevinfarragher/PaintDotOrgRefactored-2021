package paintdotorg;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Contains the tools within the toolbar of the program. 
 * @author Kevin Farragher
 *
 */
public class Tools {
	
	//reference to the program's driver, frame, drawing area, and purchase manager
	private Driver driver;
	private JFrame frame; 
	private JPanel drawingArea;
	private PurchaseManager purchaseManager=new PurchaseManager(this);
	
	//toolbar's components
	public JButton saveButton;
	public JButton saveAsButton;
	public JButton pencilButton;
	public JButton eraserButton;
	public JButton fillButton;
	public JButton rectangleButton;
	public JButton circleButton;
	public JButton lineButton;
	public JSlider lineThicknessSlider;
	public JButton textButton;
	public JButton pencilColorButton;
	public JButton fillColorButton;
	public JButton textColorButton;
	public JComboBox textFontComboBox;
	public JComboBox textStyleComboBox;
	public JComboBox textSizeComboBox;
	public JButton selectButton;
	public JButton rotateButton;
	public JButton importImageButton;
	public JButton openButton;
	public JComboBox openRecentComboBox;
	public JButton exportButton;
	public JButton newProjectButton;
	public JTextField typedTextTextField;
	public JButton buyAllRemainingFeaturesButton;
	public JLabel typeTextLabel;
	public JLabel textFontLabel;
	public JLabel textStyleLabel;
	public JLabel textSizeLabel;
	
	/**
	 * Constructs a Tools instance with reference to the program's driver, frame and drawing area. 
	 * This class is responsible for initializing the toolbar's components within the program.
	 * @param driver - The program's driver, which is responsible for initializing the program
	 * @param frame - the program's frame, which contains all its components
	 * @param drawingArea - the program's drawing area, where the drawings are stored/shown
	 */
	public Tools(Driver driver, JFrame frame, JPanel drawingArea) {
		this.driver=driver;
		this.frame=frame;
		this.drawingArea=drawingArea;
		
		initializeToolbarComponents();
	}
	
	/**
	 * Intitializes the toolbar's components
	 */
	public void  initializeToolbarComponents() {
		//Combo box used to open and load recent files
		openRecentComboBox=new JComboBox();
		openRecentComboBox.setBounds(89,57,71,21);
		openRecentComboBox.setToolTipText("Load recently opened files");
		openRecentComboBox.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(openRecentComboBox);
			openRecentComboBox.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase open recent if not bought
					 if(purchaseManager.checkIfFeaturePurchased("open recent")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("open recent", openRecentComboBox, frame)==false) { //if the open recent feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 Path loadFilePath=(Path) openRecentComboBox.getSelectedItem(); //if the open recent feature is bought, the feature is executed
					 ((DrawingArea) drawingArea).userIOActions.openRecent((DrawingArea) drawingArea,loadFilePath);
				 } });	
		
		//Button to save drawings as a JPEG file
		saveAsButton=new JButton("Save As");
		saveAsButton.setBounds(12,10,71,21);
		saveAsButton.setToolTipText("Save your current drawings as a JPEG file to new file location");
		saveAsButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		frame.add(saveAsButton);
			saveAsButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){  
					 ((DrawingArea) drawingArea).userIOActions.saveAs((DrawingArea) drawingArea);
				 } });
		
		//Button to save drawings to existing file path as a JPEG.
		saveButton=new JButton("Save");
		saveButton.setBounds(12,34,71,21);
		saveButton.setToolTipText("Save your current drawings as a JPEG file to current file location");
		frame.add(saveButton);
			saveButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){   
					 ((DrawingArea) drawingArea).userIOActions.save((DrawingArea) drawingArea);
				 } });
			
		//Button to open a previous drawing from a file
		openButton=new JButton("Open");
		openButton.setBounds(12,57,71,21);
		openButton.setToolTipText("Open a previous drawing");
		openButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(openButton);
			openButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase open feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("open")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("open", openButton, frame)==false) { //if the open feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).userIOActions.open((DrawingArea) drawingArea); //if the open feature is bought, the feature is executed
					 Path filePath=((DrawingArea) drawingArea).userIOActions.getSavePath();
					 openRecentComboBox.addItem(filePath);
				 } });
		
		//Button to export drawings as a JPEG file
		exportButton=new JButton("Export");
		exportButton.setBounds(89,34,71,21);
		exportButton.setToolTipText("Export your current drawings as a JPEG file");
		exportButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(exportButton);
			exportButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase export feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("export")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("export", exportButton, frame)==false) { //if the export feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).userIOActions.saveAs((DrawingArea) drawingArea); //if the export feature is purchased, the feature is executed
				 } });
			
		//Button to start a new project
		newProjectButton=new JButton("New Project");
		newProjectButton.setBounds(89,10,71,21);
		newProjectButton.setToolTipText("Start a new project");
		newProjectButton.setFont(new Font("Dialog", Font.PLAIN, 6));
		newProjectButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(newProjectButton);
			newProjectButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase new project feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("new project")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("new project", newProjectButton, frame)==false) { //if the new project feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).userIOActions.newProject((DrawingArea) drawingArea); //if the new project feature is purchased, the feature is executed
				 } });
		
		//Button to enable drawing in pencil
		pencilButton=new JButton("Pencil");
		pencilButton.setBounds(164,10,58,34);
		pencilButton.setToolTipText("Enable drawing with a pencil");
		pencilButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		frame.add(pencilButton);
			pencilButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){  
					((DrawingArea) drawingArea).drawPencil();
				 } });
		
		//Button to enable erasing
		eraserButton=new JButton("Eraser");
		eraserButton.setBounds(164,45,58,34);
		eraserButton.setToolTipText("Enable erasing");
		eraserButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		eraserButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(eraserButton);
			eraserButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					 //popup to purchase eraser feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("eraser")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("eraser", eraserButton, frame)==false) { //if the eraser feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					((DrawingArea) drawingArea).drawEraser(); //if the eraser feature is purchased, the feature is executed
				 } });
		
		//Button to enable filling on any clicked shape.
		fillButton=new JButton("Fill");
		fillButton.setBounds(228,10,58,34);
		fillButton.setToolTipText("Enable filling a clicked shape");
		fillButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		fillButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(fillButton);
			fillButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase fill feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("fill")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("fill", fillButton, frame)==false) { //if the fill feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
						((DrawingArea) drawingArea).drawFill(); //if the fill feature is purchased, the feature is executed
				 } });	
		
		//Button for drawing a rectangle shape.
		rectangleButton=new JButton("Rectangle");
		rectangleButton.setBounds(292,10,64,21);
		rectangleButton.setToolTipText("Draw a rectangle on the screen");
		rectangleButton.setFont(new Font("Dialog", Font.PLAIN, 6));
		rectangleButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(rectangleButton);
			rectangleButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase rectangle feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("rectangle")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("rectangle", rectangleButton, frame)==false) { //if the rectangle feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
						((DrawingArea) drawingArea).drawRectangle(); //if the rectangle feature has been purchased, the feature is executed
	
				 } });
		
		//Button for drawing a circle shape.
		circleButton=new JButton("Circle");
		circleButton.setBounds(292,34,64,21);
		circleButton.setToolTipText("Draw a circle on the screen");
		circleButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		circleButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(circleButton);
			circleButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase circle feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("circle")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("circle", circleButton, frame)==false) { //if the circle feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
						((DrawingArea) drawingArea).drawCircle(); //if the circle feature has been purchased, the feature is executed
				 } });	  
		
		//Button for drawing a line shape
		lineButton=new JButton("Line");
		lineButton.setBounds(292,57,64,21);
		lineButton.setToolTipText("Draw a line on the screen");
		lineButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		lineButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(lineButton);
			lineButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase line feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("line")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("line", lineButton, frame)==false) { //if the line feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
						((DrawingArea) drawingArea).drawLine(); //if the line feature is purcahsed, the feature is executed
				 } });
		
		//Slider to change line thickness when drawing, erasing, and for shapes
		lineThicknessSlider=new JSlider(JSlider.VERTICAL,1,10,5);
		lineThicknessSlider.setBounds(359,10,18,67);
		lineThicknessSlider.setToolTipText("Set line thickness for drawing in pencil, erasing, and shape borders");
		lineThicknessSlider.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(lineThicknessSlider);
			ChangeListener lineThicknessChangeListener=new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					//popup to purchase line thickness featue if not bought
					  if(purchaseManager.checkIfFeaturePurchased("line thickness")==false) {
							 if(purchaseManager.attemptToMakeFeaturePurchase("line thickness", lineThicknessSlider, frame)==false) { //if the line thickness feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
								 return;
							 }
						 }
					  JSlider source = (JSlider)e.getSource(); //if the line thickness feature is purchased, the feature is executed
					  int newThickness = (int)source.getValue();
				        if (!source.getValueIsAdjusting()) {
				        	//((DrawingArea) drawingArea).setLineThickness(newThickness);
				        	((DrawingArea) drawingArea).lineThickness=newThickness;
				        }
				  }
			  };
	    lineThicknessSlider.addChangeListener(lineThicknessChangeListener);
		
		typeTextLabel=new JLabel("Type text:");
		typeTextLabel.setBounds(380,13,58,13);
		frame.add(typeTextLabel);
		
		//Textfield to type desired text to display.
		typedTextTextField=new JTextField("default");
		typedTextTextField.setEnabled(false);
		typedTextTextField.setBounds(441,10,118,20);
		typedTextTextField.setToolTipText("Type desired text to display");
		frame.add(typedTextTextField);
		
		//Button to enable putting text on screen when clicked
		textButton=new JButton("Text");
		textButton.setBounds(228,45,58,34);
		textButton.setToolTipText("Enable drawing desired text on screen where clicked ");
		textButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		textButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textButton);
			textButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase text feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("text")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("text", textButton, frame)==false) { //if the text feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).drawText(); //if the text feature is purchased, the feature is executed
					 String newText=typedTextTextField.getText();
					 if(newText==null) {
						 newText="default";
						 typedTextTextField.setText("default");
					 } 
					// ((DrawingArea) drawingArea).getTexts().setTextToDisplay(newText); 
					 ((DrawingArea) drawingArea).textToDisplay=newText; 
				 } });
		
		//Button to choose color to draw in with pencil
		pencilColorButton=new JButton("<html>Pencil<br />Color</html>");
		pencilColorButton.setBounds(380,34,58,40);
		pencilColorButton.setToolTipText("Choose the pencil's color");
		pencilColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(pencilColorButton);
			pencilColorButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){
					//popup to purchase pencil color feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("pencil color")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("color", pencilColorButton, frame)==false) { //if the pencil color feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 Color newColor=JColorChooser.showDialog(null, "Stroke Color Chooser", Color.black); //if the pencil color feature is purchased, the feature is executed
					 ((DrawingArea) drawingArea).pointColor=newColor;
					 pencilButton.doClick();
				 } });  
		
		//Button to choose fill color for shape when clicked on.
		fillColorButton=new JButton("<html>Fill<br />Color</html>");
		fillColorButton.setBounds(441,34,58,40);
		fillColorButton.setToolTipText("Choose the color for the fill of a clicked shape");
		fillColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(fillColorButton);
			fillColorButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase fill color feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("fill color")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("fill color", fillColorButton, frame)==false) { //if the fill color feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 Color newColor=JColorChooser.showDialog(null, "Fill Color Chooser", Color.black); //if the fill color feature is bought, the feature is executed
					 ((DrawingArea) drawingArea).fillColor=newColor;
					 fillButton.doClick();
				 } });	
		
		//Button to choose text color
		textColorButton=new JButton("<html>Text<br />Color</html>");
		textColorButton.setBounds(501,34,58,40);
		textColorButton.setToolTipText("Choose the color to display text in");
		textColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textColorButton);
			textColorButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase text color feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("text color")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("text color", textColorButton, frame)==false) { //if the text color feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 Color newTextColor=JColorChooser.showDialog(null, "Text Color Chooser", Color.black); //if the text color feature is purchased, the feature is executed 
					 ((DrawingArea) drawingArea).textColor=newTextColor;
					 textButton.doClick();
				 } });
		
		textFontLabel=new JLabel("Text font:");
		textFontLabel.setBounds(565,15,57,13);
		frame.add(textFontLabel);
		
		//Combo box to select text font
		textFontComboBox=new JComboBox();
		String fonts[] = 
			    GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			    for ( int i = 0; i < fonts.length; i++ )
			    {
			      textFontComboBox.addItem(fonts[i]);
			    }
		textFontComboBox.setBounds(625,10,121,21);
		textFontComboBox.setToolTipText("Select desired text font");
		textFontComboBox.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textFontComboBox);
			textFontComboBox.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if text font feature not bought
					 if(purchaseManager.checkIfFeaturePurchased("text font")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("text font", textFontComboBox, frame)==false) { //if the text font feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 String newTextFont=(String)textFontComboBox.getSelectedItem(); //if the text font feature is purchased, the feature is executed
					 if(newTextFont==null) {
						 newTextFont="Times New Roman";
					 } 
					 ((DrawingArea) drawingArea).textName=newTextFont;
				 } });
		
		textStyleLabel=new JLabel("Text style:");
		textStyleLabel.setBounds(565,38,60,13);
		frame.add(textStyleLabel);
		
		//Combo box to select text style
		textStyleComboBox=new JComboBox();
		String[]fontStyles= {"0","1","2"};
		for ( int i = 0; i < fontStyles.length; i++ )
	    {
	      textStyleComboBox.addItem(fontStyles[i]);
	    }
		
		textStyleComboBox.setBounds(625,34,121,21);
		textStyleComboBox.setToolTipText("Select desired text style. 0:Plain; 1:Bold; 2:Italic");
		textStyleComboBox.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textStyleComboBox);
		textStyleComboBox.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){
				//popup to purchase text style feature if not bought
				 if(purchaseManager.checkIfFeaturePurchased("text style")==false) {
					 if(purchaseManager.attemptToMakeFeaturePurchase("text style", textStyleComboBox, frame)==false) { //if the text style feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
						 return;
					 }
				 }
				 int newTextStyle=Integer.parseInt((String) textStyleComboBox.getSelectedItem()); //if the text style feature is purchased, the feature is executed
				 ((DrawingArea) drawingArea).textStyle=newTextStyle; 
			 } });	
		
		textSizeLabel=new JLabel("Text size:");
		textSizeLabel.setBounds(565,61,57,13);
		frame.add(textSizeLabel);
		
		//Combo box to select text size
		textSizeComboBox=new JComboBox();
			textSizeComboBox.addItem("8");
			textSizeComboBox.addItem("9");
			textSizeComboBox.addItem("10");
			textSizeComboBox.addItem("11");
			textSizeComboBox.addItem("12");
			textSizeComboBox.addItem("14");
			textSizeComboBox.addItem("16");
			textSizeComboBox.addItem("18");
			textSizeComboBox.addItem("20");
			textSizeComboBox.addItem("22");
			textSizeComboBox.addItem("24");
			textSizeComboBox.addItem("26");
			textSizeComboBox.addItem("28");
			textSizeComboBox.addItem("36");
			textSizeComboBox.addItem("48");
			textSizeComboBox.addItem("72");
		textSizeComboBox.setBounds(625,58,121,21);
		textSizeComboBox.setToolTipText("Select desired text size ");
		textSizeComboBox.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textSizeComboBox);
			textSizeComboBox.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase text size feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("text size")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("text size", textSizeComboBox, frame)==false) { //if the text size feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 int newTextSize=Integer.parseInt((String) textSizeComboBox.getSelectedItem()); //if the text size feature is purchased, the feature is executed
					 ((DrawingArea) drawingArea).textSize=newTextSize;
				 } });	
			
		//Button for selecting a shape to move around the screen
		selectButton=new JButton("Select");
		selectButton.setBounds(752,10,64,21);
		selectButton.setToolTipText("Move a shape or image around the screen");
		selectButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		selectButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectButton);
			selectButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase select feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("select")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("select", selectButton, frame)==false) { //if the select feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).drawSelect(); //if the select feature has been purchased, the feature is executed
				 } });
			
		//Button to rotate clicked shape, image, or text
		rotateButton=new JButton("Rotate");
		rotateButton.setBounds(752,34,64,21);
		rotateButton.setToolTipText("Click a drawn shape or image to rotate it");
		rotateButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		rotateButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(rotateButton);
			rotateButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if rotate feature not bought
					 if(purchaseManager.checkIfFeaturePurchased("rotate")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("rotate", rotateButton, frame)==false) { //if the rotate feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 /*if(purchaseTracker.isRotateBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the rotate feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. Howevever, the rotate feature is not yet implemented. You just got scammed!","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setRotateBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				rotateButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}else {
		    				JOptionPane.showMessageDialog(frame,"The rotate feature is not yet implemented. You were scammed!","Can't Rotate", JOptionPane.INFORMATION_MESSAGE);
						}*/
					 //if rotate feature is purchased, the feature is executed
				 } });
		
		//Button to insert image from file into drawing
		importImageButton=new JButton("<html>Import/Choose<br />Image</html>");
		importImageButton.setBounds(752,57,64,21);
		importImageButton.setToolTipText("Choose an image from file to insert into your drawing");
		importImageButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		importImageButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(importImageButton);
			importImageButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase import image feature if not bought
					 if(purchaseManager.checkIfFeaturePurchased("import image")==false) {
						 if(purchaseManager.attemptToMakeFeaturePurchase("import image", importImageButton, frame)==false) { //if the import image feature hasn't been purchased and the user's attempt to purchase the feature fails, the feature isn't executed
							 return;
						 }
					 }
					 ((DrawingArea) drawingArea).userIOActions.importImage((DrawingArea) drawingArea); //if the import image feature is purchased, the feature is executed
				 } }); 
		
		//Button to buy all the remaining purchasable features
		buyAllRemainingFeaturesButton =new JButton("<html> Buy all </br> remaining </br> features </html>");
		buyAllRemainingFeaturesButton.setBounds(821,10,80,68);
		buyAllRemainingFeaturesButton.setToolTipText("Pay to unlock all features");
	    frame.add(buyAllRemainingFeaturesButton);
	    
	    buyAllRemainingFeaturesButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent actionEvent) {
	    		//popup to purchase all remaining features 
	    		purchaseManager.purchaseRemainingFeatures(driver, frame);
	    		}
	   });		
	}
}
