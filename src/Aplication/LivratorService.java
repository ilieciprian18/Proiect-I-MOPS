package Aplication;

import Person.Gender;
import RestaurantStuff.Livrator;
import RestaurantStuff.Masina;

import java.util.Random;
import java.util.Vector;

public class LivratorService {
    protected Vector<Livrator> livratori;

    public LivratorService(){
        this.livratori = new Vector<>();
    }

    public void addLivrator(String nume, String prenume, String email, String telefon, Gender gender, int day, int month, int year, String username, Masina masina){
        this.livratori.add(new Livrator(nume,prenume,email,telefon,gender,day,month,year,username,masina));
    }

    public void addLivrator(Livrator e){
        this.livratori.add(new Livrator(e));
        //this.livratori.add(new Livrator(e));
    }

    public String randomLivrator(){

        int max = livratori.size();
        int min=0;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min) + min;
        return livratori.elementAt(randomNumber).getUsername();
    }

    public Livrator lastElement(){
        return livratori.elementAt(livratori.size()-1);
    }

    @Override
    public String toString() {
        return
                 livratori + " ";
    }
}
