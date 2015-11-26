package el3gost.td03;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
    }
}
