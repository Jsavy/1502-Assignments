package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
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
				
				flag = false;
				break;
			default:
			}
		}
	}
	
	private void addToy() {
		String name;
		String brand;
		double price;
		int avaliableCount;
		String ageAppropriate;
		
		name = appMen.validateSN();
		
		
	}

	

	
	private void removeToy() {
		String sn;
		sn = appMen.validateSN();
	}
	
	private void searchSerialNumber() {
		
	}
	/**
	 * A method which loads the file "toys.txt" and adds different types of toys into an arraylist
	 */
	private void readFile() {
		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		Scanner fileReader = new Scanner(FILE_PATH);
		int firstDigit;
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
							splittedLine[FIVE], splittedLine[SIX].charAt(ZERO));
					toys.add(f);
				}else if (firstDigit == THREE || firstDigit == FOUR){
					
					Animal a = new Animal (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							splittedLine[FIVE], splittedLine[SIX].charAt(ZERO));
					toys.add(a);
				}else if (firstDigit == FOUR || firstDigit == FIVE || firstDigit == SIX){
					
					Puzzle p = new Puzzle (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							splittedLine[FIVE], splittedLine[SIX]);
					toys.add(p);
				}else {
					
					BoardGame b = new BoardGame (Long.parseLong(splittedLine[ZERO]), splittedLine[ONE], splittedLine[TWO], 
							Double.parseDouble(splittedLine[THREE]), Integer.parseInt(splittedLine[FOUR]), 
							splittedLine[FIVE], splittedLine[SIX], splittedLine[SEVEN]);
					toys.add(b);
				}
				
				
			}
			
	}
}
