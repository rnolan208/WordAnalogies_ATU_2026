package ie.atu.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

public class AnalogyFinder {
	
	/**
	 * Finds the top N closest words to the given vector by using either sequential
	 * processing or virtual threads.
	 *
	 * @param embeddings the map of words to vector embeddings
	 * @param resultVector the computed vector from the analogy expression
	 * @param topN the number of results to return
	 * @param useVirtualThreads true to use virtual threads, false for sequential search
	 * @return a list of the top matching words and their similarity scores
	 * @throws Exception if the virtual thread executor fails
	 *
	 * Time Complexity: O(n log n)
	 * Rationale: O(n * d) is required to compare all n word vectors of dimension d,
	 * and O(n log n) is required to sort the results.
	 */

    public List<SearchResult> findClosestWords(
            Map<String, double[]> embeddings,
            double[] resultVector,
            int topN,
            boolean useVirtualThreads
    ) throws Exception {

        if (useVirtualThreads) {
            return findWithVirtualThreads(embeddings, resultVector, topN);
        } else {
            return findSequentially(embeddings, resultVector, topN);
        }
    }
    
    
    /**
     * Performs the similarity search sequentially.
     * 
     * @param embeddings the map of words to vector embeddings
	 * @param resultVector the computed analogy vector
	 * @param topN the number of results to return
	 * @return a sorted list of the top matching results
     *
     * Time Complexity: O(n log n)
     * Rationale: each word vector is compared once and then all results are sorted.
     */

    // Sequential - one loop
    private List<SearchResult> findSequentially(
            Map<String, double[]> embeddings,
            double[] resultVector,
            int topN
    ) {
        List<SearchResult> results = new ArrayList<>();

        for (String word : embeddings.keySet()) {
            double[] vector = embeddings.get(word);
            double score = SimilarityCalculator.cosine(vector, resultVector);
            results.add(new SearchResult(word, score));
        }

        Collections.sort(results);
        return results.subList(0, Math.min(topN, results.size()));
    }
    
    
    /**
     * Performs similarity search using virtual threads.
     *
     *@param embeddings the map of words to vector embeddings
	 * @param resultVector the computed analogy vector
	 * @param topN the number of results to return
	 * @return a sorted list of the top matching results
	 * @throws Exception if virtual thread execution fails
	 * 
     * Time Complexity: O(n log n)
     * Rationale: similarity calculations are distributed across virtual threads,
     * but the final sort still requires O(n log n).
     */

    // Virtual threads - one thread per word
    private List<SearchResult> findWithVirtualThreads(
            Map<String, double[]> embeddings,
            double[] resultVector,
            int topN
    ) throws Exception {

        ConcurrentLinkedQueue<SearchResult> results = new ConcurrentLinkedQueue<>();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String word : embeddings.keySet()) {
                executor.submit(() -> {
                    double[] vector = embeddings.get(word);
                    double score = SimilarityCalculator.cosine(vector, resultVector);
                    results.add(new SearchResult(word, score));
                });
            }
        }

        List<SearchResult> sortedResults = new ArrayList<>(results);
        Collections.sort(sortedResults);

        return sortedResults.subList(0, Math.min(topN, sortedResults.size()));
    }
}