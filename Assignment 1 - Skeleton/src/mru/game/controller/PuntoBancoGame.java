package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.view.AppMenu;
/**
 * This class represents the game Punto Banco
 * @author Justin Savenko
 * @author Austin Thieu
 *
 */
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
	
	/**
	 * initialization of objects needed
	 */
	public PuntoBancoGame () {
		appMen = new AppMenu();
		deck = new CardDeck();
		player = new Player("null", 0, 0);
		myCard = new Card(0, "null");
	}
	
	/**
	 * The actual Punto Banco game that hands out 4 cards then determines if more cards are needed based off the rules 
	 * and updates Player info after game is over accordingly
	 * 
	 * @param name         Player object of the current user playing the the game
	 * @param choice       the choice the player selected on who will win the game
	 * @param betAmount    The amount the player decided to bet
	 */
	public void launchGame (Player name, char choice, int betAmount) {
		boolean win = false; // indicates a win for the user
		char winner = 'n'; // indicates if player won, banker won or if it was a tie
		Player pl = name; // user that is playing the game
		final int ZERO = 0; // used to set the cards worth 10 or above to 0
		final int TEN = 10; // used to calculate the score modulo 10
		final int EIGHT = 8; // used for determining if score condition is met with four cards
		final int NINE = 9; // used for determining if score condition is met with four cards
		final int FIVE = 5; // used if four card win condition is not met
		final int TWO = 2; // used if four card win condition is not met
		final int THREE = 3; // used if four card win condition is not met
		final int FOUR = 4; // used if four card win condition is not met
		final int SIX = 6; // used if four card win condition is not met
		final int SEVEN = 7; // used if four card win condition is not met
		final int ONE = 1; // used if four card win condition is not met
		int player = ZERO; // score of the player
		int banker = ZERO; // score of the banker
		Card playerCardOne = null; // variable that holds the first player card drawn
		Card bankerCardOne = null; // variable that holds the first banker card drawn
		Card playerCardTwo = null; // variable that holds the second player card drawn
		Card bankerCardTwo = null; // variable that holds the second banker card drawn
		Card playerCardThree = null; // variable that holds the third player card drawn
		Card bankerCardThree = null; // variable that holds the third banker card drawn

			int playerScore = pl.getScore();
		
			appMen.printBoardHeader();



			
			

			// Handing out the first player card
				if (deck.getDeck().size() == ZERO){
					deck = new CardDeck();
					playerCardOne = deck.getDeck().remove(ZERO);
				}else {
					playerCardOne = deck.getDeck().remove(ZERO);
				}
				
			// Handing out first banker card
				if (deck.getDeck().size() == ZERO) {
					deck = new CardDeck();
					bankerCardOne = deck.getDeck().remove(ZERO);
				}else {
					bankerCardOne = deck.getDeck().remove(ZERO);
				}
				
			// Handing out second player card
				if (deck.getDeck().size() == ZERO) {
					deck = new CardDeck();
					playerCardTwo = deck.getDeck().remove(ZERO);
				}else {
					playerCardTwo = deck.getDeck().remove(ZERO);
				}
				
			// Handing out second banker card
				if (deck.getDeck().size() == ZERO) {
					deck = new CardDeck();
					bankerCardTwo = deck.getDeck().remove(ZERO);	
				}else {
					bankerCardTwo = deck.getDeck().remove(ZERO);
				}	
				
			// Calculating value of first two cards handed out
			if(playerCardOne.getRank() >= TEN) {
				player = ZERO;
			} else {
				player = playerCardOne.getRank();
			}
			
			if(bankerCardOne.getRank() >= TEN) {
				banker = ZERO;
			} else {
				banker = bankerCardOne.getRank();
			}
			
			if(playerCardTwo.getRank() >= TEN) {
			player += ZERO;
			} else {
				player += playerCardTwo.getRank();
			}
			
			if(bankerCardTwo.getRank() >= TEN) {
				banker += ZERO;
			} else {
				banker += bankerCardTwo.getRank();
			}
			  
			  // Calculating score using modulo 10
			  player = player % TEN;
			  banker = banker % TEN;
			  
			  // Stops the game if the player or banker has a score of 8 or 9
			  if (player == EIGHT || player == NINE || banker == EIGHT || banker == NINE) {
	  
				  
			}else {
				 // Player draw if player score is 0-5
				  if (player <= FIVE) {
					  if (deck.getDeck().size() == ZERO) {
						  deck = new CardDeck();
						  playerCardThree = deck.getDeck().remove(ZERO);
					  }else {
						  playerCardThree = deck.getDeck().remove(ZERO);	
					  }
					  if (playerCardThree.getRank() >= TEN) {
						  player += ZERO;
					  }else {
						  player += playerCardThree.getRank();
					  }	
					// Banker draw if player didn't draw and banker score is 0-5  
				  }else if (banker <= FIVE) {
					  if (deck.getDeck().size() == ZERO) {
						  deck = new CardDeck();
						  bankerCardThree = deck.getDeck().remove(ZERO);	
					  }else {
						  bankerCardThree = deck.getDeck().remove(ZERO);
					  }	
						 if (bankerCardThree.getRank() >= TEN) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				  }
				  
			  	if (playerCardThree != null) {
					  // If player draws a 2 or 3 on the third card
			  		if (playerCardThree.getRank() == TWO && playerCardThree.getRank() == THREE){
					  	if (banker <= FOUR){
					  		if (deck.getDeck().size() == ZERO) {
					  			deck = new CardDeck();
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}else {
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}
							  if (bankerCardThree.getRank() >= TEN) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if player draws a 4 or 5 on the third card
				  } else if (playerCardThree.getRank() == FOUR || playerCardThree.getRank() == FIVE){
					  	if (banker <= FIVE){
					  		if (deck.getDeck().size() == ZERO) {
					  			deck = new CardDeck();
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}else {
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}
							  if (bankerCardThree.getRank() >= TEN) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 6 or 7 on the third card
				  } else if (playerCardThree.getRank() == SIX || playerCardThree.getRank() == SEVEN) {
					  	if (banker <= 6){
					  		if (deck.getDeck().size() == ZERO) {
					  			deck = new CardDeck();
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}else {
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}
							  if (bankerCardThree.getRank() >= TEN) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 8 on the third card
				  }else if (playerCardThree.getRank() == EIGHT){
					  	if (banker <= TWO) {	
					  		if (deck.getDeck().size() == ZERO) {
					  			deck = new CardDeck();
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}else {
					  			bankerCardThree = deck.getDeck().remove(ZERO);	
					  		}	
							  if (bankerCardThree.getRank() >= TEN) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				 	  }
					  	// if the player draws 9, 10, Jack, Queen, King, or Ace on the third card
				}else if (playerCardThree.getRank() >= NINE || playerCardThree.getRank() == ONE) {
					  	if (banker <= 3) {
					  		if (deck.getDeck().size() == ZERO) {
					  			deck = new CardDeck();
					  			bankerCardThree = deck.getDeck().remove(0);	
					  		}else {
					  			bankerCardThree = deck.getDeck().remove(0);	
					  		}
							  if (bankerCardThree.getRank() >= 10) {
							  banker += ZERO;
						  }else {
							  banker += bankerCardThree.getRank();
						  }
				   }
			   }
			}
		}
			// calculating score of player and banker using modulo 10
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
					betAmount = betAmount * FIVE;
				}
			}
			
			// If the user wins, a win and money is added to their balance
			// If the user loses, money is deducted from their balance 
			if (winner == choice){
				pl.setScore(playerScore + betAmount);
				pl.setWins(pl.getWins() + ONE);
				win = true;
			} else {
				pl.setScore(playerScore - betAmount);
				win = false;
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




