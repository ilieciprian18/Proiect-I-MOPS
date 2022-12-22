package Aplication;

import Person.DateOfBirth;
import Person.Gender;
import UserStuff.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class UserService {
    protected Vector<User> useri;

    public UserService() {
        this.useri = new Vector<>();
    }

    public void addUser(User user){
        this.useri.add(new User(user));
    }
    public void addUser(String nume, String prenume, String email, String telefon, Gender gender, DateOfBirth birthday, String username){
        this.useri.add(new User(nume,prenume,email,telefon,gender,birthday,username));
    }

    public User lastElement(){
        return useri.elementAt(useri.size()-1);
    }

    public void getBirthdayPrize(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        int okay = 0;
        for(int i=0;i<useri.size();i++)
        {
            if((useri.elementAt(i).getBirthday().getDay() == 30 ) && (useri.elementAt(i).getBirthday().getMonth() ==3 )) {
                System.out.println("La multi ani " + useri.elementAt(i).getNume() + " " + useri.elementAt(i).getPrenume() + "! Bucurati-va de voucherul de -20% la urmatoarea comanda!");
            okay =1;
            }
        }
        if ( okay == 0)
            System.out.println("Oooof ... Nu este ziua nimanui astazi ...");


    }

    @Override
    public String toString() {
        return " "+ useri + "\n";
    }
}
