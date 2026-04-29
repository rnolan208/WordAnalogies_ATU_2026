package ie.atu.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AnalogyFinder {

    public List<SearchResult> findClosestWords(
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
}