package ua.com.bpgdev.onlineshop.security.entity;

public class PasswordEntity {
    private final int iterations;
    private final String passwordHash;
    private final String salt;

    public PasswordEntity(int iterations, String passwordHash, String salt) {
        this.iterations = iterations;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public int getIterations() {
        return iterations;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public String toString() {
        return "PasswordEntity{" +
                "iterations=" + iterations +
                ", passwordHash='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
