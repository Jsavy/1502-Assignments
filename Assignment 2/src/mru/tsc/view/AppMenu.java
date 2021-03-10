package mru.tsc.view;

import java.util.Scanner;

import mru.tsc.exceptions.ToyStoreException;
import mru.tsc.model.Toy;

public class AppMenu {
	
	Scanner scan;
	
	public AppMenu () {
		scan = new Scanner(System.in);
	}
	/**
	 * Prints a welcome banner for the Toy Store Company
	 */
	public void companyHeader() {
		System.out.println("*****************************************************");
		System.out.println("*        WELCOME TO TOY STORE COMPANY!              *");
		System.out.println("*****************************************************/n");
	}
	/**
	 * Prints the main menu that prompts the user for which option they wish to select
	 * 
	 * @return option  the integer the user selected
	 */
	public int showMainMenu() {
		int option;
		System.out.println("How We May Help You?/n");
		System.out.println("(1)   Search Inventory and Purchase Toy");
		System.out.println("(2)   Add New Toy");
		System.out.println("(3)   Remove Toy");
		System.out.println("(4)   Save & Exit \n");
		System.out.print("Enter Option:");
		option = scan.nextInt();
		return option;
	}
	/**
	 * Prints the sub menu that prompts the user for which option they wish to select
	 * 
	 * @return option  the integer the user selected
	 */
	public int showSubMenu() {
		int option;
		System.out.println("/nFind Toys With: \n");
		System.out.println("(1)   Serial Number(SN)");
		System.out.println("(2)   Toy Name");
		System.out.println("(3)   Type");
		System.out.println("(4)   Back to Main Menu/n");
		System.out.print("Enter Option:");
		option = scan.nextInt();
		return option;
	}
	/**
	 * Prompts the user to enter the desired toy name
	 * 
	 * @return text  the user generated text of the toy name
	 */
	public String enterToy() {
		String text;
		System.out.print("/nEnter Toy Name:");
		text = scan.nextLine();
		return text;
	}
	/**
	 * Prompts the user to enter the desired Serial Number
	 * 
	 * @return serial  the user generated integer for serial Number
	 */
	public Long enterSerial() {
		Long serial;
		System.out.print("/nEnter Serial Number:");
		serial = scan.nextLong();
		return serial;
	}
	/**
	 * Prompts the user to enter the desired brand name
	 * 
	 * @return brand  the user generated brand name for toy
	 */
	public String enterBrand() {
		String brand;
		System.out.print("/nEnter Toy Brand:");
		brand = scan.nextLine();
		return brand;
	}
	/**
	 * Prompts the user to enter the desired price of the toy
	 * 
	 * @return price  the user generated price for the toy in string format
	 */
	public double enterPrice() {
		double price;
		System.out.print("/nEnter Toy Price:");
		price = scan.nextDouble();
		return price;
	}
	/**
	 * Prompts the user to enter the desired amount of inventory for the toy
	 * 
	 * @return inventory  the user generated inventory for the toy
	 */
	public int enterInventory() {
		int inventory;
		System.out.print("/nEnter Available Counts:");
		inventory = scan.nextInt();
		return inventory;
	}
	/**
	 * Prompts the user to enter a recommended age for the toy
	 * 
	 * @return age  the recommended age for the toy
	 */
	public int enterMinAge() {
		int age;
		System.out.print("/nEnter Appropriate Age:");
		age = scan.nextInt();
		return age;
	}
	/**
	 * Prompts the user to enter the minimum number of players needed 
	 * for the toy to function
	 * 
	 * @return numPlayer  the amount of players needed 
	 */
	public int enterMinPlayer() {
		int numPlayer;
		System.out.print("/nEnter Minimum Number of Players: ");
		numPlayer = scan.nextInt();
		return numPlayer;
	}
	/**
	 * Prompts the user to enter the maximum number of players needed
	 * for the toy to still function
	 * 
	 * @return numPlayer  the amount of players needed
	 */
	public int enterMaxPlayer() {
		int numPlayer;
		System.out.print("/nEnter Maximum Number of Players: ");
		numPlayer = scan.nextInt();
		return numPlayer;
	}

	/**
	 * Prompts the user to enter the designer names for the toys
	 * 
	 * @return names  all the names of the designers which are seperated by a ','
	 */
	public String enterName() {
		String names;
		System.out.print("/nEnter Designer Names(Use ',' to separate the names if there is more than one name) ");
		names = scan.nextLine();
		return names;
	}
	/**
	 * Prompts the user to enter the size of the animal toy
	 * 
	 * @return size - the size of the animal
	 */
	public char enterSize() {
		String input;
		char size;
		System.out.println("\nEnter Toy Size: ");
		input = scan.nextLine();
		size = Character.toUpperCase(input.charAt(0));
		return size;
	}
	/**
	 * Prompts the user to enter the classification of the figure
	 * 
	 * @return class - the classification of the figure
	 */
	public char enterClass() {
		String input;
		char clas;
		System.out.println("\nEnter Classification: ");
		input = scan.nextLine();
		clas = Character.toUpperCase(input.charAt(0));
		return clas;
	}
	public char enterType() {
		String input;
		char type;
		System.out.println("\nEnter Puzzle Type: ");
		input = scan.nextLine();
		type = Character.toUpperCase(input.charAt(0));
		return type;
	}
	/**
	 * Prints to confirm the toy has been added to the database
	 */
	public void confirm() {
		System.out.println("New Toy Added!");
	}
	/**
	 * Prompts the user to press enter to continue
	 */
	public void enterContinue() {
		System.out.println("Press \"Enter\" to continue");
		scan.nextLine();
	}

	/**
	 * exit message for when the user wishes to exit with animation on the dots
	 */
	public void exitMessage() {
		System.out.print("/n Saving Data Into Database");
		for (int i=0; i<3; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println("/n/n*********** THANKS FOR VISITING US! ***********");
	}
	/**
	 * Success message for when the user successfully purchases a toy 
	 */
	public void successMessage() {
		System.out.println("/nThe Transaction successfully Terminated!");
	}
	/**
	 * Method which validates the user inputted serial number
	 * 
	 * @return sn - the validated sn number
	 */
	public Long validateSN() {
		Long sn = null;
		boolean valid = false;
	
		do {
			sn = enterSerial();
			
			if (sn.matches("[0-9]+")) {
				if (sn == 10) {
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
	/**
	 * method used to print exception messages
	 * 
	 * @param e      the exception message
	 */
	public void errorMessage(String e) {
		System.out.println(e);
	}
}
