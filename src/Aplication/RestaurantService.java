package Aplication;

import ProdusStuff.Produs;
import RestaurantStuff.Restaurant;
import com.sun.jdi.event.VMDeathEvent;

import java.sql.SQLException;
import java.util.Vector;

public class RestaurantService {
   public Vector<Restaurant> restaurante;

    public RestaurantService(){
        this.restaurante = new Vector<>();
    }


    public void addRestaurant(String nume, String oras,Vector<Produs> meniu){
        this.restaurante.add(new Restaurant(nume,oras,meniu));

    }

    public Restaurant elementAt(int id){
        return restaurante.elementAt(id);
    }

    public void NumarRestauranteOras(String nume){
        int nr=0;
        int okay=0;
        for(int i=0;i<restaurante.size();i++)
        {
            if(restaurante.elementAt(i).getOras().equals(nume)) {
                nr++;
                okay = 1;
            }
        }
        if( okay == 1){
        System.out.println("In orasul " + nume + " exista " + nr + " restaurante " );}
        else {
            System.out.println("Nu exista restaurante in orasul " + nume);
        }
    }

    public void CautaRestaurant(String nume){
        int okay=0;
        for(int i=0;i<restaurante.size();i++)
        {
            if(restaurante.elementAt(i).getNume().equals(nume)) {
                okay = 1;
                System.out.println("A fost gasit restaurantul " + nume + " in orasul "+ restaurante.elementAt(i).getOras());

            }

        }
        if(okay == 0)
            System.out.println("Nu a fost gasit restaurantul ... ");
    }

    public void CautaRestaurantOras(String nume,String NumeOras){
        int okay = 0;
        for(int i = 0;i < restaurante.size();i++)
        {
            if((restaurante.elementAt(i).getNume().equals(nume)) && (restaurante.elementAt(i).getOras().equals(NumeOras))) {
                okay = 1;
                System.out.println("A fost gasit restaurantul " + nume + " in orasul " + NumeOras);
                break;
            }
        }
        if (okay == 0 )
            System.out.println("Nu a fost gasit restaurantul ...");
    }

    public int CautaRestaurantOrasID(String nume,String NumeOras){
        int okay = 0;
        for(int i = 0;i < restaurante.size();i++)
        {
            if((restaurante.elementAt(i).getNume().equals(nume)) && (restaurante.elementAt(i).getOras().equals(NumeOras))) {
                okay = 1;
                return i;

            }
        }

        return 0;
    }


    public void AfisRestaurante(){
        for(int i=0;i<restaurante.size();i++)
            System.out.println((i+1) + ": "+ restaurante.elementAt(i).getNume() + " ," + restaurante.elementAt(i).getOras() );
    }
    @Override
    public String toString() {
        return "RestaurantService{" +
                "restaurante=" + restaurante +
                '}';
    }

    public void instertMeniuDatabase() throws SQLException {
        SQLConnect SQL = new SQLConnect();
        for(int i =0;i<restaurante.size();i++){
            for(int j =0;j<restaurante.elementAt(i).getMeniu().size();j++)
                SQL.insertProdus(restaurante.elementAt(i).getMeniu().elementAt(j),restaurante.elementAt(i).getNume());

        }

    }
}
