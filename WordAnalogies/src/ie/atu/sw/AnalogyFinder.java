package ie.atu.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

public class AnalogyFinder {

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