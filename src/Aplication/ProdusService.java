package Aplication;

import ProdusStuff.Ingredient;
import ProdusStuff.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProdusService {
    protected Vector<Produs> produse;

    public ProdusService(){
        this.produse = new Vector<>();
    }

    public void addProdus(String nume, Float pret, String sir1,String sir2){
        List<Ingredient> ingrediente = new ArrayList<Ingredient>();
        String[] list = sir1.split(",");
        String[] lista = sir2.split(",");
        for(int  i = 0;i< list.length;i++) {
            Ingredient aux = new Ingredient();
            aux.setNume(list[i]);
            int num = Integer.parseInt(lista[i]);
            aux.setProducator(num);
            ingrediente.add(aux);
        }

        this.produse.add(new Produs(nume,pret,ingrediente));


    }

    public Vector<Produs> getProduse() {
        return produse;
    }

    public int findProdusId(String numeProdus, String oras, String nume){
        for( int i=0; i <= produse.size();i++)
        {
            if(produse.elementAt(i).getNume().equals(numeProdus))
            {
                return i;
            }
        }
        return 0;

    }

    public Produs elementAt(int id){
        return produse.elementAt(id);
    }

    @Override
    public String toString() {
        return "produse=" + produse ;
    }
}
