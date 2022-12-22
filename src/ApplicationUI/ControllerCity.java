package ApplicationUI;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.prefs.Preferences;


public class ControllerCity {

    public ControllerCity()
    {

    }

    public void clickPloiesti(ActionEvent event) throws IOException {

        System.out.println("Ploiesti");
        Preferences userPreferences = Preferences.userRoot();
        System.out.println(userPreferences.get("username","none"));
        userPreferences.put("oras","Ploiesti");

        MainAppUI m = new MainAppUI();
        m.changeScene("menu.fxml");
    }

    public void clickBucharest(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("oras","Bucuresti");

        MainAppUI m = new MainAppUI();
        m.changeScene("meniuBucharest.fxml");
    }


}
