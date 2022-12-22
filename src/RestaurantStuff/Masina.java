package RestaurantStuff;

public class Masina {
    private String model;
    private String marca;
    private int an;
    private String NumarInmatriculare;

    public Masina() {
    }

    public Masina(String marca, String model, int an, String numarInmatriculare) {
        this.model = model;
        this.marca = marca;
        this.an = an;
        NumarInmatriculare = numarInmatriculare;
    }

    public Masina(Masina masina){
        this.model = masina.model;
        this.marca = masina.marca;
        this.an = masina.an;
        this.NumarInmatriculare = masina.NumarInmatriculare;
    }


    //getters
    public String getModel() {
        return model;
    }

    public String getMarca() {
        return marca;
    }

    public int getAn() {
        return an;
    }

    public String getNumarInmatriculare() {
        return NumarInmatriculare;
    }

    //setter
    public void setModel(String model) {
        this.model = model;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        NumarInmatriculare = numarInmatriculare;
    }

    @Override
    public String toString() {
        return
                "model: " + model + '\'' +
                ", marca: " + marca + '\'' +
                ", an: " + an +
                ", NumarInmatriculare: " + NumarInmatriculare + '\n' +
                ';';
    }
}
