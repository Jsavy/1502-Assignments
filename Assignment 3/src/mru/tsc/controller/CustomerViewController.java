package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

import com.sun.prism.paint.Color;
import java.awt.color.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mru.tsc.exceptions.PlayerException;
import mru.tsc.exceptions.ToyStoreException;
import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;

public class CustomerViewController implements Initializable {

	private static final String FILE_PATH = "res/toys.txt";
	private static final String LOG_PATH = "doc/myLog.log";
	private final static Logger LOGR = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	ArrayList<Toy> toys;
	ArrayList<Toy> toyName;
	ArrayList<Toy> toyType;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		toys = new ArrayList<Toy>();
		choiceBox.getItems().add("Figure");
		choiceBox.setValue("Figure");
		choiceBox.getItems().add("Animal");
		choiceBox.getItems().add("Board Game");
		choiceBox.getItems().add("Puzzle");
		LogManager.getLogManager().reset();
		LOGR.setLevel(Level.INFO);
		setupLogger();
		readFile();

	}
	/**
	 * This method is used to start up the logger and initiate FileHandler and log formatter objects
	 * the fileHandler allows for appendage to existing file so no log data is lost
	 */
	public static void setupLogger() {
		try {
			FileHandler fh = new FileHandler(LOG_PATH, true);
			SimpleFormatter sformatter = new SimpleFormatter();
			fh.setFormatter(sformatter);
			LOGR.addHandler(fh);
			LOGR.log(Level.WARNING, "First Log");
			LOGR.fine("info");
			
		}catch(IOException e) {
			LOGR.log(Level.SEVERE, e.getMessage(), e);
		}
	    LOGR.fine("This message is logged in the file");
	}


	// Remove item feature
	@FXML
	private ListView<Toy> removeView, homeView;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private RadioButton serialButton, nameButton, typeButton;

	@FXML
	private Button btnRemove, btnClear, removeSearchButton, btnBuy;

	@FXML
	private TextField removeInput, addSN, addName, addBrand, addPrice, addInventory, addAge, addClassification, addType,
			addMaterial, addSize, addMinPlayer, addMaxPlayer, addDesigner, homeSN;

	@FXML
	Label removeErrorMessage, addErrorMessage, searchTypeLabel, searchError;

	/**
	 * This handler is used when the user selects the search button on the remove
	 * tab and finds the toy object
	 * 
	 * @param event when the button on remove screen is clicked
	 */
	@FXML
	void removeSearchHandler(ActionEvent event) {

		String sn;
		sn = removeInput.getText();
		removeVerifySN(sn);

	}

	/**
	 * This handler is used when the user selects the remove button on the remove
	 * tab and deletes it from the toys array
	 * 
	 * @param event when the remove button on the remove screen is clicked
	 */
	@FXML
	void btnRemoveHandler(ActionEvent event) {
		Toy selectedToy;

		selectedToy = removeView.getSelectionModel().getSelectedItem();
		removeView.getItems().remove(selectedToy);
		toys.remove(selectedToy);
		removeErrorMessage.setText("Toy successfully removed");
		exitFile();
	}
	
	// add Toy feature

	/**
	 * This handler is used when the user selects the save button on the add tab and
	 * adds it to the toy array
	 * 
	 * @param event when the save button on the add toy screen is clicked
	 */
	@FXML
	void saveButtonPushed(ActionEvent event) {
		String userSelection = choiceBox.getValue();
		String reference1 = "Figure";
		String reference2 = "Animal";
		String reference3 = "Board Game";
		String reference4 = "Puzzle";
		char figureRestriction1 = 'A';
		char figureRestriction2 = 'D';
		char figureRestriction3 = 'H';
		char animalRestriction1 = 'S';
		char animalRestriction2 = 'M';
		char animalRestriction3 = 'L';
		char puzzleRestriction1 = 'M';
		char puzzleRestriction2 = 'C';
		char puzzleRestriction3 = 'L';
		char puzzleRestriction4 = 'T';
		char puzzleRestriction5 = 'R';
		final int ZERO = 0;
		final int ONE = 1;
		final int TWO = 2;
		final int THREE = 3;
		final int FOUR = 4;
		final int FIVE = 5;
		final int SIX = 6;
		final int SEVEN = 7;
		final int EIGHT = 8;
		final int NINE = 9;
		String SN;
		char SNValue;
		int SNValidation;
		String name;
		String brand;
		double price = -1;
		boolean priceValidity = false;
		boolean playerValidity = false;
		boolean SNLengthValidity = false;
		boolean SNExists;
		int inventory;
		int age;
		char classification;
		char type;
		String material;
		char size;
		int minPlayer;
		int maxPlayer;
		String numPlayer;
		String designer;
		Toy t = null;

		SN = addSN.getText();
		SNValue = SN.charAt(ZERO);
		SNValidation = Character.getNumericValue(SNValue);
		SNLengthValidity = addVerifySN(SN);
		SNExists = addSNExist(SN);
		t = searchRemove(SN);
		if (t != null) {
			addErrorMessage.setText("Toy already exists");
			LOGR.log(Level.WARNING, "Toy Exists within the database");
		} else {
			if (!SNLengthValidity) {
				addErrorMessage.setText("The Serial number contains letters or is too short or long");
				LOGR.log(Level.WARNING, "The Serial number is either too long or short. If not then it contains a letter");
			} else {
				name = addName.getText();
				brand = addBrand.getText();
				price = Double.parseDouble(addPrice.getText());
				inventory = Integer.parseInt(addInventory.getText());
				age = Integer.parseInt(addAge.getText());
				price = Double.valueOf(addPrice.getText());

				if (!priceValidity) {
					try {
						priceValidity = isPriceValid(price);
					} catch (ToyStoreException e) {
						String error = e.getMessage();
						LOGR.log(Level.WARNING, e.getMessage());
						addErrorMessage.setText(error);
					}
				}
				if (priceValidity && SNLengthValidity) {
					if (userSelection.equalsIgnoreCase(reference1)) {
						if (SNValidation == ZERO || SNValidation == ONE) {
							classification = addClassification.getText().toUpperCase().charAt(0);
							if(classification == figureRestriction1 || classification == figureRestriction2 || classification == figureRestriction3) {
								Toy f = new Figure(SN, name, brand, price, inventory, age, classification);
								toys.add(f);
								exitFile();
								addErrorMessage.setText("Success");
							} else {
								addErrorMessage.setText("The classification does not follow the database");
								LOGR.log(Level.WARNING, "The classification letter did not match the A,D,H scheme used in the database");
							}
						} else {
							addErrorMessage.setText("Figure Serial error");
							LOGR.log(Level.WARNING, "The Serial that was entered does not follow the first digit criteria of Figure");
						}
					} else if (userSelection.equalsIgnoreCase(reference2)) {
						if (SNValidation == TWO || SNValidation == THREE) {
							material = addMaterial.getText();
							size = addSize.getText().toUpperCase().charAt(0);
							if(size == animalRestriction1 || size == animalRestriction2 || size == animalRestriction3) {
								Toy a = new Animal(SN, name, brand, price, inventory, age, material, size);
								toys.add(a);
								exitFile();
								addErrorMessage.setText("Success");
							} else {
								addErrorMessage.setText("The size does not follow the database");
								LOGR.log(Level.WARNING, "The size letter did not match the S,M,L scheme used in the database");
							}
						} else {
							addErrorMessage.setText("Animal Serial error");
							LOGR.log(Level.WARNING, "The Serial that was entered does not follow the first digit criteria of Animal");
						}
					} else if (userSelection.equalsIgnoreCase(reference3)) {
						minPlayer = Integer.parseInt(addMinPlayer.getText());
						maxPlayer = Integer.parseInt(addMaxPlayer.getText());
						if (!playerValidity) {
							try {
								playerValidity = isPlayerValid(minPlayer, maxPlayer);
							} catch (PlayerException e) {
								String error = e.getMessage();
								LOGR.log(Level.WARNING, e.getMessage());
								addErrorMessage.setText(error);
							}
						} else {
							if (SNValidation == SEVEN || SNValidation == EIGHT || SNValidation == NINE) {
								designer = addDesigner.getText();
								numPlayer = minPlayer + "-" + maxPlayer;
								Toy b = new BoardGame(SN, name, brand, price, inventory, age, numPlayer, designer);
								toys.add(b);
								exitFile();
								addErrorMessage.setText("Success");
							} else {
								addErrorMessage.setText("BoardGame Serial error");
								LOGR.log(Level.WARNING, "The Serial that was entered does not follow the first digit criteria of BoardGame");
							}
						}
					} else if (userSelection.equalsIgnoreCase(reference4)) {
						if (SNValidation == FOUR || SNValidation == FIVE || SNValidation == SIX) {
							type = addType.getText().toUpperCase().charAt(0);
							if(type == puzzleRestriction1 || type == puzzleRestriction2 || type == puzzleRestriction3 
									|| type == puzzleRestriction4 || type == puzzleRestriction5) {
								Toy p = new Puzzle(SN, name, brand, price, inventory, age, type);
								toys.add(p);
								exitFile();
								addErrorMessage.setText("Success");
							} else {
								addErrorMessage.setText("The type does not follow the database");
								LOGR.log(Level.WARNING, "The type letter did not match the M,C,L,T,R scheme used in the database");
							}
						} else {
							addErrorMessage.setText("Puzzle Serial error");
							LOGR.log(Level.WARNING, "The Serial that was entered does not follow the first digit criteria of Puzzle");
						}
					}
				}
			}
		}

	}

	private boolean addSNExist(String sN) {
		boolean valid = true;
		for (Toy t : toys) {
			String grabber = t.getSN();
			if (grabber.equalsIgnoreCase(sN)) {
				valid = false;
			}
		}
		return valid;
	}
	
	// Home page 
	
	@FXML
	public void btnBuyHandler (ActionEvent event) {
		Toy selectedToy;
		final int ONE = 1; // constant variable to buy 1 instance of a toy

		selectedToy = homeView.getSelectionModel().getSelectedItem();
		
		if (selectedToy.getAvaliableCount() == 1) {
			homeView.getItems().remove(selectedToy);
			toys.remove(selectedToy);
			searchError.setText("Successful purchase");
		}else {
			homeView.refresh();
			selectedToy.setAvaliableCount(selectedToy.getAvaliableCount() - ONE);
			searchError.setText("Successful purchase");
		}
		
		
		exitFile();
	}

	/**
	 * This method is used to determine the label beside the textfield on home tab
	 * so the user does not confuse what information he needs to type in
	 * 
	 * @param event when a radio button is selected on the home tab
	 */
	@FXML
	public void radioSelected(ActionEvent event) {

		if (serialButton.isSelected()) {
			searchTypeLabel.setText("Serial Number (SN)");
		} else if (nameButton.isSelected()) {
			searchTypeLabel.setText("Name");
		} else if (typeButton.isSelected()) {
			searchTypeLabel.setText("Type");
		}
	}

	/**
	 * This method is used to determine which method is needed needs to be called to
	 * display the correct items in listview
	 * 
	 * @param event when the search button is clicked on the home page
	 */
	@FXML
	public void btnSearchHandler(ActionEvent event) {

		if (serialButton.isSelected()) {
			homeVerifySN(homeSN.getText());
		} else if (nameButton.isSelected()) {
			searchByName(homeSN.getText());
		} else if (typeButton.isSelected()) {
			searchByType(homeSN.getText());
		}
	}

	/**
	 * This method is used to clear listView on the home page
	 * 
	 * @param event when the clear button is clicked on the home page
	 */
	@FXML
	public void clearButtonHandler(ActionEvent event) {

		homeView.getItems().clear();
	}

	/**
	 * Used by the home tab to search for the Toy object based of SN input and puts
	 * it in listview
	 * 
	 * @param sn the serial number the user inputted
	 */
	private void homeVerifySN(String sn) {

		final int TEN = 10; // constant variable for 10
		Toy a = null; // toy object
		ObservableList<Toy> SN;

		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				a = searchRemove(sn);
				if (a == null) {
					removeErrorMessage.setText("Toy does not exist");
					LOGR.log(Level.WARNING, "The toy was not found to be in the database");
				} else {
					SN = FXCollections.observableArrayList(a);
					homeView.getItems().addAll(SN);
					SN.remove(a);
				}
			} else {
				searchError.setText("The Serial Number's length must be 10 digits!");
				LOGR.log(Level.WARNING, "The Serial that was entered is either too long or short");
			}
		} else {
			searchError.setText("The serial number must only contain digits!");
			LOGR.log(Level.WARNING, "The Serial that was entered contains characters outside of 0-9 range");
		}
	}

	/**
	 * Method that creates a new arrayList based off search criteria the user is
	 * looking for and updates the database ArrayList according to the actions the
	 * user selects
	 * 
	 * @param name the name of toy based off keyword search
	 */
	private void searchByName(String name) {
		ObservableList<Toy> names;
		toyName = new ArrayList<Toy>(); // arraylist for printing just the toys that contain the keyword
		String nameLow = name.toLowerCase(); // makes the inputed value by user to lowercase to use contains
		String resultLow; // the resulting name from searching the array in lowercase
		Toy t = null; // toy variable used

		for (Toy tt : toys) {
			resultLow = tt.getName().toLowerCase();
			if (resultLow.contains(nameLow)) {
				if (tt instanceof Figure) {
					t = (Figure) tt;
					toyName.add(t);
				} else if (tt instanceof Animal) {
					t = (Animal) tt;
					toyName.add(t);
				} else if (tt instanceof Puzzle) {
					t = (Puzzle) tt;
					toyName.add(t);
				} else if (tt instanceof BoardGame) {
					t = (BoardGame) tt;
					toyName.add(t);
				}
			}
		}

		names = FXCollections.observableArrayList(toyName);
		homeView.getItems().addAll(names);
		names.removeAll(toyName);
	}

	/**
	 * This method is used to display the toys of listview for the remove tab that
	 * was found based of serial search
	 * 
	 * @param sn the serial number the user inputted
	 */
	private void removeVerifySN(String sn) {

		final int TEN = 10; // constant variable for 10
		Toy a = null; // toy object
		ObservableList<Toy> t;

		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				a = searchRemove(sn);
				if (a == null) {
					removeErrorMessage.setText("Toy does not exist");
					LOGR.log(Level.WARNING, "The toy does not exist in the database");
				} else {
					t = FXCollections.observableArrayList(a);
					removeView.getItems().addAll(t);
					t.remove(a);
				}
			} else {
				removeErrorMessage.setText("The Serial Number's length must be 10 digits!");
				LOGR.log(Level.WARNING, "The Serial that was entered is either too long or short");

			}
		} else {
			removeErrorMessage.setText("The serial number must only contain digits!");
			LOGR.log(Level.WARNING, "The Serial that was entered contains characters outside the 0-9 range");

		}
	}

	/**
	 * Method that creates a new arrayList based off search criteria the user is
	 * looking for and updates the database ArrayList according to the actions the
	 * user selects
	 * 
	 * @param types the type of toy based off instance of search
	 */
	private void searchByType(String types) {
		ObservableList<Toy> type;
		toyType = new ArrayList<Toy>(); // Arraylist used to print the type of toy user requested
		String a = "Figure"; // reference item used to determine what type of casting required
		String b = "Animal"; // reference item used to determine what type of casting required
		String c = "Puzzles"; // reference item used to determine what type of casting required
		String d = "Board Games"; // reference item used to determine what type of casting required
		Toy t = null; // toy object used for the method
		final int ZERO = 0; // used to eliminate magic number
		final int ONE = 1; // used to eliminate magic number
		final int TWO = 2; // used to eliminate magic number
		final int THREE = 3; // used to eliminate magic number
		final int FOUR = 4; // used to eliminate magic number
		final int FIVE = 5; // used to eliminate magic number
		final int SIX = 6; // used to eliminate magic number
		final int SEVEN = 7; // used to eliminate magic number
		final int EIGHT = 8; // used to eliminate magic number
		final int NINE = 9; // used to eliminate magic number

		// Finds all toys that are "Figure" type
		if (types.equalsIgnoreCase(a)) {
			for (Toy tt : toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if (firstDigit == ZERO || firstDigit == ONE) {
					t = (Figure) tt;
					toyType.add(t);
				}
			}
			// Finds all toys that are "Animal" type
		} else if (types.equalsIgnoreCase(b)) {
			for (Toy tt : toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if (firstDigit == TWO || firstDigit == THREE) {
					t = (Animal) tt;
					toyType.add(t);
				}
			}
			// Finds all toys that are "Puzzle" type
		} else if (types.equalsIgnoreCase(c)) {
			for (Toy tt : toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
					t = (Puzzle) tt;
					toyType.add(t);
				}
			}
			// Finds all toys that are "Board Games" type
		} else if (types.equalsIgnoreCase(d)) {
			for (Toy tt : toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if (firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {
					t = (BoardGame) tt;
					toyType.add(t);
				}
			}
		}
		type = FXCollections.observableArrayList(toyType);
		homeView.getItems().addAll(type);
		type.removeAll(toyType);

	}

	/**
	 * Validation class that uses the created ToyStoreException class to throw error
	 * message if the price is negative
	 * 
	 * @param price the price the user inputed for the toy
	 * @return valid the boolean value of validity
	 * @throws ToyStoreException exception class used to throw exception
	 */
	public boolean isPriceValid(double price) throws ToyStoreException {
		boolean valid = true; // boolean used to determine if price is valid
		final double ZERO = 0.00; // value needed for the if condition to see if price is valid

		if (price < ZERO) {
			valid = false;
			throw new ToyStoreException(" Inputted value: " + price);
		}
		return valid;
	}

	/**
	 * Validation class that uses the created ToyStoreException class to throw error
	 * message if minimum is greater than maximum
	 * 
	 * @param min the minimum number of players user inputed for toy
	 * @param max the maximum number of players user inputed for toy
	 * @return valid the boolean value of validity
	 * @throws ToyStoreException exception class used to throw exception
	 */
	public boolean isPlayerValid(int min, int max) throws PlayerException {
		boolean valid = true; // boolean used to determine if player numbers are valid

		if (min > max) {
			valid = false;
			throw new PlayerException(" Inputted minimum: " + min + " Inputted maximum: " + max);
		}
		return valid;
	}

	/**
	 * this method is used to find the toy object in the array based off user
	 * inputted serial number
	 * 
	 * @param SN the serial number the user inputted
	 * @return t the toy object that was found in the array
	 */
	private Toy searchRemove(String SN) {
		Toy t = null; // toy variable used for the method to function

		for (Toy tt : toys) {
			if (tt.getSN().equals(SN)) {
				if (tt instanceof Figure) {
					t = (Figure) tt;
				} else if (tt instanceof Animal) {
					t = (Animal) tt;
				} else if (tt instanceof Puzzle) {
					t = (Puzzle) tt;
				} else if (tt instanceof BoardGame) {
					t = (BoardGame) tt;
				}
			}
		}
		return t;
	}

	// Read file and save methods

	/**
	 * Method which loads the toy data in the file "toys.txt"
	 * 
	 */
	private void exitFile() {
		File file = new File(FILE_PATH); // file location for export
		PrintWriter pw; // used for file export
		String text; // used to print exceptions messages
		try {
			pw = new PrintWriter(file);
			for (Toy t : toys) {
				if (t instanceof Animal) {
					Animal cast = (Animal) t;
					pw.println(cast.format());
				} else if (t instanceof Figure) {
					Figure cast = (Figure) t;
					pw.println(cast.format());
				} else if (t instanceof Puzzle) {
					Puzzle cast = (Puzzle) t;
					pw.println(cast.format());
				} else if (t instanceof BoardGame) {
					BoardGame cast = (BoardGame) t;
					pw.println(cast.format());
				}
			}

			pw.close();
		} catch (FileNotFoundException e) {
			LOGR.log(Level.WARNING, e.getMessage());
		}
	}

	/**
	 * A method which loads the file "toys.txt" and adds different types of toys
	 * into an arraylist
	 */
	private void readFile() {
		File myFile = new File(FILE_PATH); // file location to read
		String currentLine; // the current line that has been read of the file
		String[] splittedLine; // the information for the whole line split into its components based on
								// criteria
		Scanner fileReader = null; // scanner for file reader
		String text; // used for printing the error message
		int firstDigit; // the first digit of the serial number
		final int ZERO = 0; // used to determine the PolyMorphism
		final int ONE = 1; // used to determine the PolyMorphism
		final int TWO = 2; // used to determine the PolyMorphism
		final int THREE = 3; // used to determine the PolyMorphism
		final int FOUR = 4; // used to determine the PolyMorphism
		final int FIVE = 5; // used to determine the PolyMorphism
		final int SIX = 6; // used to determine the PolyMorphism
		final int SEVEN = 7; // used to determine the PolyMorphism
		final int EIGHT = 8; // used to determine the PolyMorphism
		final int NINE = 9; // used to determine the PolyMorphism

		try {
			fileReader = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			LOGR.log(Level.WARNING, e.getMessage());
		}

		while (fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			splittedLine = currentLine.split(";");
			firstDigit = Character.getNumericValue(splittedLine[ZERO].charAt(0));

			// Loads in Figure toys
			if (firstDigit == ZERO || firstDigit == ONE) {

				Toy f = new Figure((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO],
						Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]),
						Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
				toys.add(f);
				// Loads in Animal toys
			} else if (firstDigit == TWO || firstDigit == THREE) {

				Toy a = new Animal((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO],
						Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]),
						Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX], splittedLine[SEVEN].charAt(0));
				toys.add(a);
				// Loads in Puzzle toys
			} else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {

				Toy p = new Puzzle((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO],
						Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]),
						Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
				toys.add(p);
				// Loads in Board Game toys
			} else if (firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {

				Toy b = new BoardGame((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO],
						Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]),
						Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX], splittedLine[SEVEN]);
				toys.add(b);
			}
		}
		fileReader.close(); // close the file reader
	}

	/**
	 * this method is used to validate SN length
	 * 
	 * @param sn
	 * @return
	 */
	private boolean addVerifySN(String sn) {

		final int TEN = 10; // constant variable for 10
		boolean valid = false;

		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}

}
