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
	
	
	public void launchGame (Player name, char choice, int betAmount) {
		boolean win = false; // variable returned to indicate if the user won or loss
		boolean stopGame = false;  // variable to stop the game when a condition is satisfied
		char winner = 'n'; // indicates if player won, banker won or if it was a tie
		Player pl = name; // user that is playing the game
		final int ZERO = 0; // used to set the cards worth 10 or above to 0
		final int TEN = 10; // used to calculate the score modulo 10
		int player = ZERO; // score of the player
		int banker = ZERO; // score of the banker
		Card playerCardOne = null; // variable that holds the first player card drawn
		Card bankerCardOne = null; // variable that holds the first banker card drawn
		Card playerCardTwo = null; // variable that holds the second player card drawn
		Card bankerCardTwo = null; // variable that holds the second banker card drawn
		Card playerCardThree = null; // variable that holds the third player card drawn
		Card bankerCardThree = null; // variable that holds the third banker card drawn

			int playerScore = pl.getScore();
		
			System.out.println("Deck Size: ");
			System.out.println(deck.getDeck().size());
			appMen.printBoardHeader();
			
			
			// Handing out the first player card
				playerCardOne = deck.getDeck().remove(0);
				
			// Handing out first banker card
				bankerCardOne = deck.getDeck().remove(0);
				
			// Handing out second player card
				playerCardTwo = deck.getDeck().remove(0);
				
			// Handing out second banker card
				bankerCardTwo = deck.getDeck().remove(0);	
				
			// Calculating value of first two cards handed out
			if(playerCardOne.getRank() >= 10) {
				player = ZERO;
			} else {
				player = playerCardOne.getRank();
			}
			
			if(bankerCardOne.getRank() >= 10) {
				banker = ZERO;
			} else {
				banker = bankerCardOne.getRank();
			}
			
			if(playerCardTwo.getRank() >= 10) {
			player += ZERO;
			} else {
				player += playerCardTwo.getRank();
			}
			
			if(bankerCardTwo.getRank() >= 10) {
				banker += ZERO;
			} else {
				banker += bankerCardTwo.getRank();
			}
			  
			  // Calculating score using modulo 10
			  player = player % TEN;
			  banker = banker % TEN;
			  
			  if (player == 8 || player == 9 || banker == 8 || banker == 9) {
	  
				  
			}else {
				 // Player draw if player score is 0-5
				  if (player <= 5) {
						  playerCardThree = deck.getDeck().remove(0);	
					  if (playerCardThree.getRank() >= 10) {
						  player += ZERO;
					  }else {
						  player += playerCardThree.getRank();
					  }	
					// Banker draw if player didn't draw and banker score is 0-5  
				  }else if (banker <= 5) {
						  bankerCardThree = deck.getDeck().remove(0);	
						 if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
							  stopGame = true;
						  }else {
							  banker += bankerCardThree.getRank();
							  stopGame = true;
						  }
				  }
				  
			  	if (playerCardThree != null) {
					  // If player draws a 2 or 3 on the third card
			  		if (playerCardThree.getRank() == 2 && playerCardThree.getRank() == 3){
					  	if (banker <= 4){
					  			bankerCardThree = deck.getDeck().remove(0);	
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if player draws a 4 or 5 on the third card
				  } else if (playerCardThree.getRank() == 4 || playerCardThree.getRank() == 5){
					  	if (banker <= 5){
					  			bankerCardThree = deck.getDeck().remove(0);	
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 6 or 7 on the third card
				  } else if (playerCardThree.getRank() == 6 || playerCardThree.getRank() == 7) {
					  	if (banker <= 6){ 		
					  			bankerCardThree = deck.getDeck().remove(0);	
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 8 on the third card
				  }else if (playerCardThree.getRank() == 8){
					  	if (banker <= 2) {	  		
					  			bankerCardThree = deck.getDeck().remove(0);	
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 9, 10, Jack, Queen, King, or Ace on the third card
				}else if (playerCardThree.getRank() >= 9 || playerCardThree.getRank() == 1) {
					  	if (banker <= 3) {
					  			bankerCardThree = deck.getDeck().remove(0);	
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				   }
			   }
			}
		}
			player = player % TEN;
			banker = banker % TEN;
			
			
			// Confirming if the user bet on the correct winner
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
				pl.setWins(pl.getWins() + 1);
			} else {
				win = false;
				pl.setScore(playerScore - betAmount);
			}
		
		// Print out for cards being handed out
		appMen.printTwo(playerCardOne, bankerCardOne, playerCardTwo, bankerCardTwo);
		if(playerCardThree == null && bankerCardThree == null) {
			appMen.noThird(player, banker);
		}
		if(playerCardThree != null && bankerCardThree == null) {
			appMen.noThirdBanker(playerCardThree, player, banker);
		}
		if(playerCardThree != null && bankerCardThree != null) {
			appMen.thirdCard(playerCardThree, bankerCardThree, player, banker);
		}
		if(playerCardThree == null && bankerCardThree != null) {
			appMen.noThirdPlayer(bankerCardThree, player, banker);
		}
		// Print out to show how much the user won/loss
		appMen.betHeader();
		if(win == true) {
			appMen.playerWon(betAmount);
		} else {
			appMen.playerLost(betAmount);
		}
		appMen.betFooter();
			}

	}




