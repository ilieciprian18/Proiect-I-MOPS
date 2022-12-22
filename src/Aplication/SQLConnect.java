package Aplication;

import Person.DateOfBirth;
import ProdusStuff.Produs;
import RestaurantStuff.Livrator;
import RestaurantStuff.Masina;
import RestaurantStuff.Restaurant;
import UserStuff.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class SQLConnect {
    private final String MySQLname = "jdbc:mysql://localhost:3306/javajdbc";
    private final String username ="root";
    private final String password = "B@@kc@s3";
    private final Connection conector = DriverManager.getConnection(MySQLname,username,password);
   // private final Connection conector = DriverManager.getConnection(MySQLname,username,password);

   public SQLConnect() throws SQLException {
       //constructorul imi spune daca nu ne putem connecta
       try {
           Connection conector = DriverManager.getConnection(MySQLname,username,password);
       }
       catch ( SQLException e){
           System.out.println("Wrong path");
       }
   }

   //pentru citire se insereaza in database cand citesc din csv
   //insert birthday in database
   public void insertBirthday(DateOfBirth bday){
       String query = "insert into birthday(day,month,year) values " +
               "('" + bday.getDay() + "','" + bday.getMonth() + "','" + bday.getYear() + "');";
       try {
           Statement st = conector.createStatement();
           st.execute(query);
           st.execute("commit");
           //System.out.println("Insterted Birthday into database ... ready!");
       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
   }

   public void insertOrder(int ID,String restaurantNume, String usernameClient, String usernameLivrator, int day, int month, int year, String adresa, String oras, String numeProdus )
   {
       String query = "insert into comenzi(ID,nume_restaurant,nume_produs,username_client,username_livrator,dayUwU,monthUwU,yearUwU,adresa,oras) values (" + ID + ",'" + restaurantNume + "'" + "," + "'" + numeProdus + "'" +
               "," + "'" + usernameClient + "'" + "," + "'" + usernameLivrator + "'" + "," + day + "," + month + "," + year  + ',' + "'" + adresa + "'" + "," +
               "'" + oras + "'" + ")";
      // System.out.println(query);
       try{
           Statement st = conector.createStatement();
           st.execute(query);
           st.execute("commit");
       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

   }

   public boolean Login(String username, String pass){
       //System.out.println(username);
        boolean loginOK = false;
       String query = "select * from confidencial where username =" + "'"+ username + "'";
       try {
           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);

           while(respondData.next())
           {
               String tempUsername = respondData.getString("username");
               String tempPass = respondData.getString("pass");


               if ( pass.equals(tempPass))
                   return true;
           }



       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
        return false;
   }

   public void SignUp(String username, String pass){
       String query =  "insert into confidencial(username,pass) values (" + "'" + username + "','" + pass + "');" ;
       //System.out.println(query);
       try{
           Statement st = conector.createStatement();
           st.execute(query);
           //st.execute("commit");
           System.out.println("works");

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
   }

   public boolean searchUsername(String username){

       boolean userUnique = false;

       String query = "select count(*) from user where username=" + "'" + username + "'";
       System.out.println(query);

       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String numarCount = respondData.getString("count(*)");

               if(Integer.parseInt(numarCount) == 0)
               {
                   userUnique = true;
               }
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return  userUnique;







   }

   public void insertUsername(String username, String password){

       String query = "insert into confidencial(username,pass) values" + "(" + "'" + username + "'" + "," + "'" + password + "'" + ")";
       try{

           Statement st = conector.createStatement();
           st.execute(query);

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }


   }

   public int numarComenzi(){
       String query = "select max(ID) from comenzi";
     //  System.out.println(query);
       try{
           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String numarComanda = respondData.getString("max(ID)");

               return Integer.parseInt(numarComanda);
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return 0;
   }

   public String getOrasComanda(int id){
       String query = "select oras from comenzi where ID=" + id;
       try {

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String numeOras = respondData.getString("oras");

               return numeOras;
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
       return "none";
   }

   public String getClientComanda(int id)
   {
        String query = "select username_client from comenzi where ID=" + id;
        try {

            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            while(respondData.next())
            {
                String numeClient = respondData.getString("username_client");
                return numeClient;
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }
        return "none";
   }

   public String getLivratorComanda(int id){
       String query = "select username_livrator from comenzi where ID=" + id;
       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String numeLivrator = respondData.getString("username_livrator");
               return numeLivrator;
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
       return "none";
   }

   public Vector<String> getDateAditionaleComanda(int id){
       String query = "select dayUwU,monthUwU,yearUwU,adresa from comenzi where ID=" + id;
       Vector<String> aditionalData = new Vector<>();
       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String adresaComanda = respondData.getString("adresa");
               String dayComanda = respondData.getString("dayUwU");
               String monthComanda = respondData.getString("monthUwU");
               String yearComanda = respondData.getString("yearUwU");

               aditionalData.add(adresaComanda);
               aditionalData.add(dayComanda);
               aditionalData.add(monthComanda);
               aditionalData.add(yearComanda);

               return aditionalData;
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
       return aditionalData;
   }


   public String getNumeRestaurantComanda(int id){
        String query = "select nume_restaurant from comenzi where ID=" + id;
        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            while(respondData.next())
            {
                String numeRestaurant = respondData.getString("nume_restaurant");

                return numeRestaurant;
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }
        return "none";
   }

   public Vector<String> numarProduseComanda(int id){
       String query = "select nume_produs from comenzi where ID =" +id;
      // System.out.println(query);
       Vector<String> numeProduse = new Vector<>();
       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next())
           {
               String produsNume = respondData.getString("nume_produs");

               numeProduse.add(produsNume);
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return numeProduse;
   }

   public int getNumberOfUsers(){
       String query ="select count(*) from user";
       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           while(respondData.next()){
               String number = respondData.getString("count(*)");
               System.out.println(number);
               return Integer.parseInt(number);
           }



       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return 0;
   }

   public Vector<Vector<String>> getUsers(){

       String query = "select * from user";
       Vector<Vector<String>> finalVector = new Vector<Vector<String>>();
       //System.out.println(query);

       try{

           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);

           while(respondData.next()){


               Vector<String> tempData = new Vector<>();

               String tempNume = respondData.getString("nume");
               String tempPrenume = respondData.getString("prenume");
               String tempEmail = respondData.getString("email");
               String tempTelefon = respondData.getString("telefon");
               String tempSex = respondData.getString("sex");
               String tempDay = respondData.getString("birthday_day");
               String tempMonth = respondData.getString("birthday_month");
               String tempYear = respondData.getString("birthday_year");
               String tempUsername = respondData.getString("username");

              // System.out.println("da");

               tempData.add(tempNume);
               tempData.add(tempPrenume);
               tempData.add(tempEmail);
               tempData.add(tempTelefon);
               tempData.add(tempSex);
               tempData.add(tempDay);
               tempData.add(tempMonth);
               tempData.add(tempYear);
               tempData.add(tempUsername);


               finalVector.add(tempData);
              // System.out.println(finalVector);

           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return finalVector;

   }

   public void  searchRestaurantsCity(String oras)
   {
       String query  = "select * from restaurant where locatie =" + "'" + oras + "';";
      // System.out.println(query);

       try{
           Statement st = conector.createStatement();
           ResultSet respondData = st.executeQuery(query);
           int i = 0;
           while(respondData.next())
           {
               String tempName = respondData.getString("nume");
               String tempOras = respondData.getString("locatie");
               i++;
               System.out.println(i + " - " + tempName + " " + "(" + tempOras + ")");
           }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
   }

   public String searchRestaurantPosition(String oras, int pos)
   {
       String query  = "select * from restaurant where locatie =" + "'" + oras + "';";
      // System.out.println(query);
       try {
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            int i = 0;
            while(respondData.next())
            {
                String tempName = respondData.getString("nume");
                String tempOras = respondData.getString("locatie");
                i++;
                if( i == pos)
                {
                    return tempName;
                }
            }
       }
       catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
       return "none";
   }


   public void searchDataUser(String username)
   {
       String query = "select * from user where username =" + "'" + username + "';";
      // System.out.println(query);
       try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while( respondData.next())
            {
                String tempName = respondData.getString("nume");
                String tempPrenume = respondData.getString("prenume");
                String tempEmail = respondData.getString("email");
                String tempPhoneNumber = respondData.getString("telefon");
                String tempSex = respondData.getString("sex");
                String tempDay = respondData.getString("birthday_day");
                String tempMonth = respondData.getString("birthday_month");
                String tempYear = respondData.getString("birthday_year");

                System.out.println("Nume: " + tempName);
                System.out.println("Prenume: " + tempPrenume);
                System.out.println("eMail: " + tempEmail);
                System.out.println("Phone Number: " + tempPhoneNumber);
                System.out.println("Gender: " + tempSex);
                System.out.println("Date of Birth: " + tempDay + "/" + tempMonth + "/" + tempYear);


            }
       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }
   }

   public Vector<String> SQLSearhUserDatabaseUI(String username){
       String query = "select * from user where username=" + "'" + username + "'";
       Vector<String> returnRespond = new Vector<>();
       try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            while(respondData.next()){
                String tempName = respondData.getString("nume");
                String tempPrenume = respondData.getString("prenume");
                String tempEmail = respondData.getString("email");
                String tempPhoneNumber = respondData.getString("telefon");
                String tempSex = respondData.getString("sex");
                String tempDay = respondData.getString("birthday_day");
                String tempMonth = respondData.getString("birthday_month");
                String tempYear = respondData.getString("birthday_year");

                returnRespond.add(tempName);
                returnRespond.add(tempPrenume);
                returnRespond.add(tempEmail);
                returnRespond.add(tempPhoneNumber);
                returnRespond.add(tempSex);
                returnRespond.add(tempDay);
                returnRespond.add(tempMonth);
                returnRespond.add(tempYear);

                return returnRespond;

            }

       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL");
       }

       return returnRespond;

   }


   public void deleteBirthday(int day, int month,int year){
        String query = "delete from birthday where day = " + day + " and month = " + month + " and year = " + year + ";";
        try{
            Statement st = conector.createStatement();
            st.execute(query);
            st.execute("commit");
            System.out.println("Nu exista data de nastere" + day + "." + month + "." + year);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

   }

    public void deleteMasina(String numar_inmatriculare){
        String query = "delete from masina where numar_inmatriculare = '" + numar_inmatriculare + "';";
        try {
            Statement st = conector.createStatement();
            st.execute(query);
            st.execute("commit");
        } catch (SQLException e) {
            System.out.println("Nu exista masina cu numarul de inmatriculare " + numar_inmatriculare);
        }
    }

    public void insertMasina(Masina masina){
       String query ="insert into masina(marca,model,model_an,numar_inmatriculare) values " +
               "('" + masina.getMarca() + "','"+ masina.getModel() +"','"+ masina.getAn() +"','"+ masina.getNumarInmatriculare() + "');";
       try{
           Statement st = conector.createStatement();
           st.execute(query);
           st.execute("commit");
       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... inserare masina");
       }
    }

    //citim din csv user si il bagam in baza de date (READ)
    public void insertUser(User user){
       String query ="insert into user(nume,prenume,email,telefon,sex,birthday_day,birthday_month,birthday_year,username) values " + "('" +
               user.getNume()+"','"+ user.getPrenume() +"','"+ user.getEmail() +"','"+ user.getTelefon() +"','"+ user.getGender() +"','"+
               user.getBirthday().getDay() +"','"+ user.getBirthday().getMonth() +"','"+ user.getBirthday().getYear() +"','"+
               user.getUsername() + "');";
        //System.out.println(query);
       try {
           Statement st = conector.createStatement();
           st.execute(query);
           st.execute("commit");
       } catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... inserare user");
       }

    }

    public void deleteUser(User user){
       String query1 ="delete from user where username = '" + user.getUsername() + "';";
       String query2 = "delete from birthday where day = " + user.getBirthday().getDay() + " and month = " + user.getBirthday().getMonth() + " and year = " + user.getBirthday().getYear() + ";";
       try{
           Statement st = conector.createStatement();
           st.execute(query1);
           st.execute(query2);
           st.execute("commit ");
       }
       catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... delete user");
       }
    }


    public void insertLivrator(Livrator livrator){

       String query ="insert into livrator(nume,prenume,email,telefon,sex,birthday_day,birthday_month,birthday_year,username,masina_marca,masina_model,masina_model_an,masina_numar_inmatriculare) values " +"('"+
               livrator.getNume() +"','"+ livrator.getPrenume() +"','"+ livrator.getEmail() +"','"+ livrator.getTelefon() +"','"+ livrator.getGender() +"','"+
                livrator.getBirthday().getDay() +"','"+ livrator.getBirthday().getMonth() +"','"+ livrator.getBirthday().getYear() +"','"+
               livrator.getUsername() +"','"+ livrator.getMasina().getMarca() +"','"+ livrator.getMasina().getModel() +"','"+ livrator.getMasina().getAn() +"','"+
               livrator.getMasina().getNumarInmatriculare() + "');";
       try{
           Statement st = conector.createStatement();
           st.execute(query);
           st.execute("commit ");
       }
       catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... insert livrator");
       }
    }

    public void deleteLivrator(String username){
    String query = "delete from livrator where username = '" + username + "';";
        try{
            Statement st = conector.createStatement();
            st.execute(query);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... insert livrator");
        }
    }

    public void instertRestaurant(Restaurant restaurant){
       String query = "insert into restaurant(nume,locatie) values " + "('" + restaurant.getNume() +"','"+ restaurant.getOras() + "');";
        try{
            Statement st = conector.createStatement();
            st.execute(query);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... insert livrator");
        }
    }

    public void insertProdus(Produs produs,String temp){
       String query ="insert into produs(nume,pret,restaurant) values " +"('"+ produs.getNume() +"',"+ produs.getPret() +",'"+ temp + "');";
        try{
            Statement st = conector.createStatement();
            st.execute(query);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... insert livrator");
        }
    }

   public void auditLog(String order){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       String date = dtf.format(now);

       String query = "insert into auditlog (timestamp,operation_type) values ('" + date + "','" + order + "');";
       //System.out.println(query);

       try{
           Statement st = conector.createStatement();
           st.execute(query);
           //st.execute("insert into auditlog (timestamp,operation_type) values ('1','insert');");
           st.execute("commit");
       }
       catch (SQLException e){
           System.out.println("Eroare in executarea comenzii SQL");
       }

   }

   public void showAuditLog(){
        String query = "select * from auditlog;";
        try{
            Statement st = conector.createStatement();
            ResultSet rows = st.executeQuery(query);
            while(rows.next()){
                String row = rows.getString("timestamp") +" "+ rows.getString("operation_type");
                System.out.println(row);
            }
        }
        catch (SQLException e){
            System.out.println("Eroare in executarea comenzii SQL");
        }
   }

   public void restartProduse(){
       String query1 = "create table produs(\n" +
               "nume varchar(255) not null,\n" +
               "pret float(10) not null,\n" +
               "restaurant varchar(255) not null\n" +
               ");";
       String query2 = "drop table if exists produs;";
       try{
           Statement st = conector.createStatement();
           st.execute(query2);
           st.execute("commit ");
           st.execute(query1);
           st.execute("commit ");
       }
       catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... restart");
       }
   }

   public void restartRestaurant(){
       String query1 = "create table restaurant(\n" +
               "nume varchar(255) not null,\n" +
               "locatie varchar(255) not null\n" +
               ");";
       String query2 = "drop table if exists restaurant;";
       try{
           Statement st = conector.createStatement();
           st.execute(query2);
           st.execute("commit ");
           st.execute(query1);
           st.execute("commit ");
       }
       catch (SQLException e) {
           System.out.println("Eroare in executarea comenzii SQL ... restart");
       }
   }

    public void restartBirthday(){
        //database reseted
        String query1 = "create table birthday(\n" +
                "day varchar(255) not null,\n" +
                "month varchar(255) not null,\n" +
                "year varchar(255) not null\n" +
                ");";
        String query2 = "drop table if exists birthday;";
        try{
            Statement st = conector.createStatement();
            st.execute(query2);
            st.execute("commit ");
            st.execute(query1);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... restart");
        }
    }

    public void restartUser(){
       String query1 = "create table user(\n" +
               "nume varchar(255) not null,\n" +
               "prenume varchar(255) not null,\n" +
               "email varchar(255) not null,\n" +
               "telefon varchar(255) not null,\n" +
               "sex varchar(255) not null,\n" +
               "birthday_day varchar(255) not null,\n" +
               "birthday_month varchar(255) not null,\n" +
               "birthday_year varchar(255) not null,\n" +
               "username varchar(255) not null,\n" +
               "primary key (username)\n" +
               ");";
       String query2 = "drop table if exists user;";
        try{
            Statement st = conector.createStatement();
            st.execute(query2);
            st.execute("commit ");
            st.execute(query1);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... restart");
        }
    }

    public void restartMasina(){
       String query1 = "create table masina(\n" +
               "marca varchar(255) not null,\n" +
               "model varchar(255) not null,\n" +
               "model_an varchar(255) not null,\n" +
               "numar_inmatriculare varchar(255) not null,\n" +
               "primary key(numar_inmatriculare)\n" +
               ");";
       String query2 = "drop table if exists masina;";
        try{
            Statement st = conector.createStatement();
            st.execute(query2);
            st.execute("commit ");
            st.execute(query1);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... restart");
        }
    }

    public void restartLivrator(){
        String query1 ="create table livrator(\n" +
                "nume varchar(255) not null,\n" +
                "prenume varchar(255) not null,\n" +
                "email varchar(255) not null,\n" +
                "telefon varchar(255) not null,\n" +
                "sex varchar(255) not null,\n" +
                "birthday_day varchar(255) not null,\n" +
                "birthday_month varchar(255) not null,\n" +
                "birthday_year varchar(255) not null,\n" +
                "username varchar(255) not null,\n" +
                "masina_marca varchar(255) not null,\n" +
                "masina_model varchar(255) not null,\n" +
                "masina_model_an varchar(255) not null,\n" +
                "masina_numar_inmatriculare varchar(255) not null,\n" +
                "primary key(username)\n" +
                ");";
        String query2 = "drop table if exists livrator;";
        try{
            Statement st = conector.createStatement();
            st.execute(query2);
            st.execute("commit ");
            st.execute(query1);
            st.execute("commit ");
        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL ... restart");
        }
    }


}
