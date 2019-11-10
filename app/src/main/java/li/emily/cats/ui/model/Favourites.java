package li.emily.cats.ui.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Favourites {
    private static List<String> favourites = new ArrayList<String>();

    public static List<String> getFavourites() {
        return favourites;
    }

    public static void setFavourites(List<String> favourites) {
        Favourites.favourites = favourites;
    }

    public static void addFavourite(String name){
        favourites.add(name);
    }
}
