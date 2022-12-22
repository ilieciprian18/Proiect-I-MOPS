package Aplication;

import ProdusStuff.Produs;
import UserStuff.Comanda;

import java.util.Vector;

public class ComandaService {
    protected Vector<Comanda> comenzi;

    public ComandaService(){
        this.comenzi= new Vector<Comanda>();
    }

    public void AddComanda(String NumeRestaurant, Vector<Produs> produse,String usernameClient,String usernameLivrator, int day, int month, int year,String adresa){
        this.comenzi.add(new Comanda(NumeRestaurant,produse,usernameClient,usernameLivrator,day,month,year,adresa));
    }

    public Comanda elementAt(int id){
        return comenzi.elementAt(id);
    }

    public int numarComenzi(){
        return comenzi.size();
    }

    public void IstoricComenziUser(String username){
        int okay = 0;
        for(int i=0;i<comenzi.size();i++)
            if(comenzi.elementAt(i).getUsernameClient().equals(username)) {
            okay = 1;
                System.out.println(comenzi.elementAt(i).toString() + '\n');
            }
        if(okay == 0)
            System.out.println("Nu exista comenzi efectuate de userul " + username);
    }

    public Vector<Comanda> returnOrdersUser(String username){

        Vector<Comanda> tempComenzi = new Vector<>();
        for(int i=0; i<comenzi.size();i++)
        {
            if(comenzi.elementAt(i).getUsernameClient().equals(username)){
                tempComenzi.add(comenzi.elementAt(i));
            }
        }

        return tempComenzi;
    }

    public void IstoricComenziLivrator(String username){
        int okay=0;
        System.out.println("Comenzi livrate de " + username + ":");
        for(int i=0;i<comenzi.size();i++)
            if(comenzi.elementAt(i).getUsernameLivrator().equals(username)){
                okay=1;
                System.out.println("Comanda: #" +comenzi.elementAt(i).getIDComanda() + " Client: " + comenzi.elementAt(i).getUsernameClient() + "pret: ");
            }
        if(okay ==0)
            System.out.println("Nu exista comenzi livrate de acest livrator");
    }

    public void TotalComanda(int id){
        float sum = 0;
        for(int i=0;i<comenzi.elementAt(id).getProduseComanda().size();i++)
            sum = sum + comenzi.elementAt(id).getProduseComanda().elementAt(i).getPret();
        System.out.println("Suma este " + sum);
    }

    public float TotalComanda2(int id){
        float sum = 0;
        for(int i=0;i<comenzi.elementAt(id).getProduseComanda().size();i++)
            sum = sum + comenzi.elementAt(id).getProduseComanda().elementAt(i).getPret();
       return sum;
    }

    public void TotalComenziUser(String username){
        float sum =0;
        for(int i=0;i<comenzi.size();i++)
        {
            if(comenzi.elementAt(i).getUsernameClient().equals(username))
                for(int o=0;o<comenzi.elementAt(i).getProduseComanda().size();o++)
                    sum = sum + comenzi.elementAt(i).getProduseComanda().elementAt(o).getPret();
        }
        System.out.println("Suma este " + sum);
    }

    public void AfisSumaAplicatie(){
        float sum =0;
        for(int i=0;i<comenzi.size();i++)
        {
            for(int o=0;o<comenzi.elementAt(i).getProduseComanda().size();o++)
                sum = sum + comenzi.elementAt(i).getProduseComanda().elementAt(o).getPret();
        }
        System.out.println("Suma totala a Aplicatiei este " + sum);
    }

    public void AfisSumaRestaurant(String numes){
        float sum=0;
        for(int i=0;i<comenzi.size();i++)
        {
            if(comenzi.elementAt(i).getNumeRestaurant().equals(numes))
                for(int o=0;o<comenzi.elementAt(i).getProduseComanda().size();o++)
                sum = sum+ comenzi.elementAt(i).getProduseComanda().elementAt(o).getPret();
        }
        System.out.println("Suma totala a retsaurantului " + numes + " este " + sum );
    }

    public void AfisToateComenzi(){
        for(int i=0;i<comenzi.size();i++)
        {
            System.out.println("ID #" + comenzi.elementAt(i).getIDComanda() + ", data: " + comenzi.elementAt(i).getDataComanda() +", Restaurant " +comenzi.elementAt(i).getNumeRestaurant()+ ", Pret " + TotalComanda2(i) + '\n');
        }
    }
    @Override
    public String toString() {
        return  comenzi + " ";
    }
}
