package ApplicationUI;

import ProdusStuff.Produs;

import java.util.Vector;

public class DataSingleton {

    private static final  DataSingleton instance = new DataSingleton();

    private Vector<Produs> comandaTemp;
    private int[] numarProduseTemp;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public Vector<Produs> getComanda(){
        return comandaTemp;
    }

    public void setComanda(Vector<Produs> comanda){
        this.comandaTemp = comanda;
    }


    public int[] getNumarProduseTemp(){
        return  numarProduseTemp;
    }

    public void setNumarProduseTemp(int[] temp){
        this.numarProduseTemp = temp;
    }


}
