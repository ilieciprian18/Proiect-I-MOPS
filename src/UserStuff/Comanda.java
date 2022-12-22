package UserStuff;

import Person.DateOfBirth;
import ProdusStuff.Produs;

import java.util.Vector;

public class Comanda {
    //o sa le sortez dupa data sortedset
    private static int IdComanda=0;
    protected int IDComanda;
    protected DateOfBirth DataComanda;
    protected Vector<Produs> ProduseComanda = new Vector<>();
    protected String NumeRestaurant;
    protected String UsernameClient;
    protected String UsernameLivrator;
    protected String Adresa;
    public Comanda() {
    }

    public Comanda(String NumeRestaurant, Vector<Produs> comenzi,String usernameClient,String usernameLivrator, int day, int month, int year,String adresa) {
        this.DataComanda = new DateOfBirth(day,month,year);
        this.NumeRestaurant= NumeRestaurant;
        this.ProduseComanda = comenzi;
        this.UsernameClient = usernameClient;
        this.UsernameLivrator=usernameLivrator;
        this.Adresa = adresa;
        IdComanda++;
        this.IDComanda = IdComanda;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setIDComanda(int IDComanda) {
        this.IDComanda = IDComanda;
    }

    public String getUsernameLivrator() {
        return UsernameLivrator;
    }

    public String getUsernameClient() {
        return UsernameClient;
    }

    public static void setIdComanda(int idComanda) {
        IdComanda = idComanda;
    }

    public void setDataComanda(DateOfBirth dataComanda) {
        DataComanda = dataComanda;
    }

    public void setProduseComanda(Vector<Produs> produseComanda) {
        ProduseComanda = produseComanda;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        NumeRestaurant = numeRestaurant;
    }

    public static int getIdComanda() {
        return IdComanda;
    }

    public void setUsernameClient(String usernameClient) {
        UsernameClient = usernameClient;
    }

    public DateOfBirth getDataComanda() {
        return DataComanda;
    }

    public void setUsernameLivrator(String usernameLivrator) {
        UsernameLivrator = usernameLivrator;
    }

    public Vector<Produs> getProduseComanda() {
        return ProduseComanda;
    }

    public int getIDComanda() {
        return IDComanda;
    }

    public String getNumeRestaurant() {
        return NumeRestaurant;
    }

    public float getSumaProduse(){
        float suma = 0;

        for( int i=0; i<= ProduseComanda.size() ;i++)
        {
            suma = ProduseComanda.elementAt(i).getPret();
        }
        return suma;
    }


    @Override
    public String toString() {
        return "Comanda:#" +
                IDComanda +
                ", Data: " + DataComanda + '\n'+
                ", Produse: " + ProduseComanda+
                ", Restaurant: '" + NumeRestaurant + '\'' +
                ", Client: " + UsernameClient + '\'' +
                ", Livrator:" + UsernameLivrator + ", Adresa:" + Adresa + '\n'
                ;
    }
}
