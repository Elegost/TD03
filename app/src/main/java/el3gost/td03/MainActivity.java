package el3gost.td03;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements fragmentList.OnfragmentListSelectedListener{

    private Toolbar myToolBar;
    private ListeFavoris lf;
    private String curFicheData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolBar.setEnabled(true);
        setSupportActionBar(myToolBar);

        lf = new ListeFavoris();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_ListFavoris:
                Intent intent = new Intent(this, Activity_listFavoris.class);
                intent.putStringArrayListExtra("listFavoris", lf.getListFav());
                startActivityForResult(intent, 1);
                return true;

            case R.id.action_clearList:
                lf.clearList();
                Toast.makeText(this, "Liste des favoris supprimée", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_favorite:
                if(curFicheData != null) {
                    lf.addFavorite(curFicheData);
                    Toast.makeText(this, curFicheData + " Ajouté au favori", Toast.LENGTH_LONG).show();
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onArticleSelected(String data) {
        //On cherche notre fragment
        FragmentFiche fragfiche = (FragmentFiche) getSupportFragmentManager().findFragmentById(R.id.fragment_container2);
        if (fragfiche != null) {
            // On a bien notre fragment , donc on change l ’ affichage
            curFicheData = data;
            fragfiche.chargePlanete(data);
        }else
        {
            // On est en mode ’un seul ecran ’.
            // On construit le nouveau fragment
            FragmentFiche newFragment = new FragmentFiche();

            Bundle args = new Bundle();
            curFicheData = data;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (resultCode == Activity_listFavoris.RESULT_OK) {
                Bundle args = data.getExtras();
                String res = args.getString("data");

                onArticleSelected(res);
            }
        }
    }
}


