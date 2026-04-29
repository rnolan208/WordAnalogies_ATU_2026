package ie.atu.sw;

import java.util.Scanner;
import java.util.Map;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private String embeddingFilePath;
    private Map<String, double[]> embeddings;
    private String outputFilePath = "./out.txt";
    
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
		System.out.println("(4) Specify Output File (current: " + outputFilePath + ")");
		System.out.println("(0) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	    System.out.print("Select Option [1-4]> ");

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
            case "0" -> exit();
            default -> System.out.println("Invalid option.");
        }
    }

    private void setEmbeddingPath() {
        System.out.print("Enter path to embeddings file: ");
        embeddingFilePath = scanner.nextLine();

        try {
            EmbeddingLoader loader = new EmbeddingLoader();
            embeddings = loader.load(embeddingFilePath);

            System.out.println(ConsoleColour.GREEN);
            System.out.println("Embeddings loaded successfully.");
            System.out.println("Total words loaded: " + embeddings.size());
            System.out.println(ConsoleColour.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColour.RED);
            System.out.println("Error loading embeddings file: " + e.getMessage());
            System.out.println(ConsoleColour.RESET);
        }
    }

    
    private void enterOperation() {
        if (embeddings == null) {
            System.out.println(ConsoleColour.RED);
            System.out.println("Please load embeddings first (Option 1).");
            System.out.println(ConsoleColour.RESET);
            return;
        }

        System.out.print("Enter operation (e.g. king - man + woman): ");
        String input = scanner.nextLine();

        try {
        	String[] parts = input.trim().split("\\s+");

            double[] result = embeddings.get(parts[0]);
            if (result == null) {
                System.out.println("Word not found: " + parts[0]);
                return;
            }

            for (int i = 1; i < parts.length; i += 2) {
                String operator = parts[i];
                String word = parts[i + 1];

                double[] nextVector = embeddings.get(word);

                if (nextVector == null) {
                    System.out.println("Word not found: " + word);
                    return;
                }

                if (operator.equals("+")) {
                    result = VectorCalculator.add(result, nextVector);
                } else if (operator.equals("-")) {
                    result = VectorCalculator.subtract(result, nextVector);
                }
                
                else {
                    System.out.println("Unsupported operator: " + operator);
                    return;
                }
            }

            AnalogyFinder finder = new AnalogyFinder();
            var matches = finder.findClosestWords(embeddings, result, 10);

            System.out.println("Top matches:");
            for (SearchResult match : matches) {
                System.out.println(match);
            }
            
            ResultWriter writer = new ResultWriter();
            writer.write(outputFilePath, matches);

        } catch (Exception e) {
            System.out.println(ConsoleColour.RED);
            System.out.println("Error processing operation: " + e.getMessage());
            System.out.println(ConsoleColour.RESET);
        }
    }

    
    private void configureOptions() {
        System.out.println("Options not implemented yet.");
    }

    
    private void setOutputFile() {
        System.out.print("Enter output file path (leave blank for default): ");
        String path = scanner.nextLine().trim();

        if (path.isBlank()) {
            outputFilePath = "./out.txt";
        } else {
            path = path.replace("\"", "");

            // If no .txt file is specified then the program will assume it's a folder specified
            if (!path.toLowerCase().endsWith(".txt")) {
                if (!path.endsWith("\\") && !path.endsWith("/")) {
                    path += "\\";
                }
                path += "out.txt";
            }

            outputFilePath = path;
        }

        System.out.println("Output file set to: " + outputFilePath);
    }

    
    private void exit() {
        System.out.println("Exiting...");
        running = false;
    }
    
}