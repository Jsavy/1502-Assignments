package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;
import mru.game.model.Player;

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	Scanner scan;
	
	public AppMenu () {
		scan = new Scanner(System.in);
	}
	/**
	 * The main menu which the user prompts the user to select a choice, then returns the character which the user entered.
	 * 
	 * @return option      The option which the user has entered
	 */
	public char showMainMenu () {
		System.out.println("Select one of these option: \n");
		System.out.println("\t (P) Play Game");
		System.out.println("\t (S) Search");
		System.out.println("\t (E) Exit\n");
		System.out.print("\nEnter a choice: ");
		
		char option = scan.nextLine().toLowerCase().charAt(0);
		return option;
	}
	/**
	 * The sub menu which the user prompts the user to select a choice, then returns the character which the user entered.
	 * 
	 * @return option      The option which the user has entered
	 */
	public char showSubMenu () {
		System.out.println("Select one of these options: \n");
		System.out.println("\t (T) Top player (Most number of wins)");
		System.out.println("\t (N) Looking for a Name");
		System.out.println("\t (B) Back to Main menu\n");
		System.out.print("\nEnter a choice: ");
		
		char option = scan.nextLine().toLowerCase().charAt(0);
		return option;
	}
	/**
	 * This menu prompts the users on who he wishes to bet on, then returns the character which the user entered.
	 * 
	 * 
	 * @return selection    The selection which the user has entered.
	 */
	public char betWho() {
		System.out.println("Who do you want to bet on? \n");
		System.out.println("\t (P) Player Wins");
		System.out.println("\t (B) Banker Wins");
		System.out.println("\t (T) Tie Game\n");
		System.out.print("\nEnter your Choice: ");
		
		char selection = scan.nextLine().toLowerCase().charAt(0);
		return selection;
	}
	
	/**
	 * A prompt for the user to enter their name
	 * 
	 * @return name     The name which the user has entered
	 */
	public String promptName() {
		System.out.print("What is your name: ");
		String name = scan.nextLine().trim();
		return name;
	}
	/**
	 * Shows the players information
	 * 
	 * @param plyer      The player object
	 */
	public void showPlayer (Player plyer) {
		Player ply = plyer;
		System.out.println("                  - PLAYER INFO -");
		System.out.println("+==================+============+====================+");
		System.out.println("|NAME              |# WINS      |BALANCE             |");
		System.out.println("+==================+============+====================+");
		System.out.println(ply.toString());
		System.out.println("+==================+============+====================+");
	}
	/**
	 * A prompt for the user to enter the amount they wish to bet
	 * @return bet     The amount of virtual money the player wished to bet
	 */
	public int promptBet() {
		System.out.print("\nHow much do you want to bet this round? ");
		int bet = scan.nextInt();
		return bet;
	}
	/**
	 * A method to print everyone in the ArrayList that has the most amount of wins 
	 * when user inputs option (T)  
	 * @param topPlayers  A ArrayList of everyone that has the most amount of wins in the whole player database
	 */
	public void printTop(ArrayList<Player> topPlayers) {
		System.out.println("            - TOP PLAYERS -");
		System.out.println("+==================+==================+");
		System.out.println("|NAME              |# WINS            |");
		System.out.println("+==================+==================+");
		for (Player p: topPlayers) {
			System.out.println(p.ToptoString());
			System.out.println("+------------------+------------------+");
		}
		
	}
	/**
	 * Prints the header to the Punto Banco Game board
	 */
	public void printBoardHeader() {
		System.out.println("\n\n                 - PUNTO BANCO -");
		System.out.println("+=======================+=======================+");
		System.out.println("||PLAYER                |BANKER                ||");
		System.out.println("+=======================+=======================+");
	}
	/**
	 * Prints the first two cards for Punto Banco following game board layout
	 * 
	 * @param playerCardOne  the first card the player received
	 * @param bankerCardOne  the first card the banker received
	 * @param playerCardTwo  the second card the player received
	 * @param bankerCardTwo  the second card the banker received
	 */
	public void printTwo(Card playerCardOne, Card bankerCardOne, Card playerCardTwo, Card bankerCardTwo) {
		System.out.format("|%-23s|%-23s|%n", playerCardOne.toString(), bankerCardOne.toString());	
		System.out.println("+-----------------------+-----------------------+");
		System.out.format("|%-23s|%-23s|%n", playerCardTwo.toString(), bankerCardTwo.toString());
		System.out.println("+-----------------------+-----------------------+");
	}
	/**
	 * Prints if no third card was given to the players
	 * 
	 * @param player  the modulo value of the sum of cards the player received
	 * @param banker  the modulo value of the sum of cards the banker received
	 */
	public void noThird(int player, int banker) {
		System.out.println("|                       |                       |");
		System.out.println("+-----------------------+-----------------------+");
		System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
		System.out.println("+=======================+=======================+\n");
	}
	/**
	 * Prints if player has a third card and banker does not
	 * 
	 * @param playerCardThree the third card the player receives
	 * @param player          the modulo value of the sum of cards player received
	 * @param banker          the modulo value of the sum of cards banker received
	 */
	public void noThirdBanker(Card playerCardThree, int player, int banker) {
		System.out.format("|%-23s|                       |%n", playerCardThree);
		System.out.println("+-----------------------+-----------------------+");
		System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
		System.out.println("+=======================+=======================+\n");
	}
	/**
	 * Prints if player and banker both have a third card
	 * 
	 * @param playerCardThree   the third card the player receives
	 * @param bankerCardThree   the third card the banker receives
	 * @param player            the modulo value of the sum of cards player received
	 * @param banker            the modulo value of the sum of cards banker received
	 */
	public void thirdCard(Card playerCardThree, Card bankerCardThree, int player, int banker) {
		System.out.format("|%-23s|%-23s|%n", playerCardThree.toString(), bankerCardThree.toString());
		System.out.println("+-----------------------+-----------------------+");
		System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
		System.out.println("+=======================+=======================+\n");
	}
	/**
	 * Prints if only banker has a third card
	 * 
	 * @param bankerCardThree  the third card the banker receives
	 * @param player           the modulo value of the sum of cards player received
	 * @param banker           the modulo value of the sum of cards banker received
	 */
	public void noThirdPlayer(Card bankerCardThree, int player, int banker) {
		System.out.format("|                      |%-23s|%n", bankerCardThree);
		System.out.println("+-----------------------+-----------------------+");
		System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
		System.out.println("+=======================+=======================+\n");
	}
	/**
	 * Prints the header for if player won or lost 
	 */
	public void betHeader() {
		System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}
	/**
	 * Prints if the player won in punto banco
	 * 
	 * @param betAmount  the amount the user bet on the game
	 */
	public void playerWon(int betAmount) {
		System.out.println("        $       PLAYER WON " + betAmount + "$        $");
	}
	/**
	 * Prints if the player lost in punto banco
	 * 
	 * @param betAmount  the amount the user bet on the game
	 */
	public void playerLost(int betAmount) {
		System.out.println("        $      PLAYER LOST " + betAmount + "$        $");
	}
	/**
	 * Prints the footer to contain the player win or lost message to complete a box
	 */
	public void betFooter() {
		System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n");
	}
	/**
	 * Prints if the player already existed in the database
	 * 
	 * @param p  the player that is playing
	 */
	public void welcomeOldPlayer(Player p) {
		System.out.println("\n*****************************************************************");
		System.out.println(p.welcomeBackToString());
		System.out.println("*****************************************************************");
	}
	/**
	 * Prints if the player is a new player that has to be added to database
	 * 
	 * @param p  the player that is playing 
	 */
	public void welcomeNewPlayer(Player p) {
		System.out.println("\n****************************************************************");
		System.out.println(p.welcomeToString());
		System.out.println("****************************************************************");
	}
	/**
	 * Prints if the player tries to bet a amount larger than his score
	 */
	public void errorBet() {
		System.out.println("You are unable to bet an amount greater than your score please try again");
	}
	/**
	 * Prints whenever we want to know if user wishes to continue after receiving the info provided
	 */
	public void enterContinue() {
		System.out.println("Press \"Enter\" to continue");
		scan.nextLine();
	}
	/**
	 * Prints when the Punto Banco game is over to ask if player wishes to play again
	 * 
	 * @return option   the option the user selects 
	 */
	public char playAgain() {
		char option;
		System.out.println("Do you want to play again (Y/N)?");
		scan.nextLine();
		option = scan.nextLine().toLowerCase().charAt(0);
		return option;
	}
	/**
	 * Prints if the user searches for a player that is not in the database
	 */
	public void playerNotFound() {
		System.out.println("\nError: Person not found\n");
	}
	/**
	 * Prints if the user selects an invalid option in the sub menu
	 */
	public void searchError() {
		System.out.println("Invalid Input: Please try again!");
	}
	/**
	 * Prints if the user selects an invalid option in the main menu
	 */
	public void mainMenuError() {
		System.out.println("\nInvalid Input: Please try again!\n");
	}
	/**
	 * Prints if the user selects a wrong option on who to bet on 
	 */
	public void betError() {
		System.out.println("\nInvalid Input: Please try again!\n");
	}
	
	public void noScore() {
		System.out.println("\n you have run out of virtual currency and are no longer able to play");
		System.out.println("You now will be returning to the main menu\n");
	}

}
