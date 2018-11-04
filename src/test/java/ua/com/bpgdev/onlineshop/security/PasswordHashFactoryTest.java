package ua.com.bpgdev.onlineshop.security;

import org.junit.Test;
import ua.com.bpgdev.onlineshop.security.entity.PasswordEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class PasswordHashFactoryTest {



    @Test
    public void testGeneratePasswordEntity() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PasswordEntity passwordEntity = PasswordHashFactory.generatePasswordEntity("test");

    }

    @Test
    public void testValidatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String[] passwords = {"test","PassWorD","123_()*&^%$#@!","<>fsjldkfjlHJKHKJHsdfsoid"};
/*
        for (String password : passwords) {
            PasswordEntity passwordEntity = PasswordHashFactory.generatePasswordEntity(password);
            assertTrue(PasswordHashFactory.validatePassword(password, passwordEntity));
        }
        */
    }
}