package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmbeddingLoader {
	
	// Indexing the words of a book with a map exercise - check similarity
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
