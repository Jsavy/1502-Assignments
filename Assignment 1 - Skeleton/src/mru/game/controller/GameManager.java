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
	ArrayList<Player> topPlayers;

	public GameManager() throws Exception {
		players = new ArrayList<Player>();
		appMen = new AppMenu();
		casinoInfo();
		launchApp();
	}
	/**
	 * This method assists in the processing of user inputs from the main menu 
	 * @throws IOException
	 */
	private void launchApp() throws IOException {

		boolean flag = true;
		char option;

		while (flag) {
			option = appMen.showMainMenu();

			switch (option) {
			case 'p':
				
				break;
			case 's':
				search();
				break;
			case 'e':
				exit();
				break;
			}
		}
	}
	/**
	 * This method assists in processing the users input for the sub menu if user selected (S)
	 */
	private void search() {
		char option = appMen.showSubMenu();
		
		switch (option) {
		case 't':
			findTopPlayer();
			break;
		case 's':
			Player plyer = searchByName();
			appMen.showPlayer(plyer);
			break;
		case 'b':
			break;
		}
		
	}
	/**
	 * This method finds a player object from the inputed name from user 
	 * @return  plyer   Player object of proper player from user inputted name
	 */
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
	/**
	 * This method initializes new ArrayList for Top Players goes into the player database 
	 * searches for the highest wins once highest score is determined goes through the ArrayList 
	 * again and adds any player that has the highest amount of wins and adds into ArrayList for Top 
	 * Players then calls method to print topPlayers
	 *  
	 */
	private void findTopPlayer() {
		int most = 0;
		int value = 0;
		topPlayers = new ArrayList<Player>();
		
		for (Player p: players) {
			value = p.getWins();
			if(value > most) {
				most = value;
			}
		}
		for (Player p: players) {
			value = p.getWins();
			if(value == most) {
				topPlayers.add(p);
			}
		}
		appMen.printTop(topPlayers);

	}
	
	/**
	 * A method which loads the player data into the file "CasinoInfo.txt"
	 * 
	 * @throws IOException
	 */

	private void exit() throws IOException {
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
	private void casinoInfo() throws Exception {

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
			fileReader.close();
		} else {
			myFile.createNewFile();
		}
	}
}
