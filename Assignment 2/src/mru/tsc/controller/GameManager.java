package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
		Long sn;
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
		String type;
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

		}else if (firstDigit == TWO || firstDigit == THREE) {
			size = appMen.enterSize();
		}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {

		}else {
			minPlayer = appMen.enterMinPlayer();
			maxPlayer = appMen.enterMaxPlayer();
			designer = appMen.enterName();
			numPlayers = minPlayer + "-" + maxPlayer;
		}

		
		
		
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
	private int identifyToy (long sn) {
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
		
		
			while(fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(";");
				firstDigit = Character.getNumericValue(splittedLine[ZERO].charAt(0));
						
				if (firstDigit == ZERO || firstDigit == ONE) {
					
					Figure f = new Figure (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(f);
				}else if (firstDigit == THREE || firstDigit == FOUR){
					
					Animal a = new Animal (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(a);
				}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX){
					
					Puzzle p = new Puzzle (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX]);
					toys.add(p);
				}else {
					
					BoardGame b = new BoardGame (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX], splittedLine[SEVEN]);
					toys.add(b);
				}	
			}
			fileReader.close();
	}
}
