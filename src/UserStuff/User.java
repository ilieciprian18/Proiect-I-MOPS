package UserStuff;

import Person.Person;
import Person.Gender;
import Person.DateOfBirth;

public class User extends Person {
    protected String username;
    //String adress


    public User(String username) {
        this.username = username;
    }

    public User(String nume, String prenume, String email, String telefon, Gender gender, DateOfBirth birthday, String username) {
        super(nume, prenume, email, telefon, gender, birthday);
        this.username = username;
    }

    public User(User e){
        this.username = e.username;
        this.nume=e.nume;
        this.telefon=e.telefon;
        this.prenume=e.prenume;
        this.email=e.email;
        this.gender=e.gender;
        this.setBirthday(e.getBirthday());
    }

    public User() {
    }




    public String getUsername() {
        return username;
    }

    /*
    public void setBirthday(DateOfBirth e){
        super.setBirthday(e);
    }
     */


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return
                "nume: " + nume + '\'' +
                ", prenume: " + prenume + '\'' +
                ", email: " + email + '\'' +
                ", telefon: " + telefon + '\'' +
                ", gender: " + gender +
                ", username: " + username + '\'' +", birthday:" + getBirthday() +'\n'
                ;
    }


}
