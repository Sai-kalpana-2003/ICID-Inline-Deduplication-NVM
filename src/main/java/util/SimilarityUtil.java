package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Helpers for inline deduplication: a SHA-256 fingerprint for exact duplicates
 * and a character 3-gram Jaccard score for near duplicates.
 */
public class SimilarityUtil {
    private static final int N = 3;

    private SimilarityUtil() { }

    public static String normalize(String s) {
        return s == null ? "" : s.toLowerCase(Locale.ROOT).replaceAll("\\s+", " ").trim();
    }

    /** SHA-256 of the normalized text, as 64 hex characters. */
    public static String fingerprint(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(normalize(text).getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : d) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Set<String> grams(String s) {
        Set<String> set = new HashSet<>();
        if (s.length() <= N) {
            if (!s.isEmpty()) set.add(s);
            return set;
        }
        for (int i = 0; i + N <= s.length(); i++) set.add(s.substring(i, i + N));
        return set;
    }

    /** Similarity between two texts as a percentage, 0..100. */
    public static int similarityPercent(String a, String b) {
        String x = normalize(a), y = normalize(b);
        if (x.isEmpty() && y.isEmpty()) return 100;
        if (x.equals(y)) return 100;
        Set<String> gx = grams(x), gy = grams(y);
        if (gx.isEmpty() || gy.isEmpty()) return 0;
        Set<String> inter = new HashSet<>(gx);
        inter.retainAll(gy);
        int union = gx.size() + gy.size() - inter.size();
        return (int) Math.round(100.0 * inter.size() / union);
    }
}
