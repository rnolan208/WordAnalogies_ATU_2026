package ie.atu.sw;

public class VectorCalculator {

    public static double[] add(double[] v1, double[] v2) {
        double[] result = new double[v1.length];

        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }

        return result;
    }

    public static double[] subtract(double[] v1, double[] v2) {
        double[] result = new double[v1.length];

        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }

        return result;
    }
}
