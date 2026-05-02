package ie.atu.sw;

public class VectorCalculator {
	
	/**
	 * Adds two vectors together in terms of elements.
	 *
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return resulting vector
	 *
	 * Time Complexity: O(d)
	 * Rationale: loops through each element of the vectors once.
	 */

    public static double[] add(double[] v1, double[] v2) {
        double[] result = new double[v1.length];

        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }

        return result;
    }
    
    
    /**
     * Subtracts one vector from another in terms of elements.
     * 
     * @param v1 first vector
	 * @param v2 second vector
	 * @return resulting vector
     *
     * Time Complexity: O(d)
     * Rationale: loops through each element of the vectors once.
     */

    public static double[] subtract(double[] v1, double[] v2) {
        double[] result = new double[v1.length];

        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }

        return result;
    }
}
