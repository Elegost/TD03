package el3gost.td03;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment ;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class fragmentList extends Fragment{

    private ListView lv;
    private ArrayAdapter<String> listAdapter ;

    OnfragmentListSelectedListener mCallback ;

    // L ’ Activity contenant le fragment devras implementer interface :
    public interface OnfragmentListSelectedListener {
        public void onArticleSelected(String data);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);   // Pour etre sur de la presence d ’une implementation ,
                                    // on effectue une conversion explicite vers OnHeadlineSelectedListener .
        try {
            mCallback = (OnfragmentListSelectedListener) activity ;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "␣must␣implement␣OnHeadlineSelectedListener") ;
        }
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        lv = (ListView) view.findViewById(R.id.lv);

        // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        final ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(planets));

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(container.getContext(), R.layout.simplerow, planetList);
        // Set the ArrayAdapter as the ListView's adapter.
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                String str = (String) o;//As you are using Default String Adapter
                mCallback.onArticleSelected(planetList.get(position));
            }
        });
        return view;
    }
}
