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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;

public class CustomerViewController implements Initializable {

	private static final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toys;

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
	private ListView<Toy> removeView;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private Button btnRemove;

	@FXML
	Button removeSearchButton;

	@FXML
	private TextField removeInput, addSN, addName, addBrand, addPrice, addInventory, addAge, addClassification, addType, addMaterial,
	addSize, addMinPlayer, addMaxPlayer, addDesigner;

	@FXML
	Label removeErrorMessage;

	@FXML
	void removeSearchHandler(ActionEvent event) {

		ObservableList<Toy> r;
		String sn;
		final int TEN = 10;
		Toy a = null;

		sn = removeInput.getText();
		if (sn.matches("[0-9]+")) {
			if (sn.length() == TEN) {
				a = searchRemove(sn);
				if (a == null) {
					removeErrorMessage.setText("Toy does not exist");
				} else {
					r = FXCollections.observableArrayList(a);
					removeView.getItems().addAll(r);
					r.remove(a);
				}
			} else {
				removeErrorMessage.setText("The Serial Number's length must be 10 digits!");
			}
		} else {
			removeErrorMessage.setText("The serial number must only contain digits!");
		}

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
		System.out.println(userSelection);
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

}
