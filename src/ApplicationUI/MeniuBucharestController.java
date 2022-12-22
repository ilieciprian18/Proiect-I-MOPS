package ApplicationUI;


import Aplication.SQLConnect;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MeniuBucharestController {

    public  MeniuBucharestController(){

    }

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

    public void goToHistory(ActionEvent event) throws IOException {

        MainAppUI m = new MainAppUI();
        m.changeScene("history.fxml");
    }

    public void goToMenuMcdonalds(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("restaurant","Mcdonalds");
        userPreferences.put("orasRestaurant","Bucuresti");

        MainAppUI m = new MainAppUI();
        m.changeScene("KFCPloiesti.fxml");



    }

    public void goToMenuDristor(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("restaurant","Doner Kebap");
        userPreferences.put("orasRestaurant","Bucuresti");

        MainAppUI m = new MainAppUI();
        m.changeScene("KFCPloiesti.fxml");



    }


}
