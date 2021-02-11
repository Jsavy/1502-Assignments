package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.view.AppMenu;

public class PuntoBancoGame {
	
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	AppMenu appMen;
	CardDeck deck;
	Card myCard;
	Player player;
	GameManager game;
	
	
	public PuntoBancoGame () {
		appMen = new AppMenu();
		deck = new CardDeck();
		player = new Player("null", 0, 0);
		myCard = new Card(0, "null");
		ArrayList<CardDeck> deck;
	}
	
	
	public boolean launchGame (Player name, char choice, int betAmount) {
		boolean win = false;
		boolean stopGame = false;
		char winner = 'n';
		Player pl = name;
		final int ZERO = 0;
		final int TEN = 10;
		int player = 0;
		int banker = 0;
		Card playerCardOne = null;
		Card bankerCardOne = null;
		Card playerCardTwo = null;
		Card bankerCardTwo = null;
		Card playerCardThree = null;
		Card bankerCardThree = null;

		int playerScore = pl.getScore();
		
		System.out.println("\n\n                 - PUNTO BANCO -");
		System.out.println("+=======================+=======================+");
		System.out.println("||PLAYER                |BANKER                ||");
		System.out.println("+=======================+=======================+");
		

		while(deck.getDeck().remove(0) != null && stopGame != true) {
			playerCardOne = deck.getDeck().remove(0);
			bankerCardOne = deck.getDeck().remove(0);
			playerCardTwo = deck.getDeck().remove(0);
			bankerCardTwo = deck.getDeck().remove(0);
			
			if(playerCardOne.getRank() >= 10) {
				player = ZERO;
			} else {
				player = playerCardOne.getRank();
			}
			if(bankerCardOne.getRank() >= 10) {
				banker = ZERO;
			} else {
				banker = bankerCardOne.getRank();
			} if(playerCardTwo.getRank() >= 10) {
				player += ZERO;
			} else {
				player += playerCardTwo.getRank();
			}
			if(bankerCardTwo.getRank() >= 10) {
				banker += ZERO;
			} else {
				banker += bankerCardTwo.getRank();
			}
			  player = player % TEN;
			  banker = banker % TEN;
			  
			  if (player == 8 || player == 9 || banker == 8 || banker == 9) {

				  stopGame = true;
				  
			}else {
				  if (player <= 5) {
					  playerCardThree = deck.getDeck().remove(0);
					  if (playerCardThree.getRank() >= 10) {
						  player += ZERO;
					  }else {
						  player += playerCardThree.getRank();
					  }	  
				  }else if (banker <= 5) {
						  bankerCardThree = deck.getDeck().remove(0);
						  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
							  stopGame = true;
						  }else {
							  banker += bankerCardThree.getRank();
							  stopGame = true;
						  }
				  }else {
					  stopGame = true;
				  }


			  	if (playerCardThree != null) {
			  		if (playerCardThree.getRank() == 2 && playerCardThree.getRank() == 3){
					  	if (banker <= 4){
							  bankerCardThree = deck.getDeck().remove(0);
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }else {
						   stopGame = true;
					   }
				  } else if (playerCardThree.getRank() == 4 || playerCardThree.getRank() == 5){
					  	if (banker <= 5){
							  bankerCardThree = deck.getDeck().remove(0);
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }else {
						   stopGame = true;
					   }
				  } else if (playerCardThree.getRank() == 6 || playerCardThree.getRank() == 7) {
					  	if (banker <= 6){
							  bankerCardThree = deck.getDeck().remove(0);
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }else {
						   stopGame = true;
					   }
				  }else if (playerCardThree.getRank() == 8){
					  	if (banker <= 2){
							  bankerCardThree = deck.getDeck().remove(0);
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }else {
						   stopGame = true;
					   }
				}else if (playerCardThree.getRank() >= 9 || playerCardThree.getRank() == 1) {
					  	if (banker <= 3){
							  bankerCardThree = deck.getDeck().remove(0);
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }else {
						   stopGame = true;
					   }
				  }
			  	}
			}
		}
	

		  	  player = player % TEN;
			  banker = banker % TEN;

			if (player > banker) {
				winner = 'p';
			} else if (banker > player) {
				winner = 'b';
			} else if (banker == player) {
				winner = 't';
				if (choice == 't') {
					betAmount = betAmount * 5;
				}
			}

			if (winner == choice){
				win = true;
				pl.setScore(playerScore + betAmount);
			} else {
				win = false;
				pl.setScore(playerScore - betAmount);
			}

		System.out.format("|%-23s|%-23s|%n", playerCardOne.toString(), bankerCardOne.toString());	
		System.out.println("+-----------------------+-----------------------+");
		System.out.format("|%-23s|%-23s|%n", playerCardTwo.toString(), bankerCardTwo.toString());
		System.out.println("+-----------------------+-----------------------+");
		if(playerCardThree == null && bankerCardThree == null) {
			System.out.println("|                       |                       |");
			System.out.println("+-----------------------+-----------------------+");
			System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
			System.out.println("+=======================+=======================+\n");
		}
		if(playerCardThree != null && bankerCardThree == null) {
			System.out.format("|%-23s|                       |%n", playerCardThree);
			System.out.println("+-----------------------+-----------------------+");
			System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
			System.out.println("+=======================+=======================+\n");
		}
		if(playerCardThree != null && bankerCardThree != null) {
			System.out.format("|%-23s|%-23s|%n", playerCardThree.toString(), bankerCardThree.toString());
			System.out.println("+-----------------------+-----------------------+");
			System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
			System.out.println("+=======================+=======================+\n");
		}
		if(playerCardThree == null && bankerCardThree != null) {
			System.out.format("|                      |%-23s|%n", bankerCardThree);
			System.out.println("+-----------------------+-----------------------+");
			System.out.println("|PLAYER POINTS: " + player + "       |BANKER POINTS: " + banker + "       |");
			System.out.println("+=======================+=======================+\n");
		}
		System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		if(win == true) {
			System.out.println("        $       PLAYER WON " + betAmount + "$        $");
		} else {
			System.out.println("        $      PLAYER LOST " + betAmount + "$        $");
		}
		System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n");
		return win;
	}
}



