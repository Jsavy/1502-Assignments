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
		
		boolean flag = true;
		int option;
		
		while (flag) {
			option = appMen.showMainMenu();
			switch (option) {
			case 1:
				
				flag = false;
				break;
			case 2:
				addToy();
				flag = false;
				break;
			case 3:
				removeToy();
				flag = false;
				break;
			case 4:
				exitFile();
				flag = false;
				break;
			default:
				appMen.mainMenuError();
				
			}
		}
	}

	private void subMenu() {
		int option = appMen.showSubMenu();
		boolean flag = true;
		String sn;
		String name;
		String type;

		while (flag) {
			switch (option) {
				case 1:
					sn = appMen.validateSN();
					searchBySN(sn);
					break;
				case 2:
					name = appMen.enterName();
					searchByName(name);
					break;
				case 3:
					type = appMen.enterToyType();
					searchByType(type);
					break;
				case 4:
					launchApp();
					break;
				default:
					appMen.mainMenuError();
					break;
			}
		}
	}
	
	private void addToy() {
		String sn;
		String name;
		String brand;
		String designer;
		String numPlayers;
		String material;
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
		final int SEVEN = 7;
		final int EIGHT = 8;
		final int NINE = 9;
		
		sn = appMen.validateSN();
		name = appMen.enterToy();
		brand = appMen.enterBrand();
		price = appMen.enterPrice();
		avaliableCount = appMen.enterInventory();
		ageAppropriate = appMen.enterMinAge();
		
		firstDigit = Character.getNumericValue(sn.charAt(0));
		if (firstDigit == ZERO || firstDigit == ONE) {
			classification = appMen.enterClass();
			Toy f = new Figure (sn, name, brand, price, avaliableCount, ageAppropriate, classification);
			toys.add(f);
		}else if (firstDigit == TWO || firstDigit == THREE) {
			size = appMen.enterSize();
			material = appMen.enterMaterial();
			Toy a = new Animal(sn, name, brand, price, avaliableCount, ageAppropriate, material, size);
		}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX) {
			type = appMen.enterType();
			Toy p = new Puzzle (sn, name, brand, price, avaliableCount, ageAppropriate, type);
		}else if (firstDigit == SEVEN ||  firstDigit == EIGHT || firstDigit == NINE){
			minPlayer = appMen.enterMinPlayer();
			maxPlayer = appMen.enterMaxPlayer();
			designer = appMen.enterName();
			numPlayers = minPlayer + "-" + maxPlayer;
			Toy b = new BoardGame(sn, name, brand, price, avaliableCount, ageAppropriate, numPlayers, designer);
		}
		
		appMen.confirm();

		appMen.enterContinue();
		
		launchApp();
	}

	

	
	private void removeToy() {
		String serial;
		boolean valid = false;
		int firstDigit;
		char option = 'n';
		Toy t = null;

		while (!valid) {
			serial = appMen.validateSN();
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
		try {
			fileReader = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	private void searchBySN(String SN) {
		
		for(Toy tt: toys) {
			if (tt.getSN().equals(SN)) {
				if (tt instanceof Figure) {
					Figure t = (Figure)tt;	
					appMen.printSNFigure(t);
				}else if (tt instanceof Animal) {
					Animal t = (Animal)tt;
					appMen.printSNAnimal(t);
				} else if (tt instanceof Puzzle) {
					 Puzzle t = (Puzzle)tt;
					 appMen.printSNPuzzle(t);
				} else if (tt instanceof BoardGame) {
					 BoardGame t = (BoardGame)tt;
					 appMen.printSNBoardGame(t);
				}
			}
		}
		
	}
	
	private void searchByName(String name) {
		toyName = new ArrayList<Toy>();
		String nameLow = name.toLowerCase();
		String resultLow;
		int option;
		
		for(Toy tt: toys) {
			resultLow = tt.getName().toLowerCase();
			if(nameLow.contains(resultLow)) {
				if(tt instanceof Figure) {
					Figure cast = (Figure)tt;
					toyName.add(cast);
				} else if(tt instanceof Animal) {
					Animal cast = (Animal)tt;
					toyName.add(cast);
				} else if(tt instanceof Puzzle) {
					Puzzle cast = (Puzzle)tt;
					toyName.add(cast);
				} else if(tt instanceof BoardGame) {
					BoardGame cast = (BoardGame)tt;
					toyName.add(cast);
				}
			}
		}
		option = appMen.printType(toyName);
	}
	
	private void searchByType(String types) {
		toyType = new ArrayList<Toy>();
		String a = "Figure";
		String b = "Animal";
		String c = "Puzzles";
		String d = "Board Games";
		int option;
		
		
		if(types.equalsIgnoreCase(a)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(0));
				if(firstDigit == 0 || firstDigit == 1) {
					Figure cast = (Figure)tt;
					toyType.add(cast);
				}
			}
		}else if(types.equalsIgnoreCase(b)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(0));
				if(firstDigit == 2 || firstDigit == 3) {
					Animal cast = (Animal)tt;
					toyType.add(cast);
		}
}	
		}else if(types.equalsIgnoreCase(c)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(0));
				if(firstDigit == 4 || firstDigit == 5 || firstDigit == 6) {
					Puzzle cast = (Puzzle)tt;
					toyType.add(cast);
				}
			}
		}else if(types.equalsIgnoreCase(d)) {
			for(Toy tt: toys) {
				int firstDigit = Character.getNumericValue(tt.getSN().charAt(0));
				if(firstDigit == 7 || firstDigit == 8 || firstDigit == 9) {
					BoardGame cast = (BoardGame)tt;
					toyType.add(cast);
				}
			}
		}
		option = appMen.printType(toyType);
		
	
}
}
