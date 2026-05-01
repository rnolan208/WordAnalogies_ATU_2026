package ie.atu.sw;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ResultWriter {

	public void write(String filePath, List<SearchResult> results, long timeTaken, int topN, boolean useVirtualThreads) throws Exception {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

	        pw.println("**** Word Analogy Results ****");
	        pw.println("******************************");
	        pw.println(" ");
	        pw.println("Time taken: " + timeTaken + " ms");
	        pw.println("Top N: " + topN);
	        pw.println("Mode: " + (useVirtualThreads ? "Virtual Threads" : "Sequential"));
	        pw.println();

	        for (SearchResult result : results) {
	            pw.println(result.getWord() + " : " + result.getScore());
	        }
	    }

	    System.out.println("Results written to: " + filePath);
	}
}
