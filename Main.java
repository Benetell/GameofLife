import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    /*
    Létrehozza a "mx" kétdimenziós cell típusú ArrayListet, majd létrehoz egy új GameFrame-et a mx paraméterrel, amit amin egyből el is indít egy szálat
     */
    public static void  main(String[] args) {
        ArrayList<ArrayList<cell>> mx = new ArrayList<>();
        new GameFrame(mx).start();


    }

}

