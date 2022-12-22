package ApplicationUI;

import Aplication.SQLConnect;
import UserStuff.Comanda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class ControllerAHostiry {

    @FXML
    private Label istoricComenzi;

    public void ControllerAHostiry(){

    }

    public void initialize() throws IllegalAccessException, SQLException, InstantiationException {

        LoadData Data = new LoadData();
        Preferences userPreferences = Preferences.userRoot();
        String tempUsername = userPreferences.get("username","none");

        //System.out.println(Data.getComenziAplicatie());

        Data.getComenziAplicatie().IstoricComenziUser(tempUsername);


        Vector<Comanda> tempComenzi = Data.getComenziAplicatie().returnOrdersUser(tempUsername);
        String newText = "";

        for(int i=0; i < tempComenzi.size();i++)
        {
            float suma = 0;
            newText = newText + "Comanda #" + (i+1) + '\n';
            for(int j=0; j< tempComenzi.elementAt(i).getProduseComanda().size();j++)
            {
                String tempName = tempComenzi.elementAt(i).getProduseComanda().elementAt(j).getNume();
                float tempPret = tempComenzi.elementAt(i).getProduseComanda().elementAt(j).getPret();

                newText = newText + tempName + '\n';
                suma = suma + tempPret;
            }

            newText = newText + tempComenzi.elementAt(i).getNumeRestaurant() + " | " + "Total: " + suma + '\n';
            newText = newText + "Data: "+ tempComenzi.elementAt(i).getDataComanda().getDay() + "/" + tempComenzi.elementAt(i).getDataComanda().getMonth()
            + "/" + tempComenzi.elementAt(i).getDataComanda().getYear() + " | "+ "Livrat de "
                    + tempComenzi.elementAt(i).getUsernameLivrator() + '\n';



        }

        istoricComenzi.setText(newText);


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
}
