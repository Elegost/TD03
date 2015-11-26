package el3gost.td03;


import android.app.FragmentManager;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements fragmentList.OnfragmentListSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) // On verifie que l ’on trouve bien notre container .
        {
            if (savedInstanceState != null) { return ;}// On verifie que c’est le premier lancement de l’activite.
            fragmentList firstFragment = new fragmentList() ;// On creer le fragment a placer.
            firstFragment.setArguments(getIntent().getExtras());// On transmet d ’ eventuels arguments .
            // On ajoute le fragment au FrameLayout ’fragment_container’.
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }

        if (findViewById(R.id.fragment_container2) != null) // On verifie que l ’on trouve bien notre container .
        {
            if (savedInstanceState != null) { return ;}// On verifie que c’est le premier lancement de l’activite.
            FragmentFiche secondFragment = new FragmentFiche() ;// On creer le fragment a placer.
            secondFragment.setArguments(getIntent().getExtras());// On transmet d ’ eventuels arguments .
            // On ajoute le fragment au FrameLayout ’fragment_container’.
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container2, secondFragment).commit();
        }

        //Determine screen size
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen", Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onArticleSelected(String data) {
        //On cherche notre fragment
        FragmentFiche fragfiche = (FragmentFiche) getSupportFragmentManager().findFragmentById(R.id.fragment_container2);
        if (fragfiche != null) {
            // On a bien notre fragment , donc on change l ’ affichage
            fragfiche.chargePlanete(data);
        }else
        {
            // On est en mode ’un seul ecran ’.
            // On construit le nouveau fragment
            FragmentFiche newFragment = new FragmentFiche();

            Bundle args = new Bundle();
            args.putString("data" , data);
            newFragment.setArguments(args);
            //Et l ’on effectue la transaction ...
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            //On realise la transaction
            transaction.commit();
        }
    }
}
