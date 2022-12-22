package ApplicationUI;

import Aplication.SQLConnect;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.awt.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class Controller {

    public String loggedUsername;
    public String loggedPassword;

    public Controller(){

    }

    @FXML
    private Button loginButton;

    @FXML
    private Label wrongPasswordIntroduced;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void login(ActionEvent event) throws SQLException, IOException {
         SQLConnect SQL = new SQLConnect();

         MainAppUI m = new MainAppUI();

         String tempUser = username.getText();
         if(SQL.Login(username.getText(), password.getText()))
        {
            System.out.println("merge");
            Preferences userPreferences = Preferences.userRoot();
            userPreferences.put("username", tempUser);

            SQL.auditLog("User logged in!");

            m.changeScene("selectActualCity.fxml");

        }
         else {
             System.out.println("Parola incorecta");
             wrongPasswordIntroduced.setVisible(true);


         }

    }


    public void goToRegister(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();
        m.changeScene("register.fxml");
    }

    public String getLoggedUsername(){
        return this.loggedUsername;
    }


}
