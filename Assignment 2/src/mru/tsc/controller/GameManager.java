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
	/**
	*	This method assists in the proccessing of user inputs from the main menu
	*
	*/
	private void launchApp () {
		
		boolean flag = true; // variable to loop the menu
		int option; // user inputted choice
		final int ONE = 1; // used by the switch case to eliminate magic number
		final int TWO = 2; // used by the switch case to eliminate magic number
		final int THREE = 3; // used by the switch case to eliminate magic number
		final int FOUR = 4; // used by the switch case to eliminate magic number

		appMen.companyHeader(); // Welcome message

		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case ONE:
				subMenu(); // show Sub Menu
				flag = false;
				break;
			case TWO:
				addToy(); // Allows for user to add a toy to the directory
				flag = false;
				break;
			case THREE:
				removeToy(); // Allows for user to remove a toy from the directory
				flag = false;
				break;
			case FOUR:
				appMen.exitMessage(); // Exit message
				exitFile(); // Saves and exits the program
				flag = false;
				break;
			default:
				appMen.mainMenuError(); // default error message
			}
		}
	}

	private void subMenu() {
		boolean flag = true; // looping menu
		String sn; // user inputed serial number
		String name; // user inputed toy name
		String type; // user inputed toy type
		int option; // user inputed choice
		final int ONE = 1; // used by the switch case
		final int TWO = 2;
		final int THREE = 3;
		final int FOUR = 4;

		while (flag) {
			option = appMen.showSubMenu(); // choice the user makes
			switch (option) {
				case ONE: // search toy using serial number
					sn = appMen.enterPurchase();
					searchBySN(sn);
					flag = false;
					break;
				case TWO: // search toy using toy name
					name = appMen.enterToy();
					searchByName(name);
					flag = false;
					break;
				case THREE: // search toy using type of toy
					type = appMen.enterToyType();
					searchByType(type);
					flag = false;
					break;
				case FOUR: // back to main menu
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
		String sn, name , brand, designer, numPlayers, material, text;
		double price; // price element of a toy
		boolean priceValidity = false;
		boolean playerValidity = false;
		int avaliableCount, ageAppropriate; // elements of toy
		int minPlayer, maxPlayer; // minimum and maximum amount of players in a board game
		int firstDigit; // identification digit of a serial number
		char size, classification, type;
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
		
		price = ZERO;
		minPlayer = ZERO;
		maxPlayer = ZERO;

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

		appMen.enterContinue();

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
		Scanner fileReader = null;
		String text;
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
		
		try {
			fileReader = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			text = e.getMessage();
			appMen.errorMessage(text);
		}

	
		
		
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
		
		appMen.enterContinueDouble();

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
		Toy t = null;
		int option;
		final int ONE = 1;
		
		for(Toy tt: toys) {
			resultLow = tt.getName().toLowerCase();
			if(resultLow.contains(nameLow)) {
				if(tt instanceof Figure) {
					t = (Figure)tt;
					toyName.add(t);
				} else if(tt instanceof Animal) {
					t = (Animal)tt;
					toyName.add(t);
				} else if(tt instanceof Puzzle) {
					t = (Puzzle)tt;
					toyName.add(t);
				} else if(tt instanceof BoardGame) {
					t = (BoardGame)tt;
					toyName.add(t);
				}
			}
		}
		option = appMen.printType(toyName);
		if (option < toyName.size()) {
			t = searchRemove(toyName.get(option).getSN());

			if (t.getAvaliableCount() == 1) {
				toys.remove(t);
				appMen.successMessage();
			}else {
				t.setAvaliableCount(t.getAvaliableCount() - ONE);
				appMen.successMessage();
			}
		}else {
			subMenu();
		}

		appMen.enterContinueDouble();

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
		Toy t = null;
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
					t = (Figure)tt;
					toyType.add(t);
				}
			}
		}else if(types.equalsIgnoreCase(b)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == TWO || firstDigit == THREE) {
					t = (Animal)tt;
					toyType.add(t);
				}
			}
		}else if(types.equalsIgnoreCase(c)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
					t = (Puzzle)tt;
					toyType.add(t);
				}
			}

		}else if(types.equalsIgnoreCase(d)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(ZERO));
				if(firstDigit == SEVEN || firstDigit == EIGHT || firstDigit == NINE) {
					t = (BoardGame)tt;
					toyType.add(t);
				}
			}
		}
		option = appMen.printType(toyType);
		if (option < toyType.size()) {
			t = searchRemove(toyType.get(option).getSN());
			if (t.getAvaliableCount() == 1) {
				toys.remove(t);
				appMen.successMessage();
			}else {
				t.setAvaliableCount(t.getAvaliableCount() - ONE);
				appMen.successMessage();
			}
		}else {
			subMenu();
		}
		
		appMen.enterContinueDouble();

		subMenu();
	}
	
}

