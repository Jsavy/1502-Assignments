package mru.tsc.model;

public class BoardGame extends Toy {
	
	private int number;
	
	public BoardGame(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
