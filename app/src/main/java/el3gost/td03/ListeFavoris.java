package el3gost.td03;

import java.util.ArrayList;

/**
 * Created by EL3GOST on 29/11/15.
 */
public class ListeFavoris
{
    private ArrayList<String> listFav;

    public ListeFavoris()
    {
        listFav = new ArrayList<String>();
    }

    public void addFavorite(String itemToAdd)
    {
        listFav.add(itemToAdd);
    }

    public void clearList()
    {
        listFav.clear();
    }

    public void delFavorite(int position)
    {
        listFav.remove(position);
    }

    public ArrayList<String> getListFav()
    {
        return listFav;
    }

}
