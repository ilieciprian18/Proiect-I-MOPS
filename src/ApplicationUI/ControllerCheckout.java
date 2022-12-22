package ApplicationUI;

import Aplication.SQLConnect;
import ProdusStuff.Produs;
import RestaurantStuff.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;
import java.util.prefs.Preferences;

public class ControllerCheckout {

    public void ControllerCheckout(){

    }

    DataSingleton data = DataSingleton.getInstance();
    private Vector<Produs> comandaCheckout;
    private int[] numarProduseCheckout;
    private Restaurant restaurantSelected;
    private boolean voucherUsed;

    @FXML
    private Label foodList;

    @FXML
    private Label totalFood;

    @FXML
    private Label pretFood;

    @FXML
    private Label requiredPayment;

    @FXML
    private RadioButton cardPayment;

    @FXML
    private  RadioButton cashPayment;

    @FXML
    private TextField adressText;

    @FXML
    private Label adressValid;

    @FXML
    private Pane listPane;

    @FXML
    private Label comandaMinima;

    @FXML
    private ImageView imageRestaurant;

    @FXML
    private TextField voucherText;

    @FXML
    private Label voucherAccouncement;

    public void initialize() throws IllegalAccessException, SQLException, InstantiationException {

        this.comandaCheckout = data.getComanda();
        System.out.println("Aceasta este comanda:");
        System.out.println(comandaCheckout);

        this.voucherUsed = false;

        LoadData Data = new LoadData();
        this.numarProduseCheckout = data.getNumarProduseTemp();
        System.out.println(Arrays.toString(numarProduseCheckout));

        Preferences userPreferences = Preferences.userRoot();
        String tempRestaurantName = userPreferences.get("restaurant","none");
        String tempRestaurantOras = userPreferences.get("orasRestaurant","none");
        int restaurantID = Data.getRestauranteAplicatie().CautaRestaurantOrasID(tempRestaurantName,tempRestaurantOras);
        Restaurant restaurantSelectedCheckout = Data.getRestauranteAplicatie().restaurante.elementAt(restaurantID);
        this.restaurantSelected = restaurantSelectedCheckout;

        String imageName = "ApplicationUI/Images/" + tempRestaurantName+ tempRestaurantOras + ".png";
        imageRestaurant.setImage(new Image(imageName));
        imageRestaurant.setFitWidth(389);
        imageRestaurant.setFitHeight(254);




        String listaProduseComanda = "";
        String pretListaProdus ="";
        int tempDown = 14;
        for(int i=0; i< restaurantSelectedCheckout.getMeniu().size();i++)
        {
            if(numarProduseCheckout[i] >0)
            {
                //cod
                System.out.println(restaurantSelectedCheckout.getMeniu().elementAt(i).getNume() + " x " + numarProduseCheckout[i]);
                listaProduseComanda = listaProduseComanda + restaurantSelectedCheckout.getMeniu().elementAt(i).getNume() + " x " + numarProduseCheckout[i] + '\n';

                float tempSuma = 0;
                tempSuma = restaurantSelectedCheckout.getMeniu().elementAt(i).getPret() * numarProduseCheckout[i];
                pretListaProdus = pretListaProdus + String.valueOf(tempSuma) + " ron" + '\n';

                String idButton = "b" + i;
                Button tempRemoveButton = new Button();
                tempRemoveButton.setId(idButton);
                tempRemoveButton.setText("Remove");
                tempRemoveButton.setPrefWidth(55);
                tempRemoveButton.setMaxWidth(55);
                tempRemoveButton.setMinHeight(12);
                tempRemoveButton.setPrefHeight(12);
                tempRemoveButton.setMaxHeight(12);
                tempRemoveButton.setLayoutX(371);
                tempRemoveButton.setLayoutY(tempDown);
                tempRemoveButton.getStylesheets().add(getClass().getResource("Images/checkoutButton.css").toExternalForm());
                tempRemoveButton.setTextFill(Paint.valueOf("white"));
                tempRemoveButton.setFont(new Font("System", 6));
                tempRemoveButton.setStyle("-fx-font-weight: bold");
               // tempRemoveButton.setOnAction();
                tempRemoveButton.setOnAction(this::removeItem);
                listPane.getChildren().add(tempRemoveButton);

                idButton = "d" + i;
                Button tempDeleteButton = new Button();
                tempDeleteButton.setId(idButton);
                tempDeleteButton.setText("Add");
                tempDeleteButton.setPrefWidth(55);
                tempDeleteButton.setMaxWidth(55);
                tempDeleteButton.setMinWidth(55);
                tempDeleteButton.setMaxHeight(12);
                tempDeleteButton.setMinHeight(12);
                tempDeleteButton.setPrefHeight(12);
                tempDeleteButton.setLayoutX(434);
                tempDeleteButton.setLayoutY(tempDown);
                tempDeleteButton.getStylesheets().add(getClass().getResource("Images/checkoutButton.css").toExternalForm());
                tempDeleteButton.setTextFill(Paint.valueOf("white"));
                tempDeleteButton.setFont(new Font("System",6));
                tempDeleteButton.setStyle("-fx-font-weight: bold");
                tempDeleteButton.setOnAction(this::addItem);
                listPane.getChildren().add(tempDeleteButton);


                tempDown = tempDown + 17;
            }
        }
        foodList.setText(listaProduseComanda);
        float suma = 0;
        for(int i=0; i< comandaCheckout.size();i++)
        {
            suma = suma + comandaCheckout.elementAt(i).getPret();
            System.out.println(suma);
        }
        //totalFood.setText(comandaCheckout.);
        totalFood.setText("Total: " + suma + " ron");
        pretFood.setText(pretListaProdus);





    }

    public void removeItem(ActionEvent event){

        String removeButtonId = ((Button)event.getSource()).getId();
        removeButtonId = removeButtonId.replace("b","");
        int itemButtonID = Integer.parseInt(removeButtonId);

        numarProduseCheckout[itemButtonID]--;
        String produsRemoved = restaurantSelected.getMeniu().elementAt(itemButtonID).getNume();
        for(int i=0; i< comandaCheckout.size();i++)
        {
            if ( comandaCheckout.elementAt(i).getNume().equals(produsRemoved))
            {
               // System.out.println("Am sters");
                comandaCheckout.removeElementAt(i);
                break;
            }
        }
        System.out.println(numarProduseCheckout[itemButtonID]);
        if(numarProduseCheckout[itemButtonID] == 0)
        {
            //delete butoane
            System.out.println("Intra aici");
            //Button lookForRemoveButton = (Button) listPane.lookup("#b" + removeButtonId);
            listPane.getChildren().remove(listPane.lookup("#b" + removeButtonId));
            listPane.getChildren().remove(listPane.lookup("#d" + removeButtonId));

            //updatam pozitie butoane

            for( int i = itemButtonID+1; i< restaurantSelected.getMeniu().size();i++)
            {
                  System.out.println("Butonul se muta" + i);
                  try{
                      Button lookForButton = (Button) listPane.lookup("#b" +i);
                      lookForButton.setLayoutY(lookForButton.getLayoutY()-17);

                      Button lookForAddButton = (Button) listPane.lookup("#d" + i);
                      lookForAddButton.setLayoutY(lookForAddButton.getLayoutY()-17);
                  }
                  catch(NullPointerException e){

                  }
            }
            //Button lookForButton = (Button) listPane.lookup();
        }

        System.out.println(itemButtonID);
        String listaProduseActualizata = "";
        String listaPret = "";
        float pret = 0;

        for( int i= 0; i< restaurantSelected.getMeniu().size();i++){

            if(numarProduseCheckout[i] >0)
            {
                listaProduseActualizata = listaProduseActualizata + restaurantSelected.getMeniu().elementAt(i).getNume() + " x " + numarProduseCheckout[i] + '\n';
                listaPret = listaPret + (restaurantSelected.getMeniu().elementAt(i).getPret() * numarProduseCheckout[i]) + '\n';
                pret = pret + restaurantSelected.getMeniu().elementAt(i).getPret() * numarProduseCheckout[i];

            }
        }

        foodList.setText(listaProduseActualizata);
        pretFood.setText(listaPret);
        totalFood.setText("Total: " + pret);


    }

    public void addItem(ActionEvent event){

        String addButtonId = ((Button)event.getSource()).getId();
        addButtonId = addButtonId.replace("d","");
        int buttonId = Integer.parseInt(addButtonId);

        numarProduseCheckout[buttonId]++;

        String listaProduseActualizata = "";
        String listaPret = "";
        float pret = 0;

        for( int i= 0; i< restaurantSelected.getMeniu().size();i++){

            if(numarProduseCheckout[i] >0)
            {
                listaProduseActualizata = listaProduseActualizata + restaurantSelected.getMeniu().elementAt(i).getNume() + " x " + numarProduseCheckout[i] + '\n';
                listaPret = listaPret + (restaurantSelected.getMeniu().elementAt(i).getPret() * numarProduseCheckout[i]) + '\n';
                pret = pret + restaurantSelected.getMeniu().elementAt(i).getPret() * numarProduseCheckout[i];

            }
        }

        foodList.setText(listaProduseActualizata);
        pretFood.setText(listaPret);
        totalFood.setText("Total: " + pret);

    }



    public void sendOrder(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, IOException {

        LoadData data = new LoadData();
        boolean okPayment = false;
        if (cardPayment.isSelected() && cashPayment.isSelected())
        {
            requiredPayment.setText("One Option*");
            requiredPayment.setVisible(true);
        }
        else {
            if(cashPayment.isSelected() != true && cardPayment.isSelected() != true)
            {
                requiredPayment.setText("Required*");
                requiredPayment.setVisible(true);
            }
            else{
                okPayment = true;
                requiredPayment.setVisible(false);
            }
        }

        boolean okiAdress = false;
        if( adressText.getLength() >= 10)
        {
            okiAdress = true;
            adressValid.setVisible(false);
        }
        else
        {
            adressValid.setText("Required*");
            adressValid.setVisible(true);
        }

       boolean okTotal = false;
        String total = totalFood.getText();
        total = total.replace("T","");
        total = total.replace("t","");
        total = total.replace(":","");
        total = total.replace(" ","");
        total = total.replace("l","");
        total = total.replace("a","");
        total = total.replace("o","");
        total = total.replace("r","");
        total = total.replace("n","");

        float pretTotal = Float.valueOf(total);
        if( pretTotal > 5)
        {
            okTotal = true;
            comandaMinima.setVisible(false);

        }
        else
        {
            comandaMinima.setVisible(true);
        }

        if( okTotal && okiAdress && okPayment)
        {

            SQLConnect SQL = new SQLConnect();
            Preferences userPreferences = Preferences.userRoot();
            String usernameComanda = userPreferences.get("username","none");

            System.out.println("Username este " + usernameComanda);
            Vector<String> dateComanda = SQL.SQLSearhUserDatabaseUI(usernameComanda);
            String adressComanda = adressText.getText();
            String numeRestaurantComanda = restaurantSelected.getNume();
            String usernameLivratorComanda = data.getLivratoriAplicatie().randomLivrator();
            Calendar calendar = Calendar.getInstance();
            int dayComanda = calendar.get(Calendar.DAY_OF_MONTH);
            int monthComanda = calendar.get(Calendar.MONTH);
            int yearComanda = calendar.get(Calendar.YEAR);
            String orasRestaurantComanda = restaurantSelected.getOras();
            System.out.println(orasRestaurantComanda);
            int orderID = data.getComenziAplicatie().numarComenzi()-1;
            System.out.println("Order ID este " + orderID);

            for( int i = 0; i< comandaCheckout.size();i++)
            {
                String numeProdusComanda = comandaCheckout.elementAt(i).getNume();
                SQL.insertOrder(orderID+1,numeRestaurantComanda,usernameComanda,
                usernameLivratorComanda,dayComanda,monthComanda,yearComanda,adressComanda,orasRestaurantComanda,
                        numeProdusComanda);
            }

            data.getComenziAplicatie().AddComanda(numeRestaurantComanda,comandaCheckout,usernameComanda,usernameLivratorComanda,
                    dayComanda,monthComanda,yearComanda,
                    adressComanda);

            System.out.println(data.getComenziAplicatie());








            //add in Comenzi aplicatie


            //add in database



            System.out.println("merge trimisa!");
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Update Comanda");
            a.setHeaderText("Comanda Trimisa!");
            Random random = new Random();
            int max = 90;
            int min = 30;
            int randomNumber = random.nextInt(max-min) + min;
            String contextText = "";
            contextText = "Comanda va ajunge la dumneavoastra in " + randomNumber + "m" + '\n' ;
            if(voucherUsed == false)
            {
                contextText = contextText + "Nu exista voucher folosit, total: " + pretTotal + " ron";
            }
            else {
                contextText = contextText + "Ati folosit PROMO20, total: " + (pretTotal - pretTotal/100 * 20) + " ron";
            }
            a.setContentText(contextText);
            a.show();

            SQL.auditLog("Order has been sent...!");
            MainAppUI m = new MainAppUI();

            if(orasRestaurantComanda.equals("Ploiesti"))
            {
                m.changeScene("menu.fxml");
            }
            else {
                m.changeScene("meniuBucharest.fxml");
            }


        }



        //comanda finalizata
    }

    public void checkValidVoucher(ActionEvent event){


        System.out.println(voucherText.getText());
        if(voucherText.getText().equals("PROMO20") && voucherUsed == false)
        {
            this.voucherUsed = true;
            voucherAccouncement.setVisible(true);
            voucherAccouncement.setText("Applied");
        }
        else{
            voucherAccouncement.setVisible(true);
            voucherAccouncement.setText("Not Valid!");
        }



       // MainAppUI m = new MainAppUI();



    }


    public void goToOrder(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();

        // m.changeScene("");
        //System.out.println(restaurantSelected.getNume());

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

    public void goToHistory(ActionEvent event) throws IOException {

        MainAppUI m = new MainAppUI();
        m.changeScene("history.fxml");
    }

    public void goToProfile(ActionEvent event) throws IOException, SQLException {
        MainAppUI m = new MainAppUI();
        m.changeScene("profile.fxml");
        SQLConnect SQL = new SQLConnect();
        Preferences userPreferences = Preferences.userRoot();
        String usernameLogat = userPreferences.get("username","none");
        userPreferences.put("restaurant","none");
        userPreferences.put("restaurantOras","none");

        //System.out.println(usernameLogat);
        // usernameLabelProfile.setText("wow");
        // System.out.println(usernameLabelProfile.getText());
    }


}
