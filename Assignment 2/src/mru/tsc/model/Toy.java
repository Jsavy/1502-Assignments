package mru.tsc.model;

public abstract class Toy {
	
	private int SN;
	private String name;
	private String brand;
	private double price;
	private int avaliableCount;
	private String ageAppropriate;

	public int getSN() {
		return SN;
	}

	public void setSN(int sN) {
		SN = sN;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvaliableCount() {
		return avaliableCount;
	}

	public void setAvaliableCount(int avaliableCount) {
		this.avaliableCount = avaliableCount;
	}

	public String getAgeAppropriate() {
		return ageAppropriate;
	}

	public void setAgeAppropriate(String ageAppropriate) {
		this.ageAppropriate = ageAppropriate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
