package ie.atu.sw;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ResultWriter {
	
	/**
	 * Writes the search results and metadata to an output file.
	 *
	 * @param filePath the output file path
	 * @param results the list of search results
	 * @param timeTaken the time taken to perform the search (in milliseconds)
	 * @param topN the number of results requested
	 * @param useVirtualThreads indicates whether virtual threads were used
	 * @throws Exception if an error occurs while writing to the file
	 *
	 * Time Complexity: O(n)
	 * Rationale: each result is written to the file once.
	 */

	public void write(String filePath, List<SearchResult> results, long timeTaken, int topN, boolean useVirtualThreads) throws Exception {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

	        pw.println("**** Word Analogy Results ****");
	        pw.println("------------------------------");
	        pw.println();
	        pw.println("Time taken: " + timeTaken + " ms");
	        pw.println("Top N: " + topN);
	        pw.println("Mode: " + (useVirtualThreads ? "Virtual Threads" : "Sequential"));
	        pw.println("------------------------------");
	        pw.println();

	        for (SearchResult result : results) {
	            pw.println(result.getWord() + " : " + result.getScore());
	        }
	    }

	    System.out.println("Results written to: " + filePath);
	}
}
