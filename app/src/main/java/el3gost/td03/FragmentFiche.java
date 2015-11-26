package el3gost.td03;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentFiche extends Fragment
{
    ImageView img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //img = (ImageView) container.findViewById(R.id.imageView);
        //img.setImageResource(R.drawable.icone);
        return inflater.inflate(R.layout.fragment_fiche, container, false);
    }
}
