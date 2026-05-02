package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmbeddingLoader {
	
	/**
	 * Loads the word embeddings from an input file into the map.
	 *
	 * @param filePath is the path to the embeddings file
	 * @return map of words to their correct vector representations
	 * @throws Exception if an error occurs while reading the file
	 *
	 * Time Complexity: O(n * d)
	 * Rationale: n = number of words, d = vector size.
	 * Each line is read once and each value within the vector is parsed.
	 */
	
	public Map<String, double[]> load(String filePath) throws Exception {
        Map<String, double[]> embeddings = new ConcurrentHashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            
            while ((line = br.readLine()) != null) {
                lineCount++;

                String[] parts = line.trim().split("\\s+");

                if (parts.length < 2) {
                    continue;
                }

                String word = parts[0].replace(",", "").toLowerCase();
                double[] vector = new double[parts.length - 1];

                for (int i = 1; i < parts.length; i++) {
                    String value = parts[i].replace(",", "");
                    vector[i - 1] = Double.parseDouble(value);
                }

                embeddings.put(word, vector);

                if (lineCount % 1000 == 0) {
                    ProgressMeter.printProgress(lineCount, 100000);
                }
            }
        }

        System.out.println();
        System.out.println("Loaded " + embeddings.size() + " word embeddings.");

        return embeddings;
    }

}
