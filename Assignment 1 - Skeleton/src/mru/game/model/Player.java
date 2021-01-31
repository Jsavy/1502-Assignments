package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	
	private String name;  // the name of the player
	private int score;    // the score of the player
	private int wins;     // the amount of wins for the player
	/**
	 * Constructor for new Player objects to be created
	 * @param name    The first name of the player
	 * @param score   The score of the player 
	 * @param wins    The number of wins the player has
	 */
	public Player(String name, int score, int wins) {
		this.name = name;
		this.score = score;
		this.wins = wins;
	}
	/**
	 * Getter method that returns the players name
	 * @return  name   The players name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter method that returns the score of the player
	 * @return  score  The players score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Getter method that returns the amount of wins of the player
	 * @return  wins   The players amount of wins
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * Setter method to change the players name
	 * @param name  players first name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Setter method to change the players score
	 * @param score  players score in the game
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Setter method to change the wins a player has
	 * @param wins  amount of wins a player has 
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}
	

	public String format() {
		return name + "," + score + "," + wins;
	}
}

