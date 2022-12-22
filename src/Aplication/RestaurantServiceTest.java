package Aplication;

import ProdusStuff.Produs;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    @Test
    void addWringRestaurant(){
        var restaurantServiceTest = new RestaurantService();
        Vector<Produs> meniu = new Vector<>();
        assertThrows(IncompatibleClassChangeError.class,() -> restaurantServiceTest.addRestaurant("Dim Sum","Cluj",meniu));
    }

}