package paintdotorg;

import java.awt.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * Used to keep track of features purchased and purchase new features. 
 * @author Kevin Farragher
 *
 */
public class PurchaseManager {
	
	    //store the cost to purchase all features and the number of features still available to purchase
		public int costToPurchaseAllFeatures=300;
		public int featuresToPurchase=20;
		
		//Hashmaps to store each feature and their purchase status, cost, and component's border once purchased
		public HashMap<String, Boolean> featureAndPurchaseStatus = new HashMap<String, Boolean>();
		public HashMap<String, Integer> featureAndCost = new HashMap<String, Integer>();
		public HashMap<String, Border> featureAndComponentBorderAfterPurchase = new HashMap<String, Border>();
		
		private Tools tools; //reference to the program's tools
		
		/**
		 * Constructs a purchase manager with a reference to the program's tools
		 * @param tools - the program's tools
		 */
		public PurchaseManager(Tools tools) {
			this.tools=tools;
			initializeFeatureAndPurchaseStatusHashMap();
			initializeFeatureAndCostHashMap();
			initializeFeatureAndComponentBorderAfterPurchaseHashMap();
		}
		
		/**
		 * Initializes the hashmap for storing each feature and their purchase status.
		 */
		public void initializeFeatureAndPurchaseStatusHashMap() {
			featureAndPurchaseStatus.put("save", null);
			featureAndPurchaseStatus.put("save as", null);
			featureAndPurchaseStatus.put("open", false);
			featureAndPurchaseStatus.put("open recent", false);
			featureAndPurchaseStatus.put("export", false);
			featureAndPurchaseStatus.put("new project", false);
			featureAndPurchaseStatus.put("pencil", null);
			featureAndPurchaseStatus.put("eraser", false);
			featureAndPurchaseStatus.put("line thickness", false);
			featureAndPurchaseStatus.put("rectangle", false);
			featureAndPurchaseStatus.put("line", false);
			featureAndPurchaseStatus.put("circle", false);
			featureAndPurchaseStatus.put("text", false);
			featureAndPurchaseStatus.put("text string", null);
			featureAndPurchaseStatus.put("text font", false);
			featureAndPurchaseStatus.put("text color", false);
			featureAndPurchaseStatus.put("text style", false);
			featureAndPurchaseStatus.put("text size", false);
			featureAndPurchaseStatus.put("pencil color", false);
			featureAndPurchaseStatus.put("fill color", false);
			featureAndPurchaseStatus.put("select", false);
			featureAndPurchaseStatus.put("fill", false);
			featureAndPurchaseStatus.put("import image", false);
			featureAndPurchaseStatus.put("rotate", false);
		}
		
		/**
		 * Initializes the hashmap for storing each feature and their cost.
		 */
		public void initializeFeatureAndCostHashMap() {
			featureAndCost.put("save", null);
			featureAndCost.put("save as", null);
			featureAndCost.put("open", 5);
			featureAndCost.put("open recent", 5);
			featureAndCost.put("export", 5);
			featureAndCost.put("new project", 5);
			featureAndCost.put("pencil", null);
			featureAndCost.put("eraser", 25);
			featureAndCost.put("line thickness", 15);
			featureAndCost.put("rectangle", 20);
			featureAndCost.put("line", 20);
			featureAndCost.put("circle", 20);
			featureAndCost.put("text", 25);
			featureAndCost.put("text string", null);
			featureAndCost.put("text font", 10);
			featureAndCost.put("text color", 15);
			featureAndCost.put("text style", 10);
			featureAndCost.put("text size", 10);
			featureAndCost.put("pencil color", 15);
			featureAndCost.put("fill color", 15);
			featureAndCost.put("select", 25);
			featureAndCost.put("fill", 25);
			featureAndCost.put("import image", 5);
			featureAndCost.put("rotate", 25);
		}
		
		/**
		 * Initializes the hashmap for storing each feature and their 
		 * component's border once purchased.
		 */
		public void initializeFeatureAndComponentBorderAfterPurchaseHashMap() {
			featureAndComponentBorderAfterPurchase.put("save", null);
			featureAndComponentBorderAfterPurchase.put("save as", null);
			featureAndComponentBorderAfterPurchase.put("open", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("open recent", BorderFactory.createEmptyBorder());
			featureAndComponentBorderAfterPurchase.put("export", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("new project", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("pencil", null);
			featureAndComponentBorderAfterPurchase.put("eraser", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("line thickness", UIManager.getBorder("JSlider.border"));
			featureAndComponentBorderAfterPurchase.put("rectangle", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("line", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("circle", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("text", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("text string", null);
			featureAndComponentBorderAfterPurchase.put("text font", BorderFactory.createEmptyBorder());
			featureAndComponentBorderAfterPurchase.put("text color", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("text style", BorderFactory.createEmptyBorder());
			featureAndComponentBorderAfterPurchase.put("text size", BorderFactory.createEmptyBorder());
			featureAndComponentBorderAfterPurchase.put("pencil color", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("fill color", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("select", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("fill", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("import image", UIManager.getBorder("Button.border"));
			featureAndComponentBorderAfterPurchase.put("rotate", UIManager.getBorder("Button.border"));
		}
		
		/**
		 * Checks if a feature has been purchased by the user. 
		 * @param feature - feature being checked if it has been purchased by the user. 
		 * @return - true if the feature has been purchased, false otherwise
		 */
		public boolean checkIfFeaturePurchased(String feature) {
			if(featureAndPurchaseStatus.get(feature)==false) {
				return false;
			}
			return true;
		}
		
		/**
		 * When the user attempts to purchase a feature, a dialog box appears that allows
		 * the user to enter their credit card number. If the credit card number is valid,
		 * the feature is successfully purchased for its set price.
		 * @param feature - the feature attempted to be purchased
		 * @param component - the JComponent the feature is linked to. If a feature is purchased
		 * successfully, its component's border is changed to no longer have a red border. 
		 * @param frame - the program's frame
		 * @return - true if the feature is successfully purchased, false otherwise
		 */
		public boolean attemptToMakeFeaturePurchase(String feature, JComponent component, JFrame frame) {
				//a dialog box is displayed allowing the user to enter their credit number to purchase the feature
				try {
					String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the " + feature + " feature purchased. "
							+ "The price to unlock the feature is $" + featureAndCost.get(feature) + ". If you wish to buy the feature, please enter your credit card number below:",null);
					if(luhnCheck(creditCardNumber)) //if the credit card number entered is valid, the feature is purchased
	    			{
	    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the open recent feature.","Purchase Successful",
	    						JOptionPane.INFORMATION_MESSAGE);
	    				featureAndPurchaseStatus.put(feature, true);
	    				costToPurchaseAllFeatures-=featureAndCost.get(feature);
	    				featuresToPurchase--;
	    				component.setBorder(featureAndComponentBorderAfterPurchase.get(feature));
	    				return true;
	    			}
					else { //if the credit card number entered is valid, an error message is displayed
						JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
						return false;
				    }
				}catch(Exception ef){
					JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
	    			return false;
				}
		}
		
		/**
		 * Attempts to purchase remaining features (if there are any remaining features to purchase).
		 */
		public void purchaseRemainingFeatures(Driver driver, JFrame frame) {
			Collection purchaseStatusValues=featureAndPurchaseStatus.values();
			System.out.println(purchaseStatusValues);
			if(purchaseStatusValues.contains(false)) { //if a feature hasn't been purchased, a dialog box is displayed allowing the user to enter their credit number to purchase the rest of the features
				try {
					String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have " + featuresToPurchase +" features purchased. The price to unlock the remaining features is $" + costToPurchaseAllFeatures + ". If you wish to buy the remaining features, please enter your credit card number below:",null);
					if(luhnCheck(creditCardNumber)) //if the credit card number is valid, the remaining features are purchased
	    			{
	    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to all of the features.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
	    				
	    				Object[] keys=featureAndPurchaseStatus.keySet().toArray();
	    				for(int i=0;i<keys.length;i++) { //sets the status of each feature's purchase to true
	    					if((String)keys[i]!=null) {
	    						featureAndPurchaseStatus.put((String)keys[i], true);
	    					}
	    				}
	    				costToPurchaseAllFeatures=0;
	    				featuresToPurchase=0;
	    		
	    				tools.eraserButton.setBorder(UIManager.getBorder("Button.border")); //updates the border of each feature's component
	    				tools.fillButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.rectangleButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.circleButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.lineButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.lineThicknessSlider.setBorder(UIManager.getBorder("Button.border"));
	    				tools.textButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.pencilColorButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.fillColorButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.textColorButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.textFontComboBox.setBorder(BorderFactory.createEmptyBorder());
	    				tools.textStyleComboBox.setBorder(BorderFactory.createEmptyBorder());
	    				tools.textSizeComboBox.setBorder(BorderFactory.createEmptyBorder());
	    				tools.selectButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.rotateButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.importImageButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.openButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.openRecentComboBox.setBorder(BorderFactory.createEmptyBorder());
	    				tools.exportButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.newProjectButton.setBorder(UIManager.getBorder("Button.border"));
	    				tools.typedTextTextField.setEnabled(true);
	    				
	    				tools.buyAllRemainingFeaturesButton.setEnabled(false);
	    			}
					else {
						JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
						return;
				    }
				}catch(Exception ef){
					JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
	    			return;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "All features have been purchased", "No features to purchase", JOptionPane.INFORMATION_MESSAGE);
				tools.buyAllRemainingFeaturesButton.setEnabled(false);
			}
		}
		
		/**
		 * When the user attempts to purchase a feature and enters their credit card number, 
		 * the Luhn algorithm is used to check if the credit number is valid. 
		 * @param creditCardNumber - the credit number
		 * @return - true if the credit card number is valid, false otherwise
		 */
		public boolean luhnCheck(String creditCardNumber) {
	        if (creditCardNumber == null)
	            return false;
	        char checkDigit = creditCardNumber.charAt(creditCardNumber.length() - 1);
	        String digit = calculateCheckDigit(creditCardNumber.substring(0, creditCardNumber.length() - 1));
	        return checkDigit == digit.charAt(0);
	    }
		
		/**
		 * Used as part of process for checking if a credit card number is valid
		 * @param creditCardNumber - the credit card number
		 * @return
		 */
		public String calculateCheckDigit(String creditCardNumber) {
	        if (creditCardNumber == null)
	            return null;
	        String digit;
	        /* convert to array of int for simplicity */
	        int[] digits = new int[creditCardNumber.length()];
	        for (int i = 0; i < creditCardNumber.length(); i++) {
	            digits[i] = Character.getNumericValue(creditCardNumber.charAt(i));
	        }
	        
	        /* double every other starting from right - jumping from 2 in 2 */
	        for (int i = digits.length - 1; i >= 0; i -= 2)	{
	            digits[i] += digits[i];
	            
	            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
	            if (digits[i] >= 10) {
	                digits[i] = digits[i] - 9;
	            }
	        }
	        int sum = 0;
	        for (int i = 0; i < digits.length; i++) {
	            sum += digits[i];
	        }
	        /* multiply by 9 step */
	        sum = sum * 9;
	        
	        /* convert to string to be easier to take the last digit */
	        digit = sum + "";
	        return digit.substring(digit.length() - 1);
	    }
}
