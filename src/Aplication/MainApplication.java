package Aplication;

import CSV.AuditLog;
import CSV.CSVInitialize;
import Person.DateOfBirth;
import Person.Gender;
import ProdusStuff.Ingredient;
import ProdusStuff.Produs;
import RestaurantStuff.Livrator;
import RestaurantStuff.Masina;
import UserStuff.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainApplication {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, SQLException {
        //aplicatia vine cu un set de date la care se mai pot adauga diferite date
        //aplicatia are un meniu interactiv, rulati aplicatia pentru acesta.

        //9 clase (8 entitati), 7 clase Service (6 active), 20 comenzi
        //mai jos se afla un set de 4 date/obiect deja introduse


        //datele de nastere din aplicatie
        //un vector de obiecte tip DateOfBirth

        //Fisiere CSV pentru clasele: Masina,DateOfBirth,Livratori,User
        //Fisiere CSV fara Singletone:Birthday
        //Audit log in fisierul : AuditLog
        /*
        DateOfBirthService DateAplicatie = new DateOfBirthService();
        DateAplicatie.addDateOfBirth(18, 4, 2021);
        DateAplicatie.addDateOfBirth(14, 6, 2021);
        DateAplicatie.addDateOfBirth(15, 8, 2021);
        DateAplicatie.addDateOfBirth(26, 3, 2021);

         */
        //System.out.println(DateAplicatie);
        SQLConnect SQL = new SQLConnect();
        SQL.auditLog("Database restarted...");
        SQL.restartBirthday();
        SQL.restartUser();
        SQL.restartMasina();
        SQL.restartLivrator();
        SQL.restartRestaurant();
        SQL.restartProduse();


        DateOfBirthService DateAplicatie = CSVInitialize.ReadBirthday("src/CSVFiles/birthday.csv");

        /*
        UserService UseriAplicatie = new UserService();
        //UseriAplicatie.addUser("Rebecca","Stamp","rebeccastamp@gmail.com","072260567", Gender.FEMALE, DateAplicatie.elementAt(1),"rebeccastamp");
        UseriAplicatie.addUser("Rebecca", "Stamp", "rebeccastamp@gmail.com", "072260567", Gender.FEMALE, DateAplicatie.elementAt(0), "rebeccastamp");
        UseriAplicatie.addUser("Irfan", "Banks", "irfanbanks@gmail.com", "0987654318", Gender.MALE, DateAplicatie.elementAt(1), "irfanbanks");
        UseriAplicatie.addUser("Cassia", "Drake", "cassiadrake@gmail.com", "0734565677", Gender.FEMALE, DateAplicatie.elementAt(2), "cassiadrake");
        UseriAplicatie.addUser("Conner", "Mcdowell", "connermcdowell@gmail.com", "0722455588", Gender.MALE, DateAplicatie.elementAt(3), "connermcdowell");
        // UseriAplicatie.addUser("Rebecca","Stamp","rebeccastamp@gmail.com","072260567", Gender.FEMALE, DateOfBirth(10,10,10),"rebeccastamp");
        //System.out.println(UseriAplicatie);
        */
        UserService UseriAplicatie = CSVInitialize.ReadUser("src/CSVFiles/user.csv",DateAplicatie);
        //UseriAplicatie.getBirthdayPrize();


        //masina poate sa existe fara livrator
        //creem obiect masina care il atribuim livratorului

        /*
        MasinaService MasiniAplicatie = new MasinaService();
        MasiniAplicatie.addMasina("Audi", "A4", 2016, "PH35XTZ");
        MasiniAplicatie.addMasina("Ford", "Focus", 2018, "PH45UIG");
        MasiniAplicatie.addMasina("Dacia", "Duster", 2015, "B30CRX");
        MasiniAplicatie.addMasina("Renault", "Clio", 2019, "B55FRD");
        MasiniAplicatie.addMasina("Audi", "A8", 2020, "B45RSD");
        */
        //
        MasinaService MasiniAplicatie = CSVInitialize.ReadMasina("src/CSVFiles/masina.csv");
        //System.out.println(MasiniAplicatie);
        MasiniAplicatie.removeMasina("B45RSD");
        SQL.deleteMasina("B45RSD");
        //System.out.println(MasiniAplicatie);


        //creem data de nastere direct livratorului
        //model diferit de user
        //LivratorService LivratoriAplicatie = new LivratorService();

        /*
        LivratorService LivratoriAplicatie = new LivratorService();
        LivratoriAplicatie.addLivrator("Theresa", "Watson", "theresawatson@gmail.com", "0723454556", Gender.FEMALE, 10, 12, 2020, "theresawatson", MasiniAplicatie.elementAt(0));
        LivratoriAplicatie.addLivrator("Peter", "Gonzales", "petergonzales@gmail.com", "0728342213", Gender.MALE, 12, 5, 2000, "petergonzales", MasiniAplicatie.elementAt(1));
        LivratoriAplicatie.addLivrator("Katherine", "Karter", "katherinekarter@gmail.com", "0724353578", Gender.FEMALE, 15, 8, 2021, "katherinekarter@gmail.com", MasiniAplicatie.elementAt(2));
        LivratoriAplicatie.addLivrator("Steven", "Rivera", "stevenrivera@gmail.com", "0726554421", Gender.MALE, 4, 9, 2014, "stevenrivera", MasiniAplicatie.elementAt(3));
      */
        LivratorService LivratoriAplicatie = CSVInitialize.ReadLivrator("src/CSVFiles/livrator.csv",MasiniAplicatie);
        //System.out.println(LivratoriAplicatie);


        ProdusService ProduseAplicatie = new ProdusService();
        ProduseAplicatie.addProdus("shaorma de pui", 18.50F, "carne pui,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("shaorma de berbecut", 18.50F, "carne berbecut,ceapa,cartofi,maioneza,varza,castraveti murati", "100,20,50,20,20,20");
        ProduseAplicatie.addProdus("paste carbonara", 24.50F, "paste,smantana,bacon,ou", "150,50,50,50");
        ProduseAplicatie.addProdus("paste bolognese", 24.50F, "paste,sos de rosii,ciuperci,carne tocata", "150,50,50,50");

        //System.out.println(ProduseAplicatie);


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

        //System.out.println(RestauranteAplicatie);

        // RestauranteAplicatie.NumarRestauranteOras("Ploiesti");
        // RestauranteAplicatie.NumarRestauranteOras("Bucuresti");
        //RestauranteAplicatie.NumarRestauranteOras("Cluj");

        // RestauranteAplicatie.CautaRestaurant("KFC");
        // RestauranteAplicatie.CautaRestaurant("Prestij");

        //RestauranteAplicatie.CautaRestaurantOras("KFC","Ploiesti");
        //RestauranteAplicatie.CautaRestaurantOras("Prestij","Ploiesti");

        //--------------------------------------------------------
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

        //ComenziAplicatie.IstoricComenziUser("rebeccastamp");
        //ComenziAplicatie.IstoricComenziUser("Gicutu");

        //ComenziAplicatie.IstoricComenziLivrator("theresawatson");
        //ComenziAplicatie.IstoricComenziLivrator("Ionica");

        //ComenziAplicatie.TotalComanda(0);

        //ComenziAplicatie.TotalComenziUser("rebeccastamp");

        //ComenziAplicatie.AfisSumaAplicatie();

        //ComenziAplicatie.AfisSumaRestaurant("Doner");
        //ComenziAplicatie.AfisSumaRestaurant("KFC");

        System.out.println("Bine ati venit in aplicatia Food Delivery!");
        System.out.println("Aplicatia vine cu un set de date predefinite, la care se mai pot adauga date!");

        System.out.println(" 0:Iesiti din aplicatie");
        System.out.println(" 1:Afisare Date de nastere utilizatori"); //din database
        System.out.println(" 2:Afisare utilizatori"); //din database
        System.out.println(" 3:Add utilizator");
        System.out.println(" 4:Afisare livratori");
        System.out.println(" 5:Add livrator");
        //sunt masinile firmei care sunt atribuite curierilor
        System.out.println(" 6:Afisare masini aplicatie Food Delivery");
        System.out.println(" 7:Add masina");
        System.out.println(" 8:Afisare toate restaurantele aplicatiei");
        System.out.println(" 9:Afisare meniu restaurant");
        System.out.println("10:Afisare toate comenzile aplicatiei");
        System.out.println("11:Afisare utilizatori a caror zi de nastere este astazi");
        System.out.println("12:Afisare numar restaurante in orasul x");
        System.out.println("13:Cautare restaurant in aplicatie");
        System.out.println("14:Cautare restaurant in orasul x");
        System.out.println("15:Afisare total pret comanda x");
        System.out.println("16:Afisare total comenzi ale userului x");
        System.out.println("17:Afisare Istoric Comenzi user x");
        System.out.println("18:Afisare Istoric Comenzi livrator x");
        System.out.println("19:Afisare Suma Totala facuta de aplicatie");
        System.out.println("20:Afisare Suma castigata de restaurantul x");
        System.out.println("100:Audit Log");
        //un afis folosind db

        //System.out.println("Add comanda");
        //System.out.println("Add un produs la meniul unui restaurant");
        //System.out.println("Add restaurant");
        //System.out.println("Afis Ingrediente");
        //presupunem ca lumea comanda doar de la restaurantele din orasul in care se afla


        //---------while loop
        Scanner scanner = new Scanner(System.in);
        int choice = 99;
        //poate selecta doar o optiune corecta
        //!!!
        while (choice != 0) {
            boolean raspunsValid = false;
            System.out.println("Selectati optiunea dorita: ");
            try {
                choice = scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Selectati o optiune valida !");
                choice = 0;
            }
            if (choice == 1) {
                raspunsValid = true;
                System.out.println(DateAplicatie);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare Date de nastere utilizatori");
                SQL.auditLog("Afisare Date de nastere utilizatori");
            }

            if (choice == 2) {
                raspunsValid = true;
                System.out.println(UseriAplicatie);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare utilizatori");
                SQL.auditLog("Afisare utilizatori");
            }

            if (choice == 3) {
                raspunsValid = true;
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
                System.out.println("Introduceti username");
                String tempUsername = scanner.next();
                if (tempGender.equals("female"))
                    UseriAplicatie.addUser(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.FEMALE, DateAplicatie.lastElement(), tempUsername);
                else
                    UseriAplicatie.addUser(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.MALE, DateAplicatie.lastElement(), tempUsername);
                System.out.println("A fost adaugat userul " + UseriAplicatie.lastElement().getUsername());
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Add utilizator");
                SQL.auditLog("Add utilizator");
                SQL.insertUser(UseriAplicatie.lastElement());
                SQL.insertBirthday(UseriAplicatie.lastElement().getBirthday());
            }

            if (choice == 4) {
                raspunsValid = true;
                System.out.println(LivratoriAplicatie);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare livratori");
                SQL.auditLog("Afisare livratori");
            }

            if (choice == 5) {
                raspunsValid = true;
                System.out.println("Introduceti zi de nastere: ");
                int birthdayDay = scanner.nextInt();
                System.out.println("Introduceti luna de nastere: ");
                int birthdayMonth = scanner.nextInt();
                System.out.println("Introduceti an de nastere: ");
                int birthdayYear = scanner.nextInt();
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
                System.out.println("Introduceti username");
                String tempUsername = scanner.next();
                System.out.println("Introduceti marca masina");
                String tempMarca = scanner.next();
                System.out.println("Introduceti model masina");
                String tempModel = scanner.next();
                System.out.println("Introduceti an masina");
                int tempAn = scanner.nextInt();
                System.out.println("Introduceti numar de inmatriculare masina");
                String tempNrInmatriculare = scanner.next();
                MasiniAplicatie.addMasina(tempMarca, tempModel, tempAn, tempNrInmatriculare);

                if (tempGender.equals("female"))
                    LivratoriAplicatie.addLivrator(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.FEMALE, birthdayDay, birthdayMonth, birthdayYear, tempUsername, MasiniAplicatie.lastElement());
                else
                    LivratoriAplicatie.addLivrator(tempNume, tempPrenume, tempEmail, tempTelefon, Gender.MALE, birthdayDay, birthdayMonth, birthdayYear, tempUsername, MasiniAplicatie.lastElement());
                System.out.println("A fost adaugat livratorul " + LivratoriAplicatie.lastElement().getUsername());
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Add livrator");
                SQL.insertLivrator(LivratoriAplicatie.lastElement());
                SQL.auditLog("Add livrator");
            }

            if (choice == 6) {
                raspunsValid = true;
                System.out.println(MasiniAplicatie);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare masini aplicatie Food Delivery");
                SQL.auditLog("Afisare masini aplicatie Food Delivery");
            }

            if (choice == 7) {
                raspunsValid = true;
                System.out.println("Introduceti marca masina");
                String tempMarca = scanner.next();
                System.out.println("Introduceti model masina");
                String tempModel = scanner.next();
                System.out.println("Introduceti an masina");
                int tempAn = scanner.nextInt();
                System.out.println("Introduceti numar de inmatriculare masina");
                String tempNrInmatriculare = scanner.next();
                MasiniAplicatie.addMasina(tempMarca, tempModel, tempAn, tempNrInmatriculare);
                System.out.println("A fost adaugata masina" + MasiniAplicatie.lastElement().getNumarInmatriculare());
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Add masina");
                SQL.insertMasina(MasiniAplicatie.lastElement());
                SQL.auditLog("Add masina");

            }

            if (choice == 8) {
                raspunsValid = true;
                RestauranteAplicatie.AfisRestaurante();
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare toate restaurantele aplicatiei");
                SQL.auditLog("Afisare toate restaurantele aplicatiei");
            }

            if (choice == 9) {
                raspunsValid = true;
                System.out.println("Va rugam sa selectati numarul restaurantului dorit");
                int indix = scanner.nextInt();
                indix = indix - 1;
                System.out.println(RestauranteAplicatie.elementAt(indix).getMeniu());
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare meniu restaurant");
                SQL.auditLog("Afisare meniu restaurant");
            }

            if (choice == 10) {
                raspunsValid = true;
                ComenziAplicatie.AfisToateComenzi();
                System.out.println(ComenziAplicatie);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare toate comenzile aplicatiei");
                SQL.auditLog("Afisare toate comenzile aplicatiei");
            }

            if (choice == 11) {
                raspunsValid = true;
                UseriAplicatie.getBirthdayPrize();
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare utilizatori a caror zi de nastere este astazi");
                SQL.auditLog("Afisare utilizatori a caror zi de nastere este astazi");
            }

            if (choice == 12) {
                raspunsValid = true;
                System.out.println("Introduceti orasul");
                String tempOras = scanner.next();
                RestauranteAplicatie.NumarRestauranteOras(tempOras);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare numar restaurante in orasul x");
                SQL.auditLog("Afisare numar restaurante in orasul x");
            }

            if (choice == 13) {
                raspunsValid = true;
                System.out.println("Introduceti numele restaurantului");
                String tempNume = scanner.next();
                RestauranteAplicatie.CautaRestaurant(tempNume);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Cautare restaurant in aplicatie");
                SQL.auditLog("Cautare restaurant in aplicatie");
            }

            if (choice == 14) {
                raspunsValid = true;
                System.out.println("Introduceti numele restaurantului");
                String tempNume = scanner.next();
                System.out.println("Introduceti orasul");
                String tempOras = scanner.next();
                RestauranteAplicatie.CautaRestaurantOras(tempNume, tempOras);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Cautare restaurant in orasul x");
                SQL.auditLog("Cautare restaurant in orasul x");
            }

            if (choice == 15) {
                raspunsValid = true;
                System.out.println("Introduceti id comanda");
                int tempid = scanner.nextInt();
                tempid = tempid - 1;
                ComenziAplicatie.TotalComanda(tempid);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare total pret comanda x");
                SQL.auditLog("Afisare total pret comanda x");
            }

            if (choice == 16) {
                raspunsValid = true;
                System.out.println("Introduceti username user");
                String tempusername = scanner.next();
                ComenziAplicatie.TotalComenziUser(tempusername);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare total comenzi ale userului x");
                SQL.auditLog("Afisare total comenzi ale userului x");
            }

            if (choice == 17) {
                raspunsValid = true;
                System.out.println("Introduceti username user");
                String tempusername = scanner.next();
                System.out.println("Istoric comenzi user : ");
                ComenziAplicatie.IstoricComenziUser(tempusername);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare Istoric Comenzi user x");
                SQL.auditLog("Afisare Istoric Comenzi user x");
            }

            if (choice == 18) {
                raspunsValid = true;
                System.out.println("Introduceti username livrator");
                String tempusername = scanner.next();
                System.out.println("Istoric comenzi livrator : ");
                ComenziAplicatie.IstoricComenziLivrator(tempusername);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare Istoric Comenzi livrator x");
                SQL.auditLog("Afisare Istoric Comenzi livrator x");
            }

            if (choice == 19) {
                raspunsValid = true;
                ComenziAplicatie.AfisSumaAplicatie();
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare Suma Totala facuta de aplicatie");
                SQL.auditLog("Afisare Suma Totala facuta de aplicatie");
            }

            if (choice == 20) {
                raspunsValid = true;
                System.out.println("Introduceti nume restaurant");
                String tempNume = scanner.next();
                ComenziAplicatie.AfisSumaRestaurant(tempNume);
                AuditLog.AuditLogService("src/CSVFiles/AuditLog.csv","Afisare Suma castigata de restaurantul x");
                SQL.auditLog("Afisare Suma castigata de restaurantul x");
            }
            if (choice == 100){
                raspunsValid = true;
                SQL.showAuditLog();
            }
            //optional ceva care sa zica daca este o optiune valida sau nu
            if ( raspunsValid == false )
                System.out.println("Incorect Option !");
            /*
            if(choice == 21)
            {
                //read pe baza de date
                //operatie baza pe database
                RestauranteAplicatie.AfisRestaurante();
                System.out.println("Introduceti numarul restaurantului de la care doriti sa comandati");
                int tempIndex = scanner.nextInt();
                tempIndex=tempIndex-1;
                System.out.println("Restaurantul selectat este " + RestauranteAplicatie.elementAt(tempIndex).getNume() + "," +
                        RestauranteAplicatie.elementAt(tempIndex).getOras());
                System.out.println(RestauranteAplicatie.elementAt(tempIndex).getMeniu());
                System.out.println("Introduceti numarul de preparate ");
                int tempNrPreparate = scanner.nextInt();
                Vector<Produs> tempVector = new Vector<Produs>();
                for(int i =1; i<=tempNrPreparate;i++)
                    System.out.println("Introduceti numarul produsului");
                    tempVector.add()


                System.out.println("Comanda dumneavoastra cu numarul#" + "a fost procesata cu succes");
            }
            */


        }

    }
}





