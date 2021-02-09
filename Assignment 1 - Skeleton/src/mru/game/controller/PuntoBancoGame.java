package mru.game.controller;

import mru.game.view.AppMenu;

public class PuntoBancoGame {
	
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	AppMenu appMen;
	CardDeck deck;
	
	public PuntoBancoGame () {
		appMen = new AppMenu();
		deck = new CardDeck();
		
	}
	
	
	public boolean launchGame (char choice, int betAmount) {
		boolean win = false;
		
		System.out.println("        - PUNTO BANCO -");
		
		return win;
	}

}
