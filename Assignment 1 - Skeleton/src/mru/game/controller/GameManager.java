package mru.game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.AppMenu;

public class GameManager {

	/*
	 * In this class toy'll need these methods: A constructor A method to load the
	 * txt file into an arraylist (if it exists, so you check if the txt file exists
	 * first) A save method to store the arraylist into the the txt file A method to
	 * search for a player based their name A method to find the top players
	 * Depending on your designing technique you may need and you can add more
	 * methods here
	 */

	private static final String FILE_PATH = "res/CasinoInfo.txt";
	ArrayList<Player> players;
	AppMenu appMen;

	public GameManager() throws Exception {
		players = new ArrayList<Player>();
		appMen = new AppMenu();
		CasinoInfo();
		launchApp();
	}

	private void launchApp() throws IOException {

		boolean flag = true;
		char option;

		while (flag) {
			option = appMen.showMainMenu();

			switch (option) {
			case 'p':
				
				break;
			case 's':
				Search();
				break;
			case 'e':
				Exit();
				break;
			}
		}
	}

	private void Search() {
		char option = appMen.showSubMenu();
		
		switch (option) {
		case 't':
			FindTopPlayer();
			break;
		case 's':
			Player plyer = searchByName();
			appMen.showPlayer(plyer);
			break;
		case 'b':
			break;
		}
		
	}

	private Player searchByName() {
		String name = appMen.promptName();
		Player plyer = null;
		
		for (Player p: players) {
			if (p.getName().equals(name)) {
				plyer = p;
				break;
			}
		}
		
		return plyer;
	}

	private void FindTopPlayer() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * A method which loads the player data into the file "CasinoInfo.txt"
	 * 
	 * @throws IOException
	 */

	private void Exit() throws IOException {
		File file = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(file);

		for (Player p : players) {
			pw.println(p.format());
		}

		pw.close();
	}

	/**
	 * A method which loads the file "CasinoInfo.txt" and, if not found creates the file
	 * 
	 * @throws Exception
	 */
	private void CasinoInfo() throws Exception {

		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;

		if (myFile.exists()) {
			Scanner fileReader = new Scanner(myFile);
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(",");
				Player p = new Player(splittedLine[0], Integer.parseInt(splittedLine[1]),
						Integer.parseInt(splittedLine[2]));
				players.add(p);
			}

		} else {
			myFile.createNewFile();
		}
	}
}
