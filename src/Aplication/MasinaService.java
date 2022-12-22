package Aplication;

import RestaurantStuff.Masina;

import java.util.Vector;

public class MasinaService {
    protected Vector<Masina> masini;

    public MasinaService(){
        this.masini = new Vector<>();
    }

    public void addMasina(String marca, String model, int an, String numarInmatriculare){
        this.masini.add(new Masina(marca,model,an,numarInmatriculare));

    }

    public void  addMasina(Masina e){
        this.masini.add(new Masina(e));
    }

    public Masina elementAt(int id){
        return masini.elementAt(id);
    }

    public Masina lastElement(){
        return masini.elementAt(masini.size()-1);
    }

    public void removeMasina(String NumarInmatriculare){
        for(int i=0;i < masini.size();i++)
        {
            if(masini.elementAt(i).getNumarInmatriculare().equals(NumarInmatriculare))
                masini.removeElementAt(i);
        }
    }
    @Override
    public String toString() {
        return masini + " ";

    }
}
