package ie.atu.sw;

public class SearchResult implements Comparable<SearchResult> {
    private String word;
    private double score;

    public SearchResult(String word, double score) {
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(SearchResult other) {
        return Double.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return word + " : " + score;
    }
}