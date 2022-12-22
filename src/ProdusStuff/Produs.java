package ProdusStuff;

import java.util.ArrayList;
import java.util.List;

public class Produs {
    protected String nume;
    protected float pret;
    private List<Ingredient> ingrediente = new ArrayList<Ingredient>();

    public Produs() {
    }

    public Produs(String nume, float pret, List<Ingredient> ingrediente) {
        this.nume = nume;
        this.pret = pret;
        this.ingrediente = ingrediente;
    }

    public String getNume() {
        return nume;
    }

    public float getPret() {
        return pret;
    }

    public List<Ingredient> getIngrediente() {
        return ingrediente;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setIngrediente(List<Ingredient> ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String afisIngrediente(){
        String listaIngrediente = "";

        int count = 0;
        for( int i = 0; i < ingrediente.size();i++)
        {
            count++;
            listaIngrediente = listaIngrediente + ingrediente.get(i).getNume() + "(" + ingrediente.get(i).getCantitate() + "g)" + " ";
            //System.out.println(ingrediente.get(i).getNume());
            if(count == 3)
            {
                listaIngrediente = listaIngrediente + '\n';
                count = 0;
            }
        }

        return listaIngrediente;
    }

    @Override
    public String toString() {
        return "Nume Produs: " + nume + ", pret: " + pret +
                ", ingrediente: " + ingrediente ;
    }

    /*
    @Override
    public int compareTo(Produs p){
        return this.getNume().compareTo(p.getNume());
    }
     */
}
