package ua.com.bpgdev.onlineshop.security;

import ua.com.bpgdev.onlineshop.security.entity.PasswordEntity;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashFactory {
    private static final int DEFAULT_ITERATIONS_COUNT = 100_000;
    private static final int DEFAULT_SALT_LENGTH = 64;
    private static final int DEFAULT_HASH_KEY_LENGTH = 512;


    public static PasswordEntity generatePasswordEntity(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = DEFAULT_ITERATIONS_COUNT;

        byte[] salt = getSalt(DEFAULT_SALT_LENGTH);
        byte[] passwordHash = getPasswordHash(password, iterations, salt);

        return new PasswordEntity(iterations,
                byteToStringForHash(passwordHash),
                byteToStringForHash(salt));
    }

    private static byte[] getPasswordHash(String originalPassword, int iterations, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, DEFAULT_HASH_KEY_LENGTH);

        return secretKeyFactory.generateSecret(spec).getEncoded();
    }


    public static boolean validatePassword(String originalPassword, int iterations,
                                           String storedPasswordHash, String storeSalt)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        byte[] salt = stringToByteForHash(storeSalt);
        byte[]  generatedPasswordHash = getPasswordHash(originalPassword, iterations, salt);

        return storedPasswordHash.equals(byteToStringForHash(generatedPasswordHash));
    }

    public static boolean validatePassword(String originalPassword, PasswordEntity passwordEntity)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        return validatePassword(originalPassword, passwordEntity.getIterations(),
                passwordEntity.getPasswordHash(), passwordEntity.getSalt());
    }

    private static byte[] getSalt(int saltLength) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[saltLength];
        sr.nextBytes(salt);

        return salt;
    }

    private static String byteToStringForHash(byte[] value) {
        return new BigInteger(1, value).toString(16);
    }

    private static byte[] stringToByteForHash(String value){
        // I do not know this magic, I've just copy it from https://stackoverflow.com/a/140861
        int len = value.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(value.charAt(i), 16) << 4)
                    + Character.digit(value.charAt(i+1), 16));
        }
        return data;
    }

}
