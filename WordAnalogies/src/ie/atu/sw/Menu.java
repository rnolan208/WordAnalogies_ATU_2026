package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    
    public void start() {
        while (running) {
            displayMenu();
            handleChoice();
        }
}

    
    private void displayMenu() {
    	
    	System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*  Word Analogies with Vector Arithmetic & Virtual Threads *");
		System.out.println("*                                                          *");
		System.out.println("*               Robert Nolan - G00474404                   *");
		System.out.println("************************************************************");
		System.out.println("(1) Enter Path to Embeddings File>");
		System.out.println("(2) Enter Vector Operation>");
		System.out.println("(3) Configure Options");
		System.out.println("(4) Specify Output File (default: ./out.txt)");
		System.out.println("(?) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	    System.out.print("Select Option [1-4]> ");
	    System.out.println();

	    // Reset colour after
	    System.out.print(ConsoleColour.RESET);
    }

    
    private void handleChoice() {
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> setEmbeddingPath();
            case "2" -> enterOperation();
            case "3" -> configureOptions();
            case "4" -> setOutputFile();
            case "?" -> exit();
            default -> System.out.println("Invalid option.");
        }
    }

    private void setEmbeddingPath() {
        System.out.print("Enter file path: ");
        String path = scanner.nextLine();
        System.out.println("Path set to: " + path);
    }

    private void enterOperation() {
        System.out.print("Enter operation (e.g. king - man + woman): ");
        String op = scanner.nextLine();
        System.out.println("Operation: " + op);
    }

    private void configureOptions() {
        System.out.println("Options not implemented yet.");
    }

    private void setOutputFile() {
        System.out.print("Enter output file path: ");
        String path = scanner.nextLine();
        System.out.println("Output file set to: " + path);
    }

    private void exit() {
        System.out.println("Exiting...");
        running = false;
    }
    
}