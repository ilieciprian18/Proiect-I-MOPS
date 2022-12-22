package Aplication;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLConnectTest {

    @Test
    void loginWrong() throws SQLException {
        var sqlTest = new SQLConnect();
        assertEquals(false,sqlTest.Login("andreea","Voicu"));
    }

    @Test
    void loginCorrect() throws SQLException {
        var sqlTest = new SQLConnect();
        assertEquals(true,sqlTest.Login("username","pass"));
    }

    @Test
    void signUpWrong() throws SQLException {
        var sqlTest = new SQLConnect();
        assertDoesNotThrow(() -> sqlTest.SignUp("username","pass"));
    }

}