package mru.tsc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


public class CustomerViewController implements Initializable{
	
	@FXML
	private ChoiceBox choiceBox;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		choiceBox.getItems().add("Figure");
		choiceBox.getItems().add("Animal");
		choiceBox.getItems().add("Board Game");
		choiceBox.getItems().add("Puzzle");
		
		
	}
	
	public void saveButtonPushed() {
		
	}
	

	
	
	

	
}
