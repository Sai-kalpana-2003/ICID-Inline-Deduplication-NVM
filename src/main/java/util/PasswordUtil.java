package util;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Salted PBKDF2 password hashing. Stored format: base64(salt)$base64(hash). */
public class PasswordUtil {
    private static final int ITERATIONS = 65536;
    private static final int KEY_BITS = 256;

    private PasswordUtil() { }

    public static String hash(String password) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(derive(password, salt));
    }

    public static boolean verify(String password, String stored) {
        if (stored == null || !stored.contains("$")) return false;
        String[] parts = stored.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] expected = Base64.getDecoder().decode(parts[1]);
        byte[] actual = derive(password, salt);
        int diff = expected.length ^ actual.length;
        for (int i = 0; i < Math.min(expected.length, actual.length); i++) diff |= expected[i] ^ actual[i];
        return diff == 0;
    }

    private static byte[] derive(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_BITS);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
