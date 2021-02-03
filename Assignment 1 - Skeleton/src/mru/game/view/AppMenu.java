package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

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
		System.out.println("Enter a choice: ");
		
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
		System.out.println("Enter a choice: ");
		
		char option = scan.nextLine().toLowerCase().charAt(0);
		return option;
	}
	/**
	 * A prompt for the user to enter their name
	 * 
	 * @return name     The name which the user has entered
	 */
	public String promptName() {
		System.out.println("What is your name: ");
		String name = scan.nextLine().trim();
		return name;
	}
	/**
	 * Shows the players information
	 * 
	 * @param plyer      The player object
	 */
	public void showPlayer (Player plyer) {
		System.out.println(plyer);
	}
	/**
	 * A prompt for the user to enter the amount they wish to bet
	 * @return bet     The amount of virtual money the player wished to bet
	 */
	public int promptBet() {
		System.out.println("How much do you want to bet this round? ");
		int bet = scan.nextInt();
		return bet;
	}
	/**
	 * A method to print everyone in the ArrayList that has the most amount of wins 
	 * when user inputs option (T)  
	 * @param topPlayers  A ArrayList of everyone that has the most amount of wins in the whole player database
	 */
	public void printTop(ArrayList<Player> topPlayers) {
		for (Player p: topPlayers) {
			System.out.println(p.toString());
		}
		
	}
}
