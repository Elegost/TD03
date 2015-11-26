package el3gost.td03;


import android.os.Bundle;
import android.support.v4.app.Fragment ;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class fragmentList extends Fragment {

    private ListView lv;
    private ArrayAdapter<String> listAdapter ;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        lv = (ListView) view.findViewById(R.id.lv);

        // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(planets));

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(container.getContext(), R.layout.simplerow, planetList);


        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter( listAdapter );

        return view;

    }
}
