package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.tsc.exceptions.ToyStoreException;
import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;
import mru.tsc.view.AppMenu;

public class GameManager {
	
	private static final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toys;
	AppMenu appMen;
	
	
	public GameManager () {
		toys = new ArrayList<Toy>();
		appMen = new AppMenu();
		readFile();
		launchApp();
	}
	
	private void launchApp () {
		
		boolean flag = true;
		int option;
		
		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case '1':
				
				flag = false;
				break;
			case '2':
				addToy();
				flag = false;
				break;
			case '3':
				removeToy();
				flag = false;
				break;
			case '4':
				exitFile();
			default:
			}
		}
	}
	
	private void addToy() {
		String sn;
		String name;
		String brand;
		String designer;
		String numPlayers;
		double price;
		int avaliableCount;
		int ageAppropriate;
		int minPlayer;
		int maxPlayer;
		int firstDigit;
		char size;
		char classification;
		char type;
		final int ZERO = 0;
		final int ONE = 1;
		final int TWO = 2;
		final int THREE = 3;
		final int FOUR = 4;
		final int FIVE = 5;
		final int SIX = 6;
		
		sn = appMen.validateSN();
		name = appMen.enterToy();
		brand = appMen.enterBrand();
		price = appMen.enterPrice();
		avaliableCount = appMen.enterInventory();
		ageAppropriate = appMen.enterMinAge();
		
		firstDigit = identifyToy(sn);
		if (firstDigit == ZERO || firstDigit == ONE) {
			classification = appMen.enterClass();
			Figure f = new Figure (sn, name, brand, price, avaliableCount, ageAppropriate, classification);
			toys.add(f);
		}else if (firstDigit == TWO || firstDigit == THREE) {
			size = appMen.enterSize();
			Animal a = new Animal(sn, name, brand, price, avaliableCount, ageAppropriate, size);
		}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
			type = appMen.enterType();
			Puzzle p = new Puzzle (sn, name, brand, price, avaliableCount, ageAppropriate, type);
		}else {
			minPlayer = appMen.enterMinPlayer();
			maxPlayer = appMen.enterMaxPlayer();
			designer = appMen.enterName();
			numPlayers = minPlayer + "-" + maxPlayer;
			BoardGame b = new BoardGame(sn, name, brand, price, avaliableCount, ageAppropriate, numPlayers, designer);
		}
		
		appMen.confirm();

		appMen.enterContinue();
		
		
	}

	

	
	private void removeToy() {
		long sn;
	}
	/**
	 * Method which searches for the inputted serial number in the arraylist
	 * 
	 * @param serialNumber
	 * @return toy - the object toy in the arraylist
	 */
	private Toy searchSerialNumber(long serialNumber) {
		Toy toy = null;
		
		for (Toy t: toys) {
			if (t.getSN() == serialNumber) {
				toy = t;
			}
		}
		
		return toy;
	}
	/**
	 * Method which searches the ArrayList based on the user-inputted name
	 * 
	 * @param name
	 * @return toy - the object toy in the ArrayList
	 */
	private Toy searchName (String name) {
		Toy toy = null;
		
		for (Toy t: toys) {
			if (t.getName().equalsIgnoreCase(name)) {
				toy = t;
			}
		}
		return toy;
	}
	/**
	 * Method to find the first digit of the serial number
	 * 
	 * @param sn - serial number of toy
	 * @return first - first digit of serial number
	 */
	private int identifyToy (String sn) {
		int first;
		final int TEN = 10;

		first = (int)sn;
		while (first >= TEN) {
			first = first / 10;
		}
		return first;
	}
	/**
	 * Method which loads the toy data in the file "toys.txt"
	 * 
	 */
	private void exitFile() {
		File file = new File(FILE_PATH);
		PrintWriter pw;
			try {
				pw = new PrintWriter(file);
				for (Toy t: toys) {
					pw.println(t.format());
				}
				
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * A method which loads the file "toys.txt" and adds different types of toys into an arraylist
	 */
	private void readFile() {
		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		Scanner fileReader = new Scanner(FILE_PATH);
		long firstDigit;
		final int ZERO = 0;
		final int ONE = 1;
		final int TWO = 2;
		final int THREE = 3;
		final int FOUR = 4;
		final int FIVE = 5;
		final int SIX = 6;
		final int SEVEN = 7;
		final int EIGHT = 8;
		final int NINE = 9;
		
		
			while(fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(";");
				firstDigit = Character.getNumericValue(splittedLine[ZERO].charAt(0));
						
				if (firstDigit == ZERO || firstDigit == ONE) {
					
					Figure f = new Figure ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(f);
				}else if (firstDigit == TWO || firstDigit == THREE){
					
					Animal a = new Animal ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(a);
				}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX){
					
					Puzzle p = new Puzzle ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(p);
				}else if (firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {
					
					BoardGame b = new BoardGame ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX], splittedLine[SEVEN]);
					toys.add(b);
				}	
			}
			fileReader.close();
	}
	/**
	 * Validation class that uses the created ToyStoreException class to throw error message if the price is negative
	 * 
	 * @param price               the price the user inputed for the toy
	 * @return valid              the boolean value of validity
	 * @throws ToyStoreException  exception class used to throw exception
	 */
	public boolean isPriceValid(double price) throws ToyStoreException {
		boolean valid = true;
		String text;
		
		try {
			if(price < 0.00) {
				throw new ToyStoreException("Invalid, the price cannot be negative");
			}
		}catch(ToyStoreException e) {
			text = e.getMessage();
			appMen.errorMessage(text);
			valid = false;
		}
		return valid;
	}
	/**
	 * Validation class that uses the created ToyStoreException class to throw error message if minimum is greater
	 * than maximum
	 * 
	 * @param min                  the minimum number of players user inputed for toy
	 * @param max                  the maximum number of players user inputed for toy
	 * @return valid               the boolean value of validity
	 * @throws ToyStoreException   exception class used to throw exception
	 */
	public boolean isPlayerValid(int min, int max) throws ToyStoreException{
		boolean valid = true;
		String text;
		
		try {
			if(min > max) {
				throw new ToyStoreException("Invalid, the minimum number of players cannot be greater than maximum");
			}
		}catch(ToyStoreException e) {
			text = e.getMessage();
			appMen.errorMessage(text);
			valid = false;
		}
		return valid;
	}
	
	private Toy searchBySN(String SN) {
		Toy t = null;
		int firstDigit;
		firstDigit = Character.getNumericValue(SN.charAt(0));
		for(Toy tt: toys) {
			if (tt.getSN().equals(SN)) {
				if (firstDigit == 0 || firstDigit == 1) {
					t = (Figure)tt;
				}else if (firstDigit == 2 || firstDigit == 3) {
					t = (Animal)tt;
				} else if (firstDigit == 4 || firstDigit == 5 || firstDigit == 6) {
					t = (Puzzle)tt;
				} else if (firstDigit == 7 || firstDigit == 8 || firstDigit == 9) {
					t = (BoardGame)tt;
				}
			}
		}
		return t;
	}
}
