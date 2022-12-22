package RestaurantStuff;

import Aplication.ProdusService;
import ProdusStuff.Produs;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class Restaurant{
    //exista curieri ai aplicatiei ci nu ai restaurantului
    private static Integer NumarRestaurante = 0;
    protected String nume;
    protected String oras;
    protected Vector<Produs> meniu = new Vector<>();

    public Restaurant(String nume, String oras,Vector<Produs> meniu) {
        this.nume = nume;
        this.oras = oras;
        this.meniu = meniu;
    }

    public static Integer getNumarRestaurante() {
        return NumarRestaurante;
    }

    public String getNume() {
        return nume;
    }

    public String getOras() {
        return oras;
    }

    public Vector<Produs> getMeniu() {
        return meniu;
    }

    public static void setNumarRestaurante(Integer numarRestaurante) {
        NumarRestaurante = numarRestaurante;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public void setMeniu(Vector<Produs> meniu) {
        this.meniu = meniu;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                ", meniu=" + meniu +
                '}';
    }
}
