package Aplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void tooShort(){
        var passwordTest = new Password();
        assertEquals(false, passwordTest.passwordIsValid("aa8A"));
    }

    @Test
    void noUpper(){
        var passwordTest = new Password();
        assertEquals(false, passwordTest.passwordIsValid("aaaaaaa7"));
    }

    @Test
    void noDigit(){
        var passwordTest = new Password();
        assertEquals(false, passwordTest.passwordIsValid("aaaaaaAA"));
    }

    @Test
    void goodPassword(){
        var passwordTest = new Password();
        assertEquals(true, passwordTest.passwordIsValid("aaAAaaA9"));
    }

    @Test
    void tooShortNoDigit(){
        var passwordTest = new Password();
        assertEquals(false, passwordTest.passwordIsValid("Aa"));
    }
}