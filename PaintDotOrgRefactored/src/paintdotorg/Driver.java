package paintdotorg;

import java.util.HashMap;
import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Initializes the program with its main components. 
 * @author Kevin Farragher
 */

public class Driver {
	
	//The program's main components
	private Driver driver;
	private JFrame frame;
	private JPanel drawingArea;
	private JPanel background;
	private JPanel toolBar;
	private Tools tools;

	public static void main(String args[]) {
		new Driver();
	}
		
	public Driver(){
		this.driver=this;
		
		//creates frame
		JFrame frame=new JFrame();
		frame.setBounds(0, 0, 940, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creates drawing area
		drawingArea=new DrawingArea();
		drawingArea.setBounds(10,100,900,410);
		drawingArea.setBackground(Color.WHITE);
		
		//creates background
		background=new JPanel();
		background.setBounds(0,0,750,550);
		background.setBackground(Color.DARK_GRAY);
		
		//creates tools area
		toolBar=new JPanel();
		toolBar.setBounds(0,0,940,90); 
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		tools=new Tools(this,frame,drawingArea);

		frame.add(drawingArea);
		frame.add(toolBar);
		frame.add(background);
		
		frame.setVisible(true);
		frame.setTitle("PaintDotOrg");
		background.setVisible(true);
		toolBar.setVisible(true);
		drawingArea.setVisible(true);
	}

}