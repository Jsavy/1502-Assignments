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
		scan = new Scanner(System.in); // scanner initialization 
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
		int option = 0; // user input for the menu
		boolean isInt; // boolean to make sure user only enters what is required
		System.out.println("How We May Help You?\n");
		System.out.println("(1)   Search Inventory and Purchase Toy");
		System.out.println("(2)   Add New Toy");
		System.out.println("(3)   Remove Toy");
		System.out.println("(4)   Save & Exit \n");
		
		do {
			System.out.print("Enter Option:");
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
		int option = 0; // user input for the menu
		boolean isInt = false; // boolean to make sure user only enters what is required 
		System.out.println("\nFind Toys With: \n");
		System.out.println("(1)   Serial Number(SN)");
		System.out.println("(2)   Toy Name");
		System.out.println("(3)   Type");
		System.out.println("(4)   Back to Main Menu\n");

		do {
			System.out.print("Enter Option:");
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
		String sn = ""; // the serial number from the user
		boolean valid = false; // boolean to make sure the serial is only what is can be
		final int TEN = 10; // the value used for string length 
		
		do {
			System.out.print("\nEnter Serial Number:");
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
		String text; // the toy name the user wishes
		scan.nextLine();
		System.out.print("\nEnter Toy Name:");
		text = scan.nextLine();
		return text;
	}
	/**
	 * Prompts the user to enter the desired brand name
	 * 
	 * @return brand  the user generated brand name for toy
	 */
	public String enterBrand() {
		String brand; // the toy brand the user wishes
		System.out.print("\nEnter Toy Brand:");
		brand = scan.nextLine();
		return brand;
	}
	/**
	 * Prompts the user to enter the desired price of the toy
	 * 
	 * @return price  the user generated price for the toy in string format
	 */
	public double enterPrice() {
		double price = 0; // the price of the toy the user wishes
		boolean isDouble; // boolean to make sure only double is inputted
		
		
		do {
			System.out.print("\nEnter Toy Price:");
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
		int inventory = 0; // the inventory level of the toy the user wishes
		boolean isInt; // boolean used to make sure only Integer entered
		
		
		do {
			System.out.print("\nEnter Available Counts:");
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
		int age = 0; // the recommended minimum age for the toy
		boolean isInt; // boolean used to make sure only Integer entered
		
		do {
			System.out.print("\nEnter Appropriate Age:");
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
		int numPlayer = 0; // the minimum number of players for the board game to function
		boolean isInt; // boolean used to make sure only integer is entered
		do {
			System.out.print("\nEnter Minimum Number of Players:");
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
		int numPlayer = 0; // the maximum number of players for the board game to function
		boolean isInt; // boolean used to make sure only integer is entered
		
		do {
			System.out.print("\nEnter Maximum Number of Players:");
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
		String names; // the names of the designers for the board game
		scan.nextLine();
		System.out.print("\nEnter Designer Names(Use ',' to separate the names if there is more than one name):");
		names = scan.nextLine();
		return names;
	}
	/**
	 * This method allows the user to enter the toy material if the respective toy calls for a fabric type
	 * 
	 * @return material     the material type for the toy that user inputed 
	 */
	public String enterMaterial() {
		String input; // the users input
		String material = ""; // the material type that the user entered formatted accordingly
		boolean valid = false; // used to make sure only the 3 fabric types are used
		String a = "Wooden"; // fabric type of the toy
		String b = "Plastic"; // fabric type of the toy
		String c = "Fabric"; // fabric type of the toy

		scan.nextLine();
		while (!valid) {
			System.out.print("\nPlease enter a material type (Wooden, Plastic or Fabric):");
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
		String input; // the users input
		char size = 'N'; // the first character in the toy size user types to follow database format
		boolean valid = false; // boolean to make sure user enters proper size type
		final int ZERO = 0; // integer used to grab the char of the users string
		
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
		String input = ""; // the users input for toy type 
		boolean valid = false; //boolean used to make sure 
		String a = "Figure"; // reference value used to check validity
		String b = "Animal"; // reference value used to check validity
		String c = "Puzzles"; // reference value used to check validity
		String d = "Board Games"; // reference value used to check validity

		while (!valid) {
			System.out.println("Enter Toy Type (Figure, Animal, Puzzles, Board Games):");
			input = scan.next();

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
		String input; // users input
		char clas = 'n'; // the char of the users input for the type of classification to follow database format
		boolean valid = false; // boolean to make sure its a valid input type
		final int ZERO = 0; // value used to grab the char from user input string
		
		scan.nextLine();
		while (!valid) {
			System.out.print("\nEnter Classification (Action, Doll, or Historic):");
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
		String input; // user input for puzzle type
		char type = 'x'; // the char from the users entry to follow database structure
		boolean valid = false; // boolean value used to check validity
		final int ZERO = 0; // value used to grab the char of the user input 
		
		scan.nextLine();
		while (!valid) {
			System.out.print("\nEnter Puzzle Type (Mechanical, Cryptic, Logic, Trivia, or Riddle):");
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
		final int ZERO = 0; // value used to grab the char
		String input; // the users input 
		char option; // the char from the users input
		
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
		final int THREE = 3; // value used in the loop to cause animation
		final int THOUSAND = 1000; // the pause value for animation
		String text; // the error message if it catches exception
		
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
		int i; // counter value for loop
		int option; // the users option for purchasing
		final int ONE = 1; // used to make the display look human compared to computer
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
		int option; // the users option 
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
	/**
	 * Method to enter the toy name when adding a new toy
	 * 
	 * @return text  the users input
	 */
	public String addToyEnter() {
		String text;
		System.out.print("\nEnter Toy Name:");
		text = scan.nextLine();
		return text;
	}
	/**
	 * Method to enter the serial number to purchase toy
	 * 
	 * @return sn  the serial number the user entered
	 */
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
	/**
	 * Enter Continue method that contains a flush
	 */
	public void enterContinueDouble() {
		System.out.println("\nPress \"Enter\" to continue");
		scan.nextLine();
		scan.nextLine();
	}


}
