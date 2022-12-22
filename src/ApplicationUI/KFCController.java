package ApplicationUI;

import Aplication.SQLConnect;
import ProdusStuff.Produs;
import RestaurantStuff.Restaurant;
import UserStuff.Comanda;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class KFCController {

    public KFCController(){

    }

    @FXML
    private AnchorPane menuPanel;
    private Restaurant restaurantSelected;
    private Vector<Produs> comandaUser;
    private int[] updateInterfata;

    DataSingleton data = DataSingleton.getInstance();

    public void initialize() throws IllegalAccessException, SQLException, InstantiationException {

        Preferences userPreferences = Preferences.userRoot();
        String numeRestaurant = userPreferences.get("restaurant","none");
        String orasRestaurant =userPreferences.get("orasRestaurant","none");
        LoadData Data = new LoadData();
        this.comandaUser = new Vector<>();
        this.updateInterfata = new int[20];

        /*
        Pane itemPanel = new Pane();
       // itemPanel.ba
        //Button button = new Button("1");
        itemPanel.setStyle("-fx-background-color:  #D3D3D3");
        itemPanel.setPrefWidth(825);
        itemPanel.setPrefHeight(112);
        menuPanel.getChildren().add(itemPanel);
        System.out.println("inservat");

        ImageView itemImage = new ImageView();
        Image img = new Image("ApplicationUI/Images/Screenshot_4.png");
        itemImage.setImage(new Image("ApplicationUI/Images/Screenshot_4.png"));
        itemImage.setFitWidth(187);
        itemImage.setFitHeight(122);
        itemImage.setLayoutX(0);
        itemImage.setLayoutY(0);



        menuPanel.getChildren().add(itemImage);

        Label itemName = new Label();
        itemName.setText("temp name");
        itemName.setFont(new Font("Sylfaen", 25));
        itemName.setLayoutX(222);
        itemName.setLayoutY(34);
        menuPanel.getChildren().add(itemName);

        Label ingredienteName = new Label();
        ingredienteName.setText("ingrediente");
        ingredienteName.setFont(new Font("System", 12));
        ingredienteName.setLayoutX(222);
        ingredienteName.setLayoutY(65);
        menuPanel.getChildren().add(ingredienteName);

        Label pret = new Label();
        pret.setText("17.0");
        pret.setFont(new Font("Sylfaen", 28));
        pret.setLayoutX(503);
        pret.setLayoutY(39);
        menuPanel.getChildren().add(pret);

        Button buttonOrder = new Button();
        buttonOrder.setText("Add");
        buttonOrder.setPrefWidth(94);
        buttonOrder.setPrefHeight(39);
        buttonOrder.setLayoutX(679);
        buttonOrder.setLayoutY(37);
        buttonOrder.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
        buttonOrder.setTextFill(Paint.valueOf("white"));
        buttonOrder.setFont(new Font("System",16));
        buttonOrder.setStyle("-fx-font-weight: bold");
        menuPanel.getChildren().add(buttonOrder);

        Label nrProduse = new Label();
        nrProduse.setText("(3)");
        nrProduse.setFont(new Font("System", 12));
        nrProduse.setLayoutX(783);
        nrProduse.setLayoutY(48);
        menuPanel.getChildren().add(nrProduse);
        */



        /*
        Pane testItemPane = new Pane();
        testItemPane.setStyle("-fx-background-color:  red");
        testItemPane.setPrefWidth(825);
        testItemPane.setPrefHeight(112);
        testItemPane.setLayoutY(112);
        menuPanel.getChildren().add(testItemPane);
        */

        //numeRestaurant = "KFC";
       // orasRestaurant = "Ploiesti";
        int ID = Data.getRestauranteAplicatie().CautaRestaurantOrasID(numeRestaurant,orasRestaurant);
        //System.out.println("Restaurant id este: " + ID);

        //System.out.println(ID);

        //generam interfata pagina comenzi

        Restaurant restaurantSelected = Data.getRestauranteAplicatie().restaurante.elementAt(ID);
        this.restaurantSelected = restaurantSelected;

        int color = 0;
        int windowDown = 0;



        for(int i=0; i < restaurantSelected.getMeniu().size();i++)
        {
            System.out.println(restaurantSelected.getMeniu().elementAt(i));
            Pane tempPane = new Pane();
            if( color % 2 == 0)
            {
                tempPane.setStyle("-fx-background-color:  #D3D3D3");
            }
            else {
                tempPane.setStyle("-fx-background-color:   #E8E8E8");
            }
            color++;

            tempPane.setPrefWidth(825);
            tempPane.setPrefHeight(96);
            tempPane.setLayoutY(windowDown);

            menuPanel.getChildren().add(tempPane);

            ImageView tempImage = new ImageView();
           // Image image = new Image("ApplicationUI/Images/Screenshot_4.png");
            String numeImagine = "ApplicationUI/Images/"+restaurantSelected.getMeniu().elementAt(i).getNume() + ".png";
            System.out.println(numeImagine);
            //tempImage.setImage(new Image("ApplicationUI/Images/Screenshot_4.png"));
            tempImage.setImage(new Image(numeImagine));
            tempImage.setFitWidth(187);
            tempImage.setFitHeight(96);
            tempImage.setLayoutX(0);
            tempImage.setLayoutY(windowDown);
            menuPanel.getChildren().add(tempImage);

            Label tempItemName = new Label();
            tempItemName.setText(restaurantSelected.getMeniu().elementAt(i).getNume());
            tempItemName.setFont(new Font("Sylfaen", 25));
            tempItemName.setLayoutX(222);
            tempItemName.setLayoutY(16 + windowDown);
            menuPanel.getChildren().add(tempItemName);

            Label tempIngredientNume = new Label();
            tempIngredientNume.setText(restaurantSelected.getMeniu().elementAt(i).afisIngrediente());
            tempIngredientNume.setFont(new Font("System", 12));
            tempIngredientNume.setLayoutX(222);
            tempIngredientNume.setLayoutY(45 + windowDown);
            menuPanel.getChildren().add(tempIngredientNume);

            Label tempProdusApp = new Label();
            tempProdusApp.setText(String.valueOf(restaurantSelected.getMeniu().elementAt(i).getPret()) + " ron");
            tempProdusApp.setFont(new Font("Sylfaen", 28));
            tempProdusApp.setLayoutX(503);
            tempProdusApp.setLayoutY(31 + windowDown);
            menuPanel.getChildren().add(tempProdusApp);

            String idProdusButon = String.valueOf(i);

            Button tempOrder = new Button(idProdusButon);
            tempOrder.setId(idProdusButon);
            tempOrder.setText("Add");
            tempOrder.setPrefWidth(94);
            tempOrder.setPrefHeight(39);
            tempOrder.setLayoutX(679);
            tempOrder.setLayoutY(29 + windowDown);
            tempOrder.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
            tempOrder.setTextFill(Paint.valueOf("white"));
            tempOrder.setFont(new Font("System",16));
            tempOrder.setStyle("-fx-font-weight: bold");
            tempOrder.setOnAction(this::addProdus);
            menuPanel.getChildren().add(tempOrder);

            Label tempNrproduse = new Label();
            tempNrproduse.setId("i" +idProdusButon);
            tempNrproduse.setText("(0)");
            tempNrproduse.setFont(new Font("System", 12));
            tempNrproduse.setLayoutX(783);
            tempNrproduse.setLayoutY(40 + windowDown);
            menuPanel.getChildren().add(tempNrproduse);



            windowDown = windowDown + 96;





        }

        for(int i=0; i <= restaurantSelected.getMeniu().size();i++)
        {
            updateInterfata[i] = 0;
        }


    }

    public void addProdus(ActionEvent event){
        //System.out.println("click");
        //System.out.println(((Button)event.getSource()).getId());
        int itemButtonId = Integer.parseInt(((Button)event.getSource()).getId());
        comandaUser.add(restaurantSelected.getMeniu().elementAt(itemButtonId));
        //System.out.println(comandaUser);
        updateInterfata[itemButtonId]++;
        Label lookForLabel = (Label) menuPanel.lookup("#i" + itemButtonId);


        String pastText = lookForLabel.getText();
        pastText = pastText.replace("(","");
        pastText = pastText.replace(")","");
        pastText = pastText.replace("i","");
        System.out.println(pastText);
        String newText = Integer.toString(Integer.parseInt(pastText) + 1);
        System.out.println(newText);

        lookForLabel.setText("(" + newText + ")");





    }

    public void goToCheckout(ActionEvent event) throws IOException {

        data.setComanda(this.comandaUser);
        data.setNumarProduseTemp(this.updateInterfata);
        MainAppUI m = new MainAppUI();
        m.changeScene("checkOut.fxml");

       // System.out.println("Comanda este" + this.comandaUser);
        //data.setComanda(this.comandaUser);



    }

    public void goToOrder(ActionEvent event) throws IOException {
        MainAppUI m = new MainAppUI();

        // m.changeScene("");
        System.out.println(restaurantSelected.getNume());

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
