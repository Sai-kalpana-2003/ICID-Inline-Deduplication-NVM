package bean;

/** Outcome of the inline deduplication check for one upload. */
public class UploadResult {
    public static final int THRESHOLD = 60; // percent

    private final boolean stored;
    private final int similarity;       // highest similarity found, 0..100
    private final String matchedName;   // name of the most similar existing data (or null)
    private final String message;

    public UploadResult(boolean stored, int similarity, String matchedName, String message) {
        this.stored = stored;
        this.similarity = similarity;
        this.matchedName = matchedName;
        this.message = message;
    }
    public boolean isStored() { return stored; }
    public int getSimilarity() { return similarity; }
    public String getMatchedName() { return matchedName; }
    public String getMessage() { return message; }
}
