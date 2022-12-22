package ApplicationUI;

import Aplication.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class TestController {

    @FXML
    private Label usernameLabelProfile;

    @FXML
    private Label numeLabel;

    @FXML
    private Label prenumeLabel;

    @FXML
    private  Label emailLabel;

    @FXML
    private Label telefonLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label cityLabel;

    public TestController(){


    }

    public void initialize() throws SQLException {

        SQLConnect SQL = new SQLConnect();

        Preferences userPreferences = Preferences.userRoot();
        String usernameLogat = userPreferences.get("username","none");
        Vector<String> userData = new Vector<>();
        userData = SQL.SQLSearhUserDatabaseUI(usernameLogat);

        usernameLabelProfile.setText(usernameLogat);
        System.out.println();
        numeLabel.setText(userData.elementAt(0));
        prenumeLabel.setText(userData.elementAt(1));
        emailLabel.setText(userData.elementAt(2));
        telefonLabel.setText(userData.elementAt(3));
        if(userData.elementAt(4).equals("FEMALE"))
        {
            genderLabel.setText("female");
        }
        else{
            genderLabel.setText("male");
        }
        birthLabel.setText(userData.elementAt(5) + "/" + userData.elementAt(6) + "/" + userData.elementAt(7));

        String oraSelectat = userPreferences.get("oras","none");
        cityLabel.setText(oraSelectat);


    }

    public void logout(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("username","none");
        m.changeScene("ApplicationUI.fxml");
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
            m.changeScene("meniuBucharest.fxml");
        }
    }

    public void changeCity(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("oras","none");
        m.changeScene("selectActualCity.fxml");
    }

    public void goToHistory(ActionEvent event) throws IOException {

        MainAppUI m = new MainAppUI();
        m.changeScene("history.fxml");
    }


}
