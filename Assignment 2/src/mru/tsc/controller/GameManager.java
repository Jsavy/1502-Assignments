package mru.tsc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Toy;
import mru.tsc.view.AppMenu;

public class GameManager {
	
	private static final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toys = new ArrayList<>();
	AppMenu appMen;
	
	
	public GameManager () {
		appMen = new AppMenu();
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
		
		name = validateSN();
		
		
	}
	
	private String validateSN() {
		String sn = "";
		boolean valid = false;
	
		do {
			sn = appMen.enterSerial();
			
			if (sn.matches("[0-9]+")) {
				if (sn.length() == 10) {
					valid = true;
				}else {
					System.out.println("The serial number's length must be 10 digits");
				}
			}else {
				System.out.println("The serial number must only contain digits");
			}
			
		}while (!valid);
		
		System.out.println("The accepted SN is: " + sn);
		return sn;
	}
	
	private void removeItem() {
		
	}
	
	private void readFile() {
		File myFile = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		Scanner fileReader;
		
			while(fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(";");
				if (splittedLine[0].charAt(0) == 0 || splittedLine[0].charAt(0) == 1) {
					
				}else if (splittedLine[0].charAt(0) == 2 || splittedLine[0].charAt(0) == 3){
					
				}else if (splittedLine[0].charAt(0) == 4 || splittedLine[0].charAt(0) == 5 || splittedLine[0].charAt(0) == 6){
					
				}else {
					
				}
				
				
			}
			
	}
}
