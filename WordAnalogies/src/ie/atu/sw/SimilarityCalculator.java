package ie.atu.sw;

public class SimilarityCalculator {
	
	/**
	 * Computes the cosine similarity between the two vectors.
	 *
	 * @param a the first vector
	 * @param b the second vector
	 * @return similarity score
	 * @throws IllegalArgumentException if the vectors have different lengths
	 *
	 * Time Complexity: O(d)
	 * Rationale: requires one pass to compute dot product and magnitudes.
	 */

    public static double cosine(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }

        double dotProduct = 0.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;

        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
            magnitudeA += a[i] * a[i];
            magnitudeB += b[i] * b[i];
        }

        if (magnitudeA == 0 || magnitudeB == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
    }
}