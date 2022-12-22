package Aplication;

import CSV.AuditLog;
import CSV.CSVInitialize;
import Person.Gender;
import ProdusStuff.Ingredient;
import ProdusStuff.Produs;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainUser {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, IOException {

        //load date in aplicatie
        SQLConnect SQL = new SQLConnect();
        SQL.auditLog("Database restarted...");
        SQL.restartBirthday();
        SQL.restartUser();
        SQL.restartMasina();
        SQL.restartLivrator();
        SQL.restartRestaurant();
        SQL.restartProduse();

        DateOfBirthService DateAplicatie = CSVInitialize.ReadBirthday("src/CSVFiles/birthday.csv");
        UserService UseriAplicatie = CSVInitialize.ReadUser("src/CSVFiles/user.csv",DateAplicatie);
        MasinaService MasiniAplicatie = CSVInitialize.ReadMasina("src/CSVFiles/masina.csv");
        MasiniAplicatie.removeMasina("B45RSD");
        SQL.deleteMasina("B45RSD");
        LivratorService LivratoriAplicatie = CSVInitialize.ReadLivrator("src/CSVFiles/livrator.csv",MasiniAplicatie);

        ProdusService ProduseAplicatie = new ProdusService();
        ProduseAplicatie.addProdus("shaorma de pui", 18.50F, "carne pui,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("shaorma de berbecut", 18.50F, "carne berbecut,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("paste carbonara", 24.50F, "paste,smantana,bacon,ou", "150,50,50,50");
        ProduseAplicatie.addProdus("paste bolognese", 24.50F, "paste,sos de rosii,ciuperci,carne tocata", "150,50,50,50");

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


        //adaugam manual produse in meniu
        RestaurantService RestauranteAplicatie = new RestaurantService();
        RestauranteAplicatie.addRestaurant("Doner", "Ploiesti", meniu);
        //bagam in database
        SQL.instertRestaurant(RestauranteAplicatie.restaurante.lastElement());
        RestauranteAplicatie.addRestaurant("KFC", "Ploiesti", meniuKFC);
        SQL.instertRestaurant(RestauranteAplicatie.restaurante.lastElement());
        RestauranteAplicatie.addRestaurant("Doner Kebap", "Bucuresti", meniu);
        SQL.instertRestaurant(RestauranteAplicatie.restaurante.lastElement());
        RestauranteAplicatie.addRestaurant("Mcdonalds", "Bucuresti", meniuMC);
        SQL.instertRestaurant(RestauranteAplicatie.restaurante.lastElement());
        //insereaza produsele in database
        RestauranteAplicatie.instertMeniuDatabase();

        //Comenzile utilizatorilor deja introduse
        Vector<Produs> comanda1 = new Vector<Produs>();
        comanda1.add(meniu.elementAt(0));
        comanda1.add(meniu.elementAt(1));
        //o comanda de 2 shaorme

        Vector<Produs> comanda2 = new Vector<Produs>();
        comanda2.add(meniuKFC.elementAt(0));
        comanda2.add(meniuKFC.elementAt(1));
        comanda2.add(meniuKFC.elementAt(1));
        comanda2.add(meniuKFC.elementAt(1));


        ComandaService ComenziAplicatie = new ComandaService();
        /*
        ComenziAplicatie.AddComanda("Doner", comanda1, "rebeccastamp", "theresawatson", 3, 1, 2021, "Bulevardul Republicii");
        ComenziAplicatie.AddComanda("Doner Kebap", comanda1, "cassiadrake", "theresawatson", 5, 2, 2021, "Bulevardul Magheru");
        ComenziAplicatie.AddComanda("KFC", comanda2, "rebeccastamp", "theresawatson", 3, 1, 2021, "Bulevardul Republicii");
        ComenziAplicatie.AddComanda("KFC", comanda2, "irfanbanks", "stevenrivera", 5, 3, 2021, "Grigore Cantacuzino ");
        */
        //System.out.println(SQL.numarComenzi());
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



        //System.out.println(ComenziAplicatie);




        //imitam un login
        String user = "maria";
        String password = "maria";

        Scanner scanner = new Scanner(System.in);

        boolean Quit = false;
        while(Quit != true)
        {
            boolean optiuneIncorecta = true;
            System.out.println("Meniu:");
            System.out.println("1 - Login");
            System.out.println("2 - SignUp");
            System.out.println("0 - Quit");
           //System.out.println(optiune);
            System.out.println("Va rugam sa introduceti optiunea dorita:");
            int optiune = scanner.nextInt();
           if( optiune == 1)
           {
               //login
               System.out.println("Introduceti username:");
               String tempUser = scanner.next();
               System.out.println("Introduceti password:");
               String tempPass = scanner.next();
               optiuneIncorecta = false;
               if ( SQL.Login(tempUser,tempPass))
               {
                   SQL.auditLog("Login succesfully " + "(" + tempUser + ")");
                   System.out.println("Login succesfully!");
                   //deschidem app
                   boolean QuitApp = false;
                   String selectedCity = "Bucuresti";
                   while (QuitApp != true)
                   {
                       //optiune close
                       //optiune log out

                       System.out.println();
                       System.out.println("Orasul selectat este " + selectedCity);
                       System.out.println("1 - Plasati o comanda");
                       System.out.println("2 - Selectati Oras");
                       System.out.println("3 - Vizualizati restaurante din orasul selectat");
                       System.out.println("4 - Afisare Istoric comenzi");
                       System.out.println("5 - Afisare date cont utilizator");
                       System.out.println("6 - Log Out");
                       System.out.println("0 - Quit Application!");
                       //System.out.println("---------------------------------");
                      // System.out.println("6 - Log Out");
                       //System.out.println("0 - Quit");
                        System.out.println("Introduceti optiunea dorita:");
                       int optiuneApp = scanner.nextInt();

                       if(optiuneApp == 1)
                       {
                           //cod pt comanda
                           System.out.println("Va rugam sa selectati optiunea restaurantului dorit");
                           int optiuneRestaurant = scanner.nextInt();
                           //return optiune rest oras
                           if(SQL.searchRestaurantPosition(selectedCity,optiuneRestaurant).equals("none"))
                           {
                               System.out.println("Optiune incorecta!");

                           }
                           else{
                               String tempRestaurant = SQL.searchRestaurantPosition(selectedCity,optiuneRestaurant);
                                System.out.println("Restaurantul selectat este " + tempRestaurant + " (" + selectedCity + ")");
                                System.out.println("Selectati optiunea din meniu:");
                                int index = RestauranteAplicatie.CautaRestaurantOrasID(tempRestaurant,selectedCity);

                                //System.out.println(RestauranteAplicatie.elementAt(index).getMeniu());

                                Vector<Produs> tempMeniuRestaurant = RestauranteAplicatie.elementAt(index).getMeniu();
                                for( int i = 0; i < tempMeniuRestaurant.size();i++)
                                {
                                    int tempAux = i+1;
                                    System.out.println("(" + tempAux +")" + " " +tempMeniuRestaurant.elementAt(i));
                                }
                                System.out.println("(!) Renuntati la comanda");
                                System.out.println("(*) Finalizati comanda");

                               Vector<Produs> comandaUser = new Vector<Produs>();
                                boolean optiuneComanda = false;
                                while (optiuneComanda != true)
                                {
                                    String optiuneAleasa = scanner.next();
                                    if(optiuneAleasa.equals("!"))
                                    {
                                        optiuneComanda = true;
                                    }
                                    else {
                                        if(optiuneAleasa.equals("*"))
                                        {
                                            //adaugat camp info suplimentar
                                            System.out.println("Selectati adresa de livrare: ");
                                            String adresaLivrare = scanner.nextLine();
                                            adresaLivrare = scanner.nextLine();
                                            //finalizeaza
                                            System.out.println("Alegeti optiune plata:");
                                            System.out.println("1: Cash");
                                            System.out.println("2: Card ( coming soon )");
                                            System.out.println("Q");

                                            String newOptiuneX = scanner.next();
                                            if(newOptiuneX.equals("1"))
                                            {
                                                //adaugat prod in baza de date

                                                Calendar calendar = Calendar.getInstance();
                                                int dayComanda = calendar.get(Calendar.DAY_OF_MONTH);
                                                int monthComanda = calendar.get(Calendar.MONTH);
                                                int yearComanda = calendar.get(Calendar.YEAR);

                                                String livratorComanda = LivratoriAplicatie.randomLivrator();
                                                ComenziAplicatie.AddComanda(tempRestaurant,comandaUser,tempUser,livratorComanda,dayComanda,monthComanda,yearComanda,adresaLivrare);
                                                System.out.println("Total comanda: " + ComenziAplicatie.TotalComanda2(ComenziAplicatie.comenzi.size()-1));
                                                SQL.auditLog("Comanda nr#" + (ComenziAplicatie.comenzi.size()-1) +"by"  + tempUser + " de la " + tempRestaurant + " (" + selectedCity + ")" + ":" + ComenziAplicatie.TotalComanda2(ComenziAplicatie.comenzi.size()-1));
                                                for( int i=0; i< comandaUser.size();i++)
                                                {
                                                    String tempNumeProdus = comandaUser.elementAt(i).getNume();
                                                   // System.out.println(tempNumeProdus);
                                                    SQL.insertOrder(ComenziAplicatie.comenzi.size()-1,tempRestaurant,tempUser,livratorComanda, dayComanda, monthComanda, yearComanda,adresaLivrare, selectedCity, tempNumeProdus);
                                                }



                                                optiuneComanda = true;
                                                int max = 120;
                                                int min = 40;
                                                Random random = new Random();
                                                int randomNumber = random.nextInt(max - min) + min;
                                                System.out.println("Comanda Plasata! Timp estimat: " + randomNumber + "m");

                                                //total de plata este
                                            }
                                            else{
                                                optiuneComanda = true;
                                                System.out.println("Comanda a fost anulata!");
                                            }

                                        }
                                        else {
                                            int newIndex = Integer.parseInt(optiuneAleasa) -1;
                                            System.out.println("Introduceti numarul de produse:");
                                            int nrproduse = scanner.nextInt();
                                            for( int i=1; i <= nrproduse;i++)
                                            {
                                                comandaUser.add(tempMeniuRestaurant.elementAt(newIndex));
                                            }
                                            System.out.println("Produsele au fost adaugate!");
                                        }
                                    }
                                }

                                //mini menu comanda produse



                               //checkout
                           }
                       }

                       if(optiuneApp == 2)
                       {
                           System.out.println("Orasele disponibile sunt: ");
                           System.out.println("1 - Bucuresti");
                           System.out.println("2 - Ploiesti");
                           selectedCity = "Bucuresti";
                           int optiuneOras = scanner.nextInt();
                           if( optiuneOras == 1)
                           {
                               selectedCity = "Bucuresti";
                           }

                           if( optiuneOras == 2)
                           {
                               selectedCity = "Ploiesti";
                           }

                           System.out.println("Orasul a fost selectat cu succes!" + "(" + selectedCity + ")");
                           SQL.auditLog("Schimbare oras selectat");

                       }

                       if(optiuneApp == 3)
                       {
                            System.out.println("Restaurantele disponibile sunt: ");
                            SQL.searchRestaurantsCity(selectedCity);
                       }

                       if(optiuneApp == 4)
                       {
                           //istoric comenzi
                           System.out.println("Istoric comenzi:");
                           ComenziAplicatie.IstoricComenziUser(tempUser);
                           SQL.auditLog("Request afisare istoric comenzi al userului:" + tempUser);
                       }

                       if( optiuneApp == 5)
                       {
                           System.out.println("Date cont:");
                           System.out.println("Username: " + tempUser);
                           SQL.searchDataUser(tempUser);
                           SQL.auditLog("Request date cont");
                       }

                        if(optiuneApp == 6)
                        {
                            System.out.println("Logout Succesfully!");
                            SQL.auditLog("Logout efectuat "+ "(" + tempUser + ")");
                            QuitApp = true;
                        }
                        //quit app direct

                   }


               }
               else {
                   System.out.println("Username sau parola incorecta!");
               }

           }
           if ( optiune == 2)
           {
               //register
               optiuneIncorecta = false;
               System.out.println("Introduceti username:");
               String tempUsername = scanner.next();
               System.out.println("Introduceti password:");
               String tempPass = scanner.next();
               while(!passwordIsValid(tempPass))
               {
                   System.out.println("Va rugam sa introduceti o parola valida!");
                   tempPass = scanner.next();

               }



               System.out.println("Introduceti zi de nastere: ");
               int birthdayDay = scanner.nextInt();
               System.out.println("Introduceti luna de nastere: ");
               int birthdayMonth = scanner.nextInt();
               System.out.println("Introduceti an de nastere: ");
               int birthdayYear = scanner.nextInt();
               DateAplicatie.addDateOfBirth(birthdayDay, birthdayMonth, birthdayYear);
               System.out.println("Introduceti nume: ");
               String tempNume = scanner.next();
               System.out.println("Introduceti prenume: ");
               String tempPrenume = scanner.next();
               System.out.println("Introduceti email: ");
               String tempEmail = scanner.next();
               System.out.println("Introduceti telefon: ");
               String tempTelefon = scanner.next();
               System.out.println("Introduceti gender(male/female): ");
               String tempGender = scanner.next();
               if (tempGender.equals("female"))
                   UseriAplicatie.addUser(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.FEMALE, DateAplicatie.lastElement(), tempUsername);
               else
                   UseriAplicatie.addUser(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.MALE, DateAplicatie.lastElement(), tempUsername);
               System.out.println("A fost adaugat userul " + UseriAplicatie.lastElement().getUsername());
               AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Add utilizator");
               SQL.auditLog("Add utilizator");
               SQL.insertUser(UseriAplicatie.lastElement());
               SQL.insertBirthday(UseriAplicatie.lastElement().getBirthday());

               SQL.SignUp(tempUsername,tempPass);
               System.out.println("User SignUp Succesfully!");



           }
           if (optiune == 0)
           {
               //quit
               optiuneIncorecta = false;
               Quit = true;
           }

           if( optiuneIncorecta == true)
           {
               System.out.println("Optiunea aleasa este incorecta!");
           }
        }

    }

    public static boolean passwordIsValid(String passwordX)
    {
        boolean valid = true;
        if(passwordX.length() < 8)
        {
            valid = false;
            System.out.println("Parola trebuie sa aibe minim 8 caractere");
        }

        String upperCase = "(.*[A-Z].*)";
        if(!passwordX.matches(upperCase))
        {
            valid = false;
            System.out.println("Parola trebuie sa aibe minim o litera mare");
        }

        String numbers = "(.*[0-9].*)";
        if( !passwordX.matches(numbers))
        {
            System.out.println("Parola trebuie sa aibe minim o cifra");
            valid = false;
        }
        return valid;

    }
}

