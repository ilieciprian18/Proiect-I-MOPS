package ProdusStuff;

public class Ingredient {
    private String nume;
    private int cantitate;

    public Ingredient() {
    }

    public Ingredient(String nume, int cantitate) {
        this.nume = nume;
        this.cantitate = cantitate;
    }

    public String getNume() {
        return nume;
    }

    public int getCantitate(){
        return cantitate;
    }

    public int getProducator() {
        return cantitate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setProducator(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return nume +("(") +cantitate + ("g)");
    }
}
