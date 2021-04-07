package mru.tsc.application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("../view/CustomerView.fxml"));
			Scene scene = new Scene(root,1150,600);
			scene.getStylesheets().add(getClass().getResource("../style/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(event -> {
			
		});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
