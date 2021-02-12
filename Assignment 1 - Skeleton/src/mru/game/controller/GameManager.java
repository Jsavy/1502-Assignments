package mru.game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.AppMenu;
import mru.game.controller.PuntoBancoGame;
/**
 * This class manages the game
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
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
	PuntoBancoGame pbg;

	public GameManager() throws Exception {
		players = new ArrayList<Player>();
		appMen = new AppMenu();
		casinoInfo();
		launchApp(); 
	}
	/**
	 * This method assists in the processing of user inputs from the main menu 
	 * @throws IOException needed for file input
	 */
	private void launchApp() throws IOException {

		boolean flag = true;
		char option; // choice that the user will input

		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case 'p': // option to play the Punto Banco Game
				playGame();
				flag = false;
				break;
			case 's': // option to go the Sub Menu, Search
				search();
				flag = false;
				break;
			case 'e': // option to save and exit the application
				flag = false;
				exit();
				break;
			default:
				appMen.mainMenuError(); // error if user entered an invalid input
			}
		}
	}
	/**
	 * This method assists in processing the users input for the sub menu if user selected (S)
	 * @throws IOException needed for file input 
	 */
	private void search() throws IOException {
		char option = appMen.showSubMenu();
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		
		while (flag) {
			switch (option) {
			case 't': // option to display info about the players with the most wins
				findTopPlayer();
				flag = false;
				break;
			case 'n': // option to display info about user inputted player
				String name = appMen.promptName();
				Player plyer = searchByName(name);
				if (plyer != null) {
					appMen.showPlayer(plyer); // shows user's inputted player data
					appMen.enterContinue();
					launchApp();
					flag = false;
				}else {
					 appMen.playerNotFound();
					 search();
				}
				break;
			case 'b': // option to go back to the main menu
				launchApp();
				flag = false;
				break;
			default: 
				appMen.searchError(); // error if user enters an invalid input
				option = appMen.showSubMenu();
			}
		}

		
	}
	/**
	 * This method is the Play option which initializes the start of a game
	 * It asks for the player's name and creates a new one if not found.
	 * A welcome message displays if the player is already in the database
	 * @throws IOException  needed for file input
	 */
	private void playGame() throws IOException {
		Scanner scan = new Scanner(System.in);
		final int HUNDRED = 100;
		final int ZERO = 0;
		String name = appMen.promptName();
		Player p = searchByName(name);
		boolean input = true;
		char playAgain;
		char choice;
		pbg = new PuntoBancoGame();
		
		// Adding new player and welcoming back returning players via name search
		if (p == null) {
			players.add (new Player (name, HUNDRED, ZERO));
			p = searchByName(name);
			appMen.welcomeNewPlayer(p);
		}else {
			appMen.welcomeOldPlayer(p);
		}
			playAgain = 'y'; // variable to determine if the user wishes to continue playing
			while (playAgain == 'y') {
				// Menu to display which side to bet on
				choice = appMen.betWho();
            while (input == true) {
            	switch (choice) {
    			case 'p': // option for Player Wins
    				choice = 'p';
    				input = false;
    				break;
    			case 'b': // option for Banker wins
    				choice = 'b';
    				input = false;
    				break;
    			case 't': // option for Tie
    				choice = 't';
    				input = false;
    				break;
    			default:
    			appMen.betError();
    			choice = appMen.betWho();
    		}

            }
            int betAmt = appMen.promptBet(); // Asking user for bet amount
  
			// Error message when user bets more than their balance
            while(betAmt > p.getScore()) {
            	appMen.errorBet();
            	betAmt = appMen.promptBet();
            	}
			// If user's balance is 0, returns them to main menu to exit
            if(p.getScore() == ZERO) {
            	appMen.noScore();
            	playAgain = 'n';
            } else {
			// Replays the game if the user chooses 'y'
				pbg.launchGame(p, choice, betAmt);
            	playAgain = appMen.playAgain();
            }
			}
    		
			launchApp();
		


	}
	/**
	 * This method finds a player object from the inputed name from user
	 * @param   name    String of the players name 
	 * @return  plyer   Player object of proper player from user inputted name
	 */
	private Player searchByName(String name) {
		Player plyer = null;
		
		for (Player p: players) {
			if (p.getName().equalsIgnoreCase(name)) {
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
	 * @throws IOException needed for File input
	 *  
	 */
	private void findTopPlayer() throws IOException {
		int most = 0;
		int value = 0;
		topPlayers = new ArrayList<Player>();
		Scanner scan = new Scanner(System.in);
		
		// finding player with most wins
		for (Player p: players) {
			value = p.getWins();
			if(value > most) {
				most = value;
			}
		}
		// finding other players with the same amount of wins as the top player
		for (Player p: players) {
			value = p.getWins();
			if(value == most) {
				topPlayers.add(p); // adding players with the most wins to an arraylist
			}
		}
		
		appMen.printTop(topPlayers); // printing the arraylist of top player with most wins
		
		appMen.enterContinue();

		launchApp();
	}
	
	/**
	 * A method which loads the player data into the file "CasinoInfo.txt"
	 * 
	 * @throws IOException needed for file input
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
	 * @throws Exception  used for file input
	 */
	private void casinoInfo() throws Exception {

		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		final int ZERO = 0;
		final int ONE = 1;
		final int TWO = 2;

		if (myFile.exists()) {
			Scanner fileReader = new Scanner(myFile);
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(",");
				Player p = new Player(splittedLine[ZERO], Integer.parseInt(splittedLine[ONE]),
						Integer.parseInt(splittedLine[TWO]));
				players.add(p);
			}
			fileReader.close();
		} else {
			myFile.createNewFile();
		}
	}
}
