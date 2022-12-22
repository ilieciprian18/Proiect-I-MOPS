package Aplication;

import CSV.CSVInitialize;
import Person.DateOfBirth;
import Person.Gender;
import RestaurantStuff.Livrator;
import RestaurantStuff.Masina;
import UserStuff.User;
import jdk.swing.interop.LightweightContentWrapper;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, SQLException {
        //un main pur si simplu de teste
        CSVInitialize.ReadMasina("/src/CSVFiles/masina.csv");
        SQLConnect test = new SQLConnect();
       // test.auditLog("create tables");
       // test.showAuditLog();
        //De implementat
        //SELECT AuditLog
        //INSERT AuditLog
        //INSERT,SELECT,DELETE,READ
        DateOfBirth birthday = new DateOfBirth(11,12,2020);
        test.insertBirthday(birthday);
        Masina masina = new Masina("audi","a8",2016,"PH22XVGGH");
        test.insertMasina(masina);
        test.deleteMasina("PH22XVX");
        User user = new User("deea","uga","gmail@zz","0726", Gender.MALE,birthday,"gxx");
        System.out.println(user.getGender());
        test.insertUser(user);
        test.deleteBirthday(11,122,11);
        //test.deleteUser(user);
        Livrator gigel = new Livrator("deea","uga","gmail@zz","0726", Gender.MALE,20,11,22,"dudu",masina);
        test.insertLivrator(gigel);

    }
}
