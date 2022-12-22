package RestaurantStuff;

import Person.Person;
import Person.DateOfBirth;
import Person.Gender;

public class Livrator extends Person {
    protected String username;
    //presupunem ca fiecare livrator are doar o masina de serviciu
    protected Masina masina;

    public Livrator(Livrator e) {
        this.username = e.username;
        this.nume=e.nume;
        this.telefon=e.telefon;
        this.prenume=e.prenume;
        this.email=e.email;
        this.gender=e.gender;
        this.setMasina(e.getMasina());
        this.setBirthdayInt(e.getBirthday().getDay(),e.getBirthday().getMonth(),e.getBirthday().getYear());
        //dateofbirth
        //masina
    }

    public Livrator() {
    }

    public Livrator(String nume, String prenume, String email, String telefon, Gender gender, int day, int month, int year, String username){
        super(nume, prenume, email, telefon, gender, day, month, year);
        this.username = username;
        this.masina = null;
    }
    public Livrator(String username, Masina masina) {
        this.username = username;
        this.masina = masina;
    }

    public Livrator(String nume, String prenume, String email, String telefon, Gender gender, DateOfBirth birthday, String username, Masina masina) {
        super(nume, prenume, email, telefon, gender, birthday);
        this.username = username;
        this.masina = masina;
    }

    public Livrator (String nume, String prenume, String email, String telefon, Gender gender,int day,int month,int year,String username,Masina masina){
        super(nume, prenume, email, telefon, gender, day, month, year);
        this.username = username;
        this.masina = masina;
    }

    public Livrator(String nume, String prenume, String email, String telefon, Gender gender, DateOfBirth birthday, String username,String marca, String model, int an, String numarInmatriculare){
        super(nume, prenume, email, telefon, gender, birthday);
        this.username = username;
        this.masina = new Masina(marca,model,an,numarInmatriculare);
    }

    public String getUsername() {
        return username;
    }

    public Masina getMasina() {
        return masina;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }



    @Override
    public String toString() {
        return
                "nume: " + nume + '\'' +
                ", prenume: " + prenume + '\'' +
                ", email: " + email + '\'' +
                ", telefon: " + telefon + '\'' +
                ", gender: " + gender +
                ", username: " + username + '\'' +
                         ", birthday" + getBirthday().getDay() + getBirthday().getMonth() + getBirthday().getYear()+
                ", masina: " + masina ;
    }
}
