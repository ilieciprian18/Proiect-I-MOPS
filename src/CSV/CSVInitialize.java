package CSV;

import Aplication.*;
import Person.Person;
import Person.DateOfBirth;
import Person.Gender;
import RestaurantStuff.Livrator;
import RestaurantStuff.Masina;
import UserStuff.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class CSVInitialize {

    public static MasinaService ReadMasina(String filePath) throws InstantiationException, IllegalAccessException, SQLException {
        MasinaService MasiniAplicatie = new MasinaService();
        Masina masinaBuffer = Singleton.getInstance(Masina.class);
        SQLConnect SQL = new SQLConnect();
        try{
            File my_file = new File(filePath);
            Scanner scanner = new Scanner(my_file);
            //String data = scanner.nextLine();
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] info_masina = data.split(",");
                masinaBuffer.setMarca(info_masina[0]);
                masinaBuffer.setModel(info_masina[1]);
                masinaBuffer.setAn(Integer.parseInt(info_masina[2]));
                masinaBuffer.setNumarInmatriculare(info_masina[3]);
                MasiniAplicatie.addMasina(masinaBuffer);
                //add la database
                SQL.insertMasina(masinaBuffer);
            }
            scanner.close();
        }
        catch(FileNotFoundException exception){
            System.out.println("Lipseste fisierul de citire!");
        }
        return MasiniAplicatie;
    }

    public  static DateOfBirthService ReadBirthday(String filePath) throws InstantiationException, IllegalAccessException, SQLException {
        DateOfBirthService DateAplicatie = new DateOfBirthService();
        DateOfBirth dateBuffer = Singleton.getInstance(DateOfBirth.class);
        SQLConnect SQL = new SQLConnect();
        try{
            File my_file = new File(filePath);
            Scanner scanner = new Scanner(my_file);
            //String data = scanner.nextLine();
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] info_date = data.split(",");
                dateBuffer.setDay(Integer.parseInt(info_date[0]));
                dateBuffer.setMonth(Integer.parseInt(info_date[1]));
                dateBuffer.setYear(Integer.parseInt(info_date[2]));
                DateAplicatie.addDateOfBirth(dateBuffer);
                //add la database
                SQL.insertBirthday(dateBuffer);
                //DateAplicatie.addDateOfBirth(Integer.parseInt(info_date[0]),Integer.parseInt(info_date[1]),Integer.parseInt(info_date[2]));
            }

        }
        catch(FileNotFoundException exception){
            System.out.println("Lipseste fisierul de citire!");
        }
        return DateAplicatie;
    }

    public static UserService ReadUser(String filePath,DateOfBirthService dateBirthday) throws InstantiationException, IllegalAccessException, SQLException {
        UserService UseriAplicatie = new UserService();
        User userBuffer = Singleton.getInstance(User.class);
        SQLConnect SQL = new SQLConnect();
        try{
            File my_file = new File(filePath);
            Scanner scanner = new Scanner(my_file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                //System.out.println(data);
                String[] info_date = data.split(",");
                userBuffer.setNume(info_date[0]);
                //System.out.println(info_date[0]);
                userBuffer.setPrenume(info_date[1]);
                userBuffer.setEmail(info_date[2]);
                userBuffer.setTelefon(info_date[3]);
                if(info_date[4].equals("Gender.MALE")){
                    userBuffer.setGender(Gender.MALE);}
                else {userBuffer.setGender(Gender.FEMALE);}
                userBuffer.setBirthday(dateBirthday.elementAt(Integer.parseInt(info_date[5])));
                //System.out.println(userBuffer.getBirthday());
                userBuffer.setUsername(info_date[6]);
                //System.out.println(UseriAplicatie);
                UseriAplicatie.addUser(userBuffer);
                SQL.insertUser(userBuffer);
            }
        }
        catch(FileNotFoundException exception){
            System.out.println("Lipseste fisierul de citire!");

        }
        return UseriAplicatie;
    }

    public static LivratorService ReadLivrator(String filePath,MasinaService MasiniAplicatie) throws InstantiationException, IllegalAccessException, SQLException {
        LivratorService LivratoriAplicatie = new LivratorService();
        Livrator livratorBuffer = Singleton.getInstance(Livrator.class);
        SQLConnect SQL = new SQLConnect();
        try{
            File my_file = new File(filePath);
            Scanner scanner = new Scanner(my_file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] livrator_info = data.split(",");
                livratorBuffer.setNume(livrator_info[0]);
                livratorBuffer.setPrenume(livrator_info[1]);
                livratorBuffer.setEmail(livrator_info[2]);
                livratorBuffer.setTelefon(livrator_info[3]);
                if(livrator_info[4].equals("Gender.MALE")){
                    livratorBuffer.setGender(Gender.MALE);}
                else {livratorBuffer.setGender(Gender.FEMALE);}
                livratorBuffer.setBirthdayInt(Integer.parseInt(livrator_info[5]),Integer.parseInt(livrator_info[6]),Integer.parseInt(livrator_info[7]));
                livratorBuffer.setUsername(livrator_info[8]);
                //userBuffer.setBirthday(dateBirthday.elementAt(Integer.parseInt(info_date[5])));
                livratorBuffer.setMasina(MasiniAplicatie.elementAt(Integer.parseInt(livrator_info[9])));
                //System.out.println(livratorBuffer.getBirthday());
                //System.out.println(LivratoriAplicatie);
                //System.out.println(livratorBuffer.getNume());
                LivratoriAplicatie.addLivrator(livratorBuffer);
                SQL.insertLivrator(livratorBuffer);

            }
            scanner.close();

        }
        catch (FileNotFoundException exception){
            System.out.println("Lipseste fisierul de citire!");
        }
        return LivratoriAplicatie;
    }

}

