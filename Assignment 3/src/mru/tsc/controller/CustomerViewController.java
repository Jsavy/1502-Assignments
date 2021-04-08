package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	ArrayList<Toy> toys;
	ArrayList<Toy> toyName;
	ArrayList<Toy> toyType;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		toys = new ArrayList<Toy>();

		choiceBox.getItems().add("Figure");
		choiceBox.getItems().add("Animal");
		choiceBox.getItems().add("Board Game");
		choiceBox.getItems().add("Puzzle");

		readFile();

	}

	// Remove item feature
	@FXML
	private ListView<Toy> removeView, homeView;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private RadioButton serialButton, nameButton, typeButton;

	@FXML
	private Button btnRemove;

	@FXML
	Button removeSearchButton;

	@FXML
	private TextField removeInput, addSN, addName, addBrand, addPrice, addInventory, addAge, addClassification, addType,
			addMaterial, addSize, addMinPlayer, addMaxPlayer, addDesigner, homeSN;

	@FXML
	Label removeErrorMessage, addErrorMessage, searchTypeLabel, searchError;

	@FXML
	void removeSearchHandler(ActionEvent event) {

		String sn;
		sn = removeInput.getText();
		removeVerifySN(sn);

	}

	@FXML
	void btnRemoveHandler(ActionEvent event) {
		int selectedToy;

		selectedToy = removeView.getSelectionModel().getSelectedIndex();
		removeView.getItems().remove(selectedToy);
		toys.remove(selectedToy);
		removeErrorMessage.setText("Toy successfully removed");
	}

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

	@FXML
	void saveButtonPushed(ActionEvent event) {
		String userSelection = (String) choiceBox.getValue();
		String reference1 = "Figure";
		String reference2 = "Animal";
		String reference3 = "Board Game";
		String reference4 = "Puzzle";
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
		String name;
		String brand;
		double price;
		boolean priceValidity = false;
		boolean playerValidity = false;
		int inventory;
		int age;
		char classification;
		char type;
		String material;
		char size;
		int minPlayer;
		int maxPlayer;
		String designer;

		price = Double.valueOf(addPrice.getText());
		if (!priceValidity) {
			try {
				priceValidity = isPriceValid(price);
			} catch (ToyStoreException e) {
				String error = e.getMessage();
				addErrorMessage.setText(error);
			}
		}
		if (priceValidity) {
			if (userSelection == reference1) {
				SN = addSN.getText();
				char number = SN.charAt(ZERO);
				int value = number;
				if (value != ZERO || value != ONE) {
					addErrorMessage.setText("Invalid SN");
				} else {
					name = addName.getText();
					brand = addBrand.getText();
					price = Double.valueOf(addPrice.getText());
					inventory = Integer.valueOf(addInventory.getText());
					age = Integer.valueOf(addAge.getText());
					classification = addClassification.getText().charAt(ZERO);
					Toy f = new Figure(SN, name, brand, price, inventory, age, classification);
					toys.add(f);
				}
			} else if (userSelection == reference2) {
				SN = addSN.getText();
				char number = SN.charAt(ZERO);
				int value = number;
				if (value != TWO || value != THREE) {
					addErrorMessage.setText("Invalid SN");
				} else {
					name = addName.getText();
					brand = addBrand.getText();
					price = Double.valueOf(addPrice.getText());
					inventory = Integer.valueOf(addInventory.getText());
					age = Integer.valueOf(addAge.getText());
					material = addMaterial.getText();
					size = addSize.getText().charAt(ZERO);
					Toy a = new Animal(SN, name, brand, price, inventory, age, material, size);
					toys.add(a);
				}
			} else if (userSelection == reference3) {
				minPlayer = Integer.valueOf(addMinPlayer.getText());
				maxPlayer = Integer.valueOf(addMaxPlayer.getText());
				if (!playerValidity) {
					try {
						playerValidity = isPlayerValid(minPlayer, maxPlayer);
					} catch (PlayerException e) {
						String error = e.getMessage();
						addErrorMessage.setText(error);
					}
				} else {
					SN = addSN.getText();
					char number = SN.charAt(ZERO);
					int value = number;
					if (value != SEVEN || value != EIGHT || value != NINE) {
						addErrorMessage.setText("Invalid SN");
					} else {
						name = addName.getText();
						brand = addBrand.getText();
						price = Double.valueOf(addPrice.getText());
						inventory = Integer.valueOf(addInventory.getText());
						age = Integer.valueOf(addAge.getText());
						String playerFormat = minPlayer + "-" + maxPlayer;
						designer = addDesigner.getText();
						Toy b = new BoardGame(SN, name, brand, price, inventory, age, playerFormat, designer);
						toys.add(b);
					}
				}
			} else if (userSelection == reference4) {
				SN = addSN.getText();
				char number = SN.charAt(ZERO);
				int value = number;
				if (value != FOUR || value != FIVE || value != SIX) {
					addErrorMessage.setText("Invalid SN");
				} else {
					name = addName.getText();
					brand = addBrand.getText();
					price = Double.valueOf(addPrice.getText());
					inventory = Integer.valueOf(addInventory.getText());
					age = Integer.valueOf(addAge.getText());
					type = addType.getText().charAt(ZERO);
					Toy p = new Puzzle(SN, name, brand, price, inventory, age, type);
					toys.add(p);
				}
			}
		}
	}

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
			text = e.getMessage();
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

	public void removeVerifySN(String sn) {

		final int TEN = 10; // constant variable for 10
		Toy a = null; // toy object
		ObservableList<Toy> t;

		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				a = searchRemove(sn);
				if (a == null) {
					removeErrorMessage.setText("Toy does not exist");
				} else {
					t = FXCollections.observableArrayList(a);
					removeView.getItems().addAll(t);
					t.remove(a);
				}
			} else {
				removeErrorMessage.setText("The Serial Number's length must be 10 digits!");

			}
		} else {
			removeErrorMessage.setText("The serial number must only contain digits!");

		}
	}

	public void homeVerifySN(String sn) {

		final int TEN = 10; // constant variable for 10
		Toy a = null; // toy object
		ObservableList<Toy> SN;

		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				a = searchRemove(sn);
				if (a == null) {
					removeErrorMessage.setText("Toy does not exist");
				} else {
					SN = FXCollections.observableArrayList(a);
					homeView.getItems().addAll(SN);
					SN.remove(a);
				}
			} else {
				searchError.setText("The Serial Number's length must be 10 digits!");
			}
		} else {
			searchError.setText("The serial number must only contain digits!");
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
}
