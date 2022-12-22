package ApplicationUI;


import Aplication.*;
import CSV.CSVInitialize;
import Person.DateOfBirth;
import Person.Gender;
import ProdusStuff.Ingredient;
import ProdusStuff.Produs;
import UserStuff.User;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;


public class LoadData {

    private DateOfBirthService DateAplicatie;
    public UserService UseriAplicatie;
    private MasinaService MasiniAplicatie;
    private LivratorService LivratoriAplicatie;
    private ProdusService ProduseAplicatie;
    private RestaurantService RestauranteAplicatie;
    private ComandaService ComenziAplicatie;

    public ComandaService getComenziAplicatie() {
        return ComenziAplicatie;
    }

    public RestaurantService getRestauranteAplicatie(){
        return RestauranteAplicatie;
    }

    public LivratorService getLivratoriAplicatie(){
        return LivratoriAplicatie;
    }

    public LoadData() throws SQLException, IllegalAccessException, InstantiationException {

        SQLConnect SQL = new SQLConnect();
        SQL.restartMasina();
        SQL.restartLivrator();
        this.UseriAplicatie = new UserService();


        this.DateAplicatie = CSVInitialize.ReadBirthday("src/CSVFiles/birthday.csv");
        //load useri din Database
        int numberUsers = SQL.getNumberOfUsers();
        //System.out.println(numberUsers);
        Vector<Vector<String>> usersFromSQL = SQL.getUsers();
        for( int i=0; i< numberUsers; i++)
        {
            //System.out.println(usersFromSQL.elementAt(i).elementAt(4));
            //System.out.println("user added");
            if(usersFromSQL.elementAt(i).elementAt(4).equals("FEMALE"))
            {
                this.UseriAplicatie.addUser(usersFromSQL.elementAt(i).elementAt(0),usersFromSQL.elementAt(i).elementAt(1),
                        usersFromSQL.elementAt(i).elementAt(2),usersFromSQL.elementAt(i).elementAt(3), Gender.FEMALE,
                        new DateOfBirth(Integer.parseInt(usersFromSQL.elementAt(i).elementAt(5)),Integer.parseInt(usersFromSQL.elementAt(i).elementAt(6)),
                                Integer.parseInt(usersFromSQL.elementAt(i).elementAt(7))),usersFromSQL.elementAt(i).elementAt(8));
            }
            else{
                this.UseriAplicatie.addUser(usersFromSQL.elementAt(i).elementAt(0),usersFromSQL.elementAt(i).elementAt(1),
                        usersFromSQL.elementAt(i).elementAt(2),usersFromSQL.elementAt(i).elementAt(3), Gender.MALE,
                        new DateOfBirth(Integer.parseInt(usersFromSQL.elementAt(i).elementAt(5)),Integer.parseInt(usersFromSQL.elementAt(i).elementAt(6)),
                                Integer.parseInt(usersFromSQL.elementAt(i).elementAt(7))),usersFromSQL.elementAt(i).elementAt(8));
            }

        }

        //System.out.println(UseriAplicatie);

        //inseram masinile in aplicatie
        this.MasiniAplicatie = CSVInitialize.ReadMasina("src/CSVFiles/masina.csv");
        this.MasiniAplicatie.removeMasina("B45RSD");
        SQL.deleteMasina("B45RSD");

        //inseram livratorii
        this.LivratoriAplicatie = CSVInitialize.ReadLivrator("src/CSVFiles/livrator.csv",MasiniAplicatie);

        //inseram produsele

        this.ProduseAplicatie = new ProdusService();
        ProduseAplicatie.addProdus("shaorma de pui", 18.50F, "carne pui,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("shaorma de berbecut", 18.50F, "carne berbecut,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("paste carbonara", 24.50F, "paste,smantana,bacon,ou", "150,50,50,50");
        ProduseAplicatie.addProdus("paste bolognese", 24.50F, "paste,sos de rosii,ciuperci,carne tocata", "150,50,50,50");

        //inseram ingredientele
        List<Ingredient> ingrediente = new ArrayList<Ingredient>();
        ingrediente.add(new Ingredient("ceapa", 50));
        ingrediente.add(new Ingredient("cartofi", 150));
        ingrediente.add(new Ingredient("carne de pui", 250));
        ingrediente.add(new Ingredient("maioneza", 50));
        ingrediente.add(new Ingredient("castraveti murati", 50));
        ingrediente.add(new Ingredient("varza", 50));
        //adaugam manual produse in meniu

        List<Ingredient> ingrediente2 = new ArrayList<Ingredient>();
        ingrediente2.add(new Ingredient("ceapa", 50));
        ingrediente2.add(new Ingredient("cartofi", 150));
        ingrediente2.add(new Ingredient("carne de berbecut", 250));
        ingrediente2.add(new Ingredient("maioneza", 50));
        ingrediente2.add(new Ingredient("castraveti murati", 50));
        ingrediente2.add(new Ingredient("varza", 50));

        List<Ingredient> ingrediente3 = new ArrayList<Ingredient>();
        ingrediente3.add(new Ingredient("cartofi", 150));
        //ingrediente.add(new Ingredient());
        ingrediente3.add(new Ingredient("suc", 500));
        ingrediente3.add(new Ingredient("bucati crispy de pui", 200));
        ingrediente3.add(new Ingredient("sos glen usturoi", 50));

        List<Ingredient> ingrediente4 = new ArrayList<Ingredient>();
        ingrediente4.add(new Ingredient("piept de pui", 150));
        ingrediente4.add(new Ingredient("chifle", 80));
        ingrediente4.add(new Ingredient("sos mustar cu miere", 40));
        ingrediente4.add(new Ingredient("ceapa", 40));
        ingrediente4.add(new Ingredient("salata Eisberg", 40));
        ingrediente4.add(new Ingredient("rosie", 40));

        List<Ingredient> ingrediente5 = new ArrayList<Ingredient>();
        ingrediente5.add(new Ingredient("ceapa", 50));
        ingrediente5.add(new Ingredient("cartofi", 150));
        ingrediente5.add(new Ingredient("carne de pui", 250));
        ingrediente5.add(new Ingredient("maioneza", 50));
        ingrediente5.add(new Ingredient("castraveti murati", 50));
        ingrediente5.add(new Ingredient("varza", 50));
        ingrediente5.add(new Ingredient("lipie", 120));

        List<Ingredient> ingrediente6 = new ArrayList<Ingredient>();
        ingrediente6.add(new Ingredient("ceapa", 50));
        ingrediente6.add(new Ingredient("cartofi", 150));
        ingrediente6.add(new Ingredient("carne de berbecut", 250));
        ingrediente6.add(new Ingredient("maioneza", 50));
        ingrediente6.add(new Ingredient("castraveti murati", 50));
        ingrediente6.add(new Ingredient("varza", 50));
        ingrediente6.add(new Ingredient("lipie", 120));

        List<Ingredient> ingrediente7 = new ArrayList<Ingredient>();
        ingrediente7.add(new Ingredient("Crispy Strips", 150));
        ingrediente7.add(new Ingredient("Tortila", 50));
        ingrediente7.add(new Ingredient("rosii", 30));
        ingrediente7.add(new Ingredient("salata iceberg", 40));
        ingrediente7.add(new Ingredient("sos burger", 30));

        List<Ingredient> ingrediente8 = new ArrayList<Ingredient>();
        ingrediente8.add(new Ingredient("Fillet Bites", 200));
        ingrediente8.add(new Ingredient("Hot Wings", 200));
        ingrediente8.add(new Ingredient("cartofi", 150));

        List<Ingredient> ingrediente9 = new ArrayList<Ingredient>();
        ingrediente9.add(new Ingredient("suc", 400));
        ingrediente9.add(new Ingredient("crispy sandwich", 250));
        ingrediente9.add(new Ingredient("Hot Wings", 150));
        ingrediente9.add(new Ingredient("cartofi", 100));
        ingrediente9.add(new Ingredient("sos glen usturoi", 50));

        List<Ingredient> ingrediente10 = new ArrayList<Ingredient>();
        ingrediente10.add(new Ingredient("chifle", 50));
        ingrediente10.add(new Ingredient("carne de vita", 100));
        ingrediente10.add(new Ingredient("bacon", 20));
        ingrediente10.add(new Ingredient("ceapa", 30));
        ingrediente10.add(new Ingredient("sos big tasty", 30));
        ingrediente10.add(new Ingredient("rosi", 30));
        ingrediente10.add(new Ingredient("branza Emmentaler", 20));

        Vector<Produs> meniu = new Vector<Produs>();
        meniu.add(new Produs("Shaorma Pui", 20.50F, ingrediente));
        meniu.add(new Produs("Shaorma Berbecut", 22.50F, ingrediente2));
        meniu.add(new Produs("Doner Box Pui", 16F, ingrediente5));
        meniu.add(new Produs("Doner Box Berbecut", 18F, ingrediente6));

        Vector<Produs> meniuKFC = new Vector<Produs>();
        //meniuKFC.add(new Produs(()))
        meniuKFC.add(new Produs("Meniu Crispy Strips", 30F, ingrediente3));
        meniuKFC.add(new Produs("Twister Classic", 13.50F, ingrediente7));
        meniuKFC.add(new Produs("So Good Bucket", 40F, ingrediente8));
        meniuKFC.add(new Produs("Ceva Box", 25.50F, ingrediente9));
        Vector<Produs> meniuMC = new Vector<Produs>();
        //meniuMC.add(new Produs())
        meniuMC.add(new Produs("Chicken Grill", 10F, ingrediente4));
        meniuMC.add(new Produs("Big Tasty Bacon", 16F, ingrediente10));

        //inseram restaurante
        this.RestauranteAplicatie = new RestaurantService();
        RestauranteAplicatie.addRestaurant("Doner", "Ploiesti", meniu);
        RestauranteAplicatie.addRestaurant("KFC", "Ploiesti", meniuKFC);
        RestauranteAplicatie.addRestaurant("Doner Kebap", "Bucuresti", meniu);
        RestauranteAplicatie.addRestaurant("Mcdonalds", "Bucuresti", meniuMC);

        //inseram comenzi existente
        Vector<Produs> comanda1 = new Vector<Produs>();
        comanda1.add(meniu.elementAt(0));
        comanda1.add(meniu.elementAt(1));

        Vector<Produs> comanda2 = new Vector<Produs>();
        comanda2.add(meniuKFC.elementAt(0));
        comanda2.add(meniuKFC.elementAt(1));
        comanda2.add(meniuKFC.elementAt(1));
        comanda2.add(meniuKFC.elementAt(1));

        this.ComenziAplicatie = new ComandaService();
        //inseram comenzile in aplicatie din SQL

        for(int i=0; i<=SQL.numarComenzi(); i++)
        {
            Vector<Produs> comanda = new Vector<>();
            Vector<String> produseDinComanda = SQL.numarProduseComanda(i);
            // System.out.println(produseDinComanda);
            //functie oras din comanda
            //functie restaurant nume
            //functie cautare pozitie
            String orasComanda = SQL.getOrasComanda(i);
            String numeRestaurantComanda = SQL.getNumeRestaurantComanda(i);
            // System.out.println(RestauranteAplicatie.CautaRestaurantOrasID(numeRestaurantComanda,orasComanda));
            int idRestaurantX = RestauranteAplicatie.CautaRestaurantOrasID(numeRestaurantComanda,orasComanda);
            for( int j=0; j< produseDinComanda.size();j++)
            {
                // System.out.println(produseDinComanda.elementAt(j));
                // comanda.add();
                Vector<Produs> produse = new Vector<>();
                produse = RestauranteAplicatie.elementAt(idRestaurantX).getMeniu();
                int idProdus = 0;
                for( int o=0; o< produse.size();o++)
                {
                    if(produse.elementAt(o).getNume().equals(produseDinComanda.elementAt(j)))
                    {
                        idProdus = o;
                    }
                }

                comanda.add(RestauranteAplicatie.elementAt(idRestaurantX).getMeniu().elementAt(idProdus));
            }
            // System.out.println(comanda);
            //comanda nume restaurant oras
            String usernameComanda = SQL.getClientComanda(i);
            String livratorComanda = SQL.getLivratorComanda(i);
            Vector<String> aditionalData = SQL.getDateAditionaleComanda(i);
            int dayComanda = Integer.parseInt(aditionalData.elementAt(1));
            int monthComanda = Integer.parseInt(aditionalData.elementAt(2));
            int yearComanda = Integer.parseInt((aditionalData.elementAt(3)));
            String adresaComanda = aditionalData.elementAt(0);

            ComenziAplicatie.AddComanda(numeRestaurantComanda,comanda,usernameComanda,livratorComanda,dayComanda,monthComanda,yearComanda,adresaComanda);

        }










    }

}
