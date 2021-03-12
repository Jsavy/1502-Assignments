package mru.tsc.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Figure;
import mru.tsc.model.Puzzle;
import mru.tsc.model.Toy;

public class AppMenu {
	
	Scanner scan; //Scanner to allow user to use the keyboard
	
	public AppMenu () {
		scan = new Scanner(System.in);
	}
	/**
	 * Prints a welcome banner for the Toy Store Company
	 */
	public void companyHeader() {
		System.out.println("*****************************************************");
		System.out.println("*        WELCOME TO TOY STORE COMPANY!              *");
		System.out.println("*****************************************************\n");
	}
	/**
	 * Prints the main menu that prompts the user for which option they wish to select
	 * 
	 * @return option  the integer the user selected
	 */
	public int showMainMenu() {
		int option = 0;
		boolean isInt;
		System.out.println("How We May Help You?\n");
		System.out.println("(1)   Search Inventory and Purchase Toy");
		System.out.println("(2)   Add New Toy");
		System.out.println("(3)   Remove Toy");
		System.out.println("(4)   Save & Exit \n");
		
		do {
			System.out.print("Enter Option: ");
			if (scan.hasNextInt()) {
				option = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("\nError: Invalid Menu Input\n");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		
		scan.nextLine();
		return option;
	}
	/**
	 * Prints the sub menu that prompts the user for which option they wish to select
	 * 
	 * @return option  the integer the user selected
	 */
	public int showSubMenu() {
		int option = 0;
		boolean isInt = false;;
		System.out.println("\nFind Toys With: \n");
		System.out.println("(1)   Serial Number(SN)");
		System.out.println("(2)   Toy Name");
		System.out.println("(3)   Type");
		System.out.println("(4)   Back to Main Menu\n");

		do {
			System.out.print("Enter Option: ");
			if (scan.hasNextInt()) {
				option = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("\nError: Invalid Menu Input\n");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		
		return option;
	}
	/**
	 * Prompts the user to enter the desired Serial Number
	 * 
	 * @return serial  the user generated integer for serial Number
	 */
	public String enterSerial() {
		String sn = "";
		boolean valid = false;
		final int TEN = 10;
		
		do {
			System.out.print("\nEnter Serial Number: ");
			sn = scan.nextLine();
			
			if (sn.matches("[0-9]+")) {
				if (sn.length() == TEN) {
					valid = true;
				}else {
					System.out.println("The Serial Number's length must be 10 digits!\n");
				}
			}else {
				System.out.println("The serial number must only contain digits!\n");
			}
		} while (!valid);
		
		return sn;
	}
	/**
	 * Prompts the user to enter the desired toy name
	 * 
	 * @return text  the user generated text of the toy name
	 */
	public String enterToy() {
		String text;
		scan.nextLine();
		System.out.print("\nEnter Toy Name: ");
		text = scan.nextLine();
		return text;
	}
	/**
	 * Prompts the user to enter the desired brand name
	 * 
	 * @return brand  the user generated brand name for toy
	 */
	public String enterBrand() {
		String brand;
		System.out.print("\nEnter Toy Brand: ");
		brand = scan.nextLine();
		return brand;
	}
	/**
	 * Prompts the user to enter the desired price of the toy
	 * 
	 * @return price  the user generated price for the toy in string format
	 */
	public double enterPrice() {
		double price = 0;
		boolean isDouble;
		
		
		do {
			System.out.print("\nEnter Toy Price: ");
			if (scan.hasNextDouble()) {
				price = scan.nextDouble();
				isDouble = true;
			}else {
				System.out.println("Error: Invalid Price Input");
				isDouble = false;
				scan.next();
			}
			
		} while (!(isDouble));
		
		return price;
	}
	/**
	 * Prompts the user to enter the desired amount of inventory for the toy
	 * 
	 * @return inventory  the user generated inventory for the toy
	 */
	public int enterInventory() {
		int inventory = 0;
		boolean isInt;
		
		
		do {
			System.out.print("\nEnter Available Counts: ");
			if (scan.hasNextDouble()) {
				inventory = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("Error: Invalid Inventory Input");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		return inventory;
	}
	/**
	 * Prompts the user to enter a recommended age for the toy
	 * 
	 * @return age  the recommended age for the toy
	 */
	public int enterMinAge() {
		int age = 0;
		boolean isInt;
		
		do {
			System.out.print("\nEnter Appropriate Age: ");
			if (scan.hasNextInt()) {
				age = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("Error: Invalid Age Input");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		return age;
	}
	/**
	 * Prompts the user to enter the minimum number of players needed 
	 * for the toy to function
	 * 
	 * @return numPlayer  the amount of players needed 
	 */
	public int enterMinPlayer() {
		int numPlayer = 0;
		boolean isInt;
		do {
			System.out.print("\nEnter Minimum Number of Players: ");
			if (scan.hasNextInt()) {
				numPlayer = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("Error: Invalid Age Input");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		
		return numPlayer;
	}
	/**
	 * Prompts the user to enter the maximum number of players needed
	 * for the toy to still function
	 * 
	 * @return numPlayer  the amount of players needed
	 */
	public int enterMaxPlayer() {
		int numPlayer = 0;
		boolean isInt;
		
		do {
			System.out.print("\nEnter Maximum Number of Players: ");
			if (scan.hasNextInt()) {
				numPlayer = scan.nextInt();
				isInt = true;
			}else {
				System.out.println("Error: Invalid Age Input");
				isInt = false;
				scan.next();
			}
			
		} while (!(isInt));
		return numPlayer;
	}

	/**
	 * Prompts the user to enter the designer names for the toys
	 * 
	 * @return names  all the names of the designers which are seperated by a ','
	 */
	public String enterName() {
		String names;
		scan.nextLine();
		System.out.print("\nEnter Designer Names(Use ',' to separate the names if there is more than one name): ");
		names = scan.nextLine();
		return names;
	}
	/**
	 * This method allows the user to enter the toy material if the respective toy calls for a fabric type
	 * 
	 * @return material     the material type for the toy that user inputed 
	 */
	public String enterMaterial() {
		String input;
		String material = "";
		boolean valid = false;
		String a = "Wooden";
		String b = "Plastic";
		String c = "Fabric";

		scan.nextLine();
		while (!valid) {
			System.out.print("\nPlease enter a material type (Wooden, Plastic or Fabric): ");
			input = scan.nextLine();
			
			if (input.equalsIgnoreCase(a)) {
				material = a;
				valid = true;
			}else if (input.equalsIgnoreCase(b)) {
				material = b;
				valid = true;
			}else if (input.equalsIgnoreCase(c)) {
				material = c;
				valid = true;
			}else {
				System.out.println("Error: Invalid Material Type");
			}
		}
		
		return material;
		
	}
	/**
	 * Prompts the user to enter the size of the animal toy
	 * 
	 * @return size - the size of the animal
	 */
	public char enterSize() {
		String input;
		char size = 'N';
		boolean valid = false;
		final int ZERO = 0;
		
		while (!valid) {
			

			System.out.print("\nEnter Toy Size: ");
			input = scan.nextLine();
			size = Character.toUpperCase(input.charAt(ZERO));

			if (size == 'S' || size == 'M' || size == 'L') {
				valid = true;
			}else {
				System.out.println("Error: Invalid Size Type");
				valid = false;
			}
		}
	

		return size;
	}
	/**
	*	Prompts the user to enter the type of toy and validates the input (Figure, Animal, Puzzles, Board Games)
	*
	*   @return input - the validated type of toy
	*/
	public String enterToyType() {
		String input = "";
		boolean valid = false;
		String a = "Figure";
		String b = "Animal";
		String c = "Puzzles";
		String d = "Board Games";

		while (!valid) {
			System.out.println("Enter Toy Type (Figure, Animal, Puzzles, Board Games): ");
			input = scan.nextLine();

			if (input.equalsIgnoreCase(a)) {
				valid = true;
			}else if (input.equalsIgnoreCase(b)) {
				valid = true;
			}else if (input.equalsIgnoreCase(c)) {
				valid = true;
			}else if (input.equalsIgnoreCase(d)) {
				valid = true;
			}else {
				valid = false;
			}
		}

		return input;
	}
	/**
	 * Prompts the user to enter the classification of the figure
	 * 
	 * @return class - the classification of the figure
	 */
	public char enterClass() {
		String input;
		char clas = 'n';
		boolean valid = false;
		final int ZERO = 0;
		
		scan.nextLine();
		while (!valid) {
			System.out.print("\nEnter Classification (Action, Doll, or Historic): ");
			input = scan.nextLine();
			clas = Character.toUpperCase(input.charAt(ZERO));

			if (clas == 'A' || clas == 'D' || clas == 'H') {
				valid = true;
			}else {
				System.out.println("Error: Invalid Type of Classification");
				valid = false;
			}
		}
		
		return clas;
	}
	/**
	 * Allows the user to enter the type of puzzle for toy puzzles
	 * 
	 * @return type       the character for the type of puzzle
	 */
	public char enterType() {
		String input;
		char type = 'x';
		boolean valid = false;
		final int ZERO = 0;
		
		scan.nextLine();
		while (!valid) {
			System.out.print("\nEnter Puzzle Type (Mechanical, Cryptic, Logic, Trivia, or Riddle): ");
			input = scan.nextLine();
			type = Character.toUpperCase(input.charAt(ZERO));
			
			if (type == 'M' || type == 'C' || type == 'L' || type == 'T' || type == 'R') {
				valid = true;
			}else {
				System.out.println("Error: Invalid Type of Puzzle");
			}
		}
		return type;
	}
	/**
	 * Prints to confirm the toy has been added to the database
	 */
	public void confirm() {
		System.out.println("\nNew Toy Added!");
	}
	/**
	 * Prompts the user to press enter to continue
	 */
	public void enterContinue() {
		System.out.println("\nPress \"Enter\" to continue");
		scan.nextLine();
	}

	/**
	* Finds the toy found by the user's inputted serial number
	*/ 
	public void itemFound() {
		System.out.println("\nThis Item Found: \n");
	}

	/**
	*	Prompts the user to remove the item or not
	*
	*   @return  option - the choice the user inputted
	*/
	public char removeToy() {
		final int ZERO = 0;
		String input;
		char option;
		System.out.print("\nDo you want to remove it (Y/N)? ");
		input = scan.nextLine();
		option = input.toLowerCase().charAt(ZERO);
		return option;
	}
	/**
	 * Message to confirm when the user inputs a SN number that does not correspond to a toy
	 */
	public void itemNotFound() {
		System.out.println("\nItem not found\n");
	}
	/**
	 * Message to confirm the item was removed
	 */
	public void itemRemove() {
		System.out.println("\nItem Removed!\n");
	}
	/**
	 * Message when item was not removed
	 */
	public void itemNotRemove() {
		System.out.println("\nItem not removed!\n");
	}
	/**
	 * Message when user inputs a non-unique SN
	 */
	public void toyExists() {
		System.out.print("\nError: Toy already exists!\n");
	}
	/**
	 * exit message for when the user wishes to exit with animation on the dots
	 */
	public void exitMessage() {
		final int THREE = 3;
		final int THOUSAND = 1000;
		String text;
		System.out.print("\nSaving Data Into Database");
		for (int i=0; i<THREE; i++) {
			try {
				Thread.sleep(THOUSAND);
			} catch (InterruptedException e) {
				text = e.getMessage();
				errorMessage(text);
			}
			System.out.print(".");
		}
		System.out.println("\n\n*********** THANKS FOR VISITING US! ***********");
	}
	/**
	 * Success message for when the user successfully purchases a toy 
	 */
	public void successMessage() {
		System.out.println("\nThe Transaction successfully Terminated!");
	}

	/**
	 * method used to print exception messages
	 * 
	 * @param e      the exception message
	 */
	public void errorMessage(String e) {
		System.out.println(e);
	}
	/**
	 * Method that prints out the arrayList based off search criteria the user inputted
	 * 
	 * @param toyType   the arraylist of the toys the user is searching for
	 * @return option   the option the user selects to purchase or menu
	 */
	public int printType(ArrayList<Toy> toyType) {
		int i;
		int option;
		final int ONE = 1;
		System.out.println("Here are the search results:\n");
		for(i = 0; i < toyType.size(); i++) {
			Toy tp = toyType.get(i);
			if(tp instanceof Figure) {
				Figure cast = (Figure)tp;
				System.out.println("(" + (i + ONE) + ")  " + cast.toString());
			} else if(tp instanceof Animal) {
				Animal cast = (Animal)tp;
				System.out.println("(" + (i + ONE) + ")  " + cast.toString());
			} else if(tp instanceof Puzzle) {
				Puzzle cast = (Puzzle)tp;
				System.out.println("(" + (i + ONE) + ")  " + cast.toString());
			} else if(tp instanceof BoardGame) {
				BoardGame cast = (BoardGame)tp;
				System.out.println("(" + (i + ONE) + ")  " + cast.toString());
			}
		}
		System.out.println("(" + (i + ONE) + ")  " + "Back to Search Menu");
		option = scan.nextInt();
		option = option - ONE;
		return option;
	}

	/**
	 * Method to print toy puzzles
	 * 
	 * @param toy   this toy is of type puzzle that is printing
	 */
	public int printSN(Toy toy) {
		int option;
		System.out.println("(1) " + toy.toString());
		System.out.println("(2) Back to the search menu");
		option = scan.nextInt();
		return option;
	}

	/**
	 * Prints if the user selects an invalid option in the main menu
	 */
	public void mainMenuError() {
		System.out.println("\nError: Invalid Choice, Please try again!\n");
	}
	
	public String enterPurchase() {
		String sn = "";
		boolean valid = false;
		final int TEN = 10;
		scan.nextLine();
		do {
			System.out.print("\nEnter Serial Number: ");
			sn = scan.nextLine();
			
			if (sn.matches("[0-9]+")) {
				if (sn.length() == TEN) {
					valid = true;
				}else {
					System.out.println("The Serial Number's length must be 10 digits!\n");
				}
			}else {
				System.out.println("The serial number must only contain digits!\n");
			}
		} while (!valid);
		
		return sn;
	}
	public void enterContinueDouble() {
		System.out.println("\nPress \"Enter\" to continue");
		scan.nextLine();
		scan.nextLine();
	}


}
