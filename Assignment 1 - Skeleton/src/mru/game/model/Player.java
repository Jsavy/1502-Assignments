package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	
	private String name;
	private int score;
	private int wins;
	
	public Player(String name, int score, int wins) {
		this.name = name;
		this.score = score;
		this.wins = wins;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
}
