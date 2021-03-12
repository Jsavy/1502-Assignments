package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	ArrayList<Toy> toyName;
	ArrayList<Toy> toyType;
	
	
	public GameManager () {
		toys = new ArrayList<Toy>();
		appMen = new AppMenu();
		readFile();
		launchApp();
	}
	
	private void launchApp () {
		
		boolean flag = true; // variable to loop the menu
		int option; // user inputted choice

		appMen.companyHeader(); // Welcome message

		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case 1:
				subMenu(); // show Sub Menu
				flag = false;
				break;
			case 2:
				addToy(); // Allows for user to add a toy to the directory
				flag = false;
				break;
			case 3:
				removeToy(); // Allows for user to remove a toy from the directory
				flag = false;
				break;
			case 4:
				appMen.exitMessage(); // Exit message
				exitFile();
				flag = false;
				break;
			default:
				appMen.mainMenuError();
			}
		}
	}

	private void subMenu() {
		boolean flag = true;
		String sn;
		String name;
		String type;

		while (flag) {
			int option = appMen.showSubMenu();
			switch (option) {
				case 1:
					sn = appMen.enterSerial();
					searchBySN(sn);
					flag = false;
					break;
				case 2:
					name = appMen.enterToy();
					searchByName(name);
					flag = false;
					break;
				case 3:
					type = appMen.enterToyType();
					searchByType(type);
					flag = false;
					break;
				case 4:
					launchApp();
					flag = false;
					break;
				default:
					appMen.mainMenuError();
					break;
			}
		}
	}
	
	private void addToy() {
		Toy t = null;
		String sn;
		String name;
		String brand;
		String designer;
		String numPlayers;
		String material;
		String text;
		double price = 0;
		boolean priceValidity = false;
		boolean playerValidity = false;
		int avaliableCount;
		int ageAppropriate;
		int minPlayer = 0;
		int maxPlayer = 0;
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
		final int SEVEN = 7;
		final int EIGHT = 8;
		final int NINE = 9;
		
		sn = appMen.enterSerial();
		t = searchRemove(sn);
		
		while (t != null) {
			appMen.toyExists();
			sn = appMen.enterSerial();
			t = searchRemove(sn);
		}
			name = appMen.enterToy();
			brand = appMen.enterBrand();
			while(!priceValidity) {
				price = appMen.enterPrice();
				try {
					priceValidity = isPriceValid(price);
				} catch (ToyStoreException e) {
					text = e.getMessage();
					appMen.errorMessage(text);
				}
			}
			avaliableCount = appMen.enterInventory();
			ageAppropriate = appMen.enterMinAge();
			
			
			firstDigit = Character.getNumericValue(sn.charAt(0));
			if (firstDigit == ZERO || firstDigit == ONE) {
				classification = appMen.enterClass();
				Toy f = new Figure (sn, name, brand, price, avaliableCount, ageAppropriate, classification);
				toys.add(f);
			}else if (firstDigit == TWO || firstDigit == THREE) {
				material = appMen.enterMaterial();
				size = appMen.enterSize();
				Toy a = new Animal(sn, name, brand, price, avaliableCount, ageAppropriate, material, size);
				toys.add(a);
			}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
				type = appMen.enterType();
				Toy p = new Puzzle (sn, name, brand, price, avaliableCount, ageAppropriate, type);
				toys.add(p);
			}else if (firstDigit == SEVEN ||  firstDigit == EIGHT || firstDigit == NINE){
				while(!playerValidity) {
					minPlayer = appMen.enterMinPlayer();
					maxPlayer = appMen.enterMaxPlayer();
					try {
						playerValidity = isPlayerValid(minPlayer,maxPlayer);
					} catch (ToyStoreException e) {
						text = e.getMessage();
						appMen.errorMessage(text);
					}
				}
				designer = appMen.enterName();
				numPlayers = minPlayer + "-" + maxPlayer;
				Toy b = new BoardGame(sn, name, brand, price, avaliableCount, ageAppropriate, numPlayers, designer);
				toys.add(b);
			}
		
		
		
		appMen.confirm();

		appMen.enterContinue();		
	
		launchApp();
	}

	

	
	private void removeToy() {
		String serial;
		boolean valid = false;
		char option = 'n';
		Toy t = null;
		
		while (!valid) {
			serial = appMen.enterSerial();
			t = searchRemove(serial);
			if (t == null) {
				appMen.itemNotFound();
				valid = true;
			}else {
				appMen.itemFound();
				System.out.println(t.toString());
				option = appMen.removeToy();
				if (option == 'y') {
					appMen.itemRemove();
					toys.remove(t);
					valid = true;
				}else {
					appMen.itemNotRemove();
					valid = true;
				}
			}
		}

		launchApp();
		
	}
		

	/**
	 * Method which loads the toy data in the file "toys.txt"
	 * 
	 */
	private void exitFile() {
		File file = new File(FILE_PATH);
		PrintWriter pw;
		String text;
			try {
				pw = new PrintWriter(file);
				for (Toy t: toys) {
					if(t instanceof Animal) {
						Animal cast = (Animal)t;
						pw.println(cast.format());
					} else if(t instanceof Figure) {
						Figure cast = (Figure)t;
						pw.println(cast.format());
					} else if(t instanceof Puzzle) {
						Puzzle cast = (Puzzle)t;
						pw.println(cast.format());
					} else if(t instanceof BoardGame) {
						BoardGame cast = (BoardGame)t;
						pw.println(cast.format());
					}
				}
				
				pw.close();
			} catch (FileNotFoundException e) {
				text = e.getMessage();
				appMen.errorMessage(text);
			}
	}
	
	
	/**
	 * A method which loads the file "toys.txt" and adds different types of toys into an arraylist
	 */
	private void readFile() {
		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		String splittedLine1;
		Scanner fileReader = null;
		String text;
		
		try {
			fileReader = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			text = e.getMessage();
			appMen.errorMessage(text);
		}
		int firstDigit;
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
					
					Toy f = new Figure ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(f);
				}else if (firstDigit == TWO || firstDigit == THREE){
					
					Toy a = new Animal ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX], splittedLine[SEVEN].charAt(0));
					toys.add(a);
				}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX){
					
					Toy p = new Puzzle ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							Integer.parseInt(splittedLine[FIVE]), splittedLine[SIX].charAt(ZERO));
					toys.add(p);
				}else if (firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {
					
					Toy b = new BoardGame ((splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
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
		final double ZERO = 0.00;
		if(price < ZERO) {
			valid = false;
			throw new ToyStoreException("Invalid, price of the toy cannot be negative, Inputted value: " + price);
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
		
		if(min > max) {
			valid = false;
			throw new ToyStoreException("Invalid, minimum number of players must be equal"
					+ " or greater than maximum number of players, Inputted minimum: " + min + " Inputted maximum: " + max);
		}
		return valid;
	}

	private Toy searchRemove(String SN) {
		Toy t = null;

		for (Toy tt: toys) {
			if (tt.getSN().equals(SN)) {
				if (tt instanceof Figure) {
					t = (Figure)tt;	
				}else if (tt instanceof Animal) {
					t = (Animal)tt;
				} else if (tt instanceof Puzzle) {
					 t = (Puzzle)tt;
				} else if (tt instanceof BoardGame) {
					t = (BoardGame)tt;
				}
			}
		}
		return t;
	}
	/**
	 * Method that searches the arrayList based off SN input from user
	 * 
	 * @param SN  the serial number the user inputed for search
	 */
	private void searchBySN(String SN) {
		Toy t = searchRemove(SN);
		int option;
		final int ONE = 1;

		if (t == null) {
			appMen.itemNotFound();
		}else {
			option = appMen.printSN(t);
			if (option == 1) {
				if (t.getAvaliableCount() == 1) {
					toys.remove(t);
					appMen.successMessage();
				}else {
					t.setAvaliableCount(t.getAvaliableCount() - 1);
					appMen.successMessage();
				}
			}else {
				subMenu();
			}
		}
		
		appMen.enterContinue();

		subMenu();
	}
	/**
	 * Method that creates a new arrayList based off search criteria the user is looking for and
	 * updates the database ArrayList according to the actions the user selects
	 * 
	 * @param name  the name of toy based off keyword search
	 */
	private void searchByName(String name) {
		toyName = new ArrayList<Toy>();
		String nameLow = name.toLowerCase();
		String resultLow;
		Toy cast = null;
		int option;
		final int ONE = 1;
		
		for(Toy tt: toys) {
			resultLow = tt.getName().toLowerCase();
			if(resultLow.contains(nameLow)) {
				if(tt instanceof Figure) {
					cast = (Figure)tt;
					toyName.add(cast);
				} else if(tt instanceof Animal) {
					cast = (Animal)tt;
					toyName.add(cast);
				} else if(tt instanceof Puzzle) {
					cast = (Puzzle)tt;
					toyName.add(cast);
				} else if(tt instanceof BoardGame) {
					cast = (BoardGame)tt;
					toyName.add(cast);
				}
			}
		}
		for(Toy test: toyName) {
			toys.remove(test);
		}
		option = appMen.printType(toyName);
		if(option < toyName.size()) {
			if(toyName.get(option) instanceof Figure) {
				Figure grab = (Figure)toyName.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyName.remove(option);
				}else {
					toyName.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyName.add(grab);
				}
			}else if(toyName.get(option) instanceof Animal) {
				Animal grab = (Animal)toyName.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyName.remove(option);
				}else {
					toyName.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyName.add(grab);
				}
			}else if(toyName.get(option) instanceof Puzzle) {
				Puzzle grab = (Puzzle)toyName.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyName.remove(option);
				}else {
					toyName.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyName.add(grab);
				}
			}else if(toyName.get(option) instanceof BoardGame) {
				BoardGame grab = (BoardGame)toyName.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyName.remove(option);
				}else {
					toyName.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyName.add(grab);
				}
			}
		}
		
		for(Toy tUpdate: toyName) {
			if(tUpdate instanceof Figure) {
				Figure update = (Figure)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof Animal) {
				Animal update = (Animal)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof Puzzle) {
				Puzzle update = (Puzzle)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof BoardGame) {
				BoardGame update = (BoardGame)tUpdate;
				toys.add(update);
			}
		}
		appMen.enterContinue();

		subMenu();
	}
	/**
	 * Method that creates a new arrayList based off search criteria the user is looking for and
	 * updates the database ArrayList according to the actions the user selects
	 * 
	 * @param types  the type of toy based off instance of search
	 */
	private void searchByType(String types) {
		toyType = new ArrayList<Toy>();
		String a = "Figure";
		String b = "Animal";
		String c = "Puzzles";
		String d = "Board Games";
		int option;
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
		
		
		if(types.equalsIgnoreCase(a)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == ZERO || firstDigit == ONE) {
					Figure cast = (Figure)tt;
					toyType.add(cast);
				}
			}
			for(Toy test: toyType) {
				toys.remove(test);
			}
		}else if(types.equalsIgnoreCase(b)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == TWO || firstDigit == THREE) {
					Animal cast = (Animal)tt;
					toyType.add(cast);
		}
			}
			for(Toy test: toyType) {
				toys.remove(test);
			}
		}else if(types.equalsIgnoreCase(c)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
					Puzzle cast = (Puzzle)tt;
					toyType.add(cast);
				}
			}
			for(Toy test: toyType) {
				toys.remove(test);
			}
		}else if(types.equalsIgnoreCase(d)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {
					BoardGame cast = (BoardGame)tt;
					toyType.add(cast);
				}
			}
			for(Toy test: toyType) {
				toys.remove(test);
			}
		}
		option = appMen.printType(toyType);
		if(option < toyType.size()) {
			if(toyType.get(option) instanceof Figure) {
				Figure grab = (Figure)toyType.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyType.remove(option);
				}else {
					toyType.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyType.add(grab);
				}
			}else if(toyType.get(option) instanceof Animal) {
				Animal grab = (Animal)toyType.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyType.remove(option);
				}else {
					toyType.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyType.add(grab);
				}
			}else if(toyType.get(option) instanceof Puzzle) {
				Puzzle grab = (Puzzle)toyType.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyType.remove(option);
				}else {
					toyType.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyType.add(grab);
				}
			}else if(toyType.get(option) instanceof BoardGame) {
				BoardGame grab = (BoardGame)toyType.get(option);
				if(grab.getAvaliableCount() == ONE) {
					toyType.remove(option);
				}else {
					toyType.remove(option);
					grab.setAvaliableCount(grab.getAvaliableCount() - ONE);
					toyType.add(grab);
				}
			}
		}
		
		for(Toy tUpdate: toyType) {
			if(tUpdate instanceof Figure) {
				Figure update = (Figure)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof Animal) {
				Animal update = (Animal)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof Puzzle) {
				Puzzle update = (Puzzle)tUpdate;
				toys.add(update);
			}else if(tUpdate instanceof BoardGame) {
				BoardGame update = (BoardGame)tUpdate;
				toys.add(update);
			}
		}
		appMen.enterContinue();

		subMenu();
	}
	
}

