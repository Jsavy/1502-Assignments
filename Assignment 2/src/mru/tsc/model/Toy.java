package mru.tsc.model;

public abstract class Toy {
	
	private int SN;
	private String name;
	private String brand;
	private double price;
	private int avaliableCount;
	private String ageAppropriate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
