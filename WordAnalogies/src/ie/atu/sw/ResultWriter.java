package ie.atu.sw;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ResultWriter {

    public void write(String filePath, List<SearchResult> results) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            for (SearchResult result : results) {
                pw.println(result.getWord() + " : " + result.getScore());
            }

            System.out.println("Results written to: " + filePath);
        }
    }
}
