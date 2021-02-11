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
		
		appMen.printBoardHeader();
		

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
			
			 player = player % TEN;
			 banker = banker % TEN;

			if (winner == choice){
				win = true;
				pl.setScore(playerScore + betAmount);
			} else {
				win = false;
				pl.setScore(playerScore - betAmount);
			}

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
		appMen.betHeader();
		if(win == true) {
			appMen.playerWon(betAmount);
		} else {
			appMen.playerLost(betAmount);
		}
		appMen.betFooter();
		return win;
	}
}



