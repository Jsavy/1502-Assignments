package mru.tsc.controller;

import java.util.ArrayList;

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
		
		validateSN();
		
		
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
				System.out.println("The serial nuumber must only contain digits");
			}
			
		}while (!valid);
		
		System.out.println("The accepted SN is: " + sn);
		return sn;
	}
	
	private void removeItem() {
		
	}
}
