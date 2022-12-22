package ApplicationUI;

import Aplication.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javafx.scene.control.Label;

public class ControllerBackend {

    public ControllerBackend(){

    }


    @FXML
    private Label usernameLabelProfile;

    public void goToProfile(ActionEvent event) throws IOException, SQLException {
        MainAppUI m = new MainAppUI();
        m.changeScene("profile.fxml");
        SQLConnect SQL = new SQLConnect();
        Preferences userPreferences = Preferences.userRoot();
        String usernameLogat = userPreferences.get("username","none");

        //System.out.println(usernameLogat);
       // usernameLabelProfile.setText("wow");
       // System.out.println(usernameLabelProfile.getText());
    }

    public void goToOrder(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();

        // m.changeScene("");

        Preferences userPreferences = Preferences.userRoot();
        String selectedCity = userPreferences.get("oras","none");

        if(selectedCity.equals("Ploiesti"))
        {
            m.changeScene("menu.fxml");
        }
        else
        {
            m.changeScene("");
        }
    }

    public void goToHistory(ActionEvent event) throws IOException {

        MainAppUI m = new MainAppUI();
        m.changeScene("history.fxml");
    }


    public void goToMenuKFC(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("restaurant","KFC");
        userPreferences.put("orasRestaurant","Ploiesti");

        MainAppUI m = new MainAppUI();
        m.changeScene("KFCPloiesti.fxml");



    }

    public void goToMenuDoner(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("restaurant","Doner");
        userPreferences.put("orasRestaurant","Ploiesti");

        MainAppUI m = new MainAppUI();
        m.changeScene("KFCPloiesti.fxml");



    }



}
