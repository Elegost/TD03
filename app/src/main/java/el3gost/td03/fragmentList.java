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

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_list , container, false);

    }
}
