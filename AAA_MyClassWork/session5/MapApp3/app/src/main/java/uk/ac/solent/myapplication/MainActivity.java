package uk.ac.solent.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;

    double lat;
    double lon;
    int zl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        lat = 50.9080;
        lon = -1.4;
        zl = 16;

        if (savedInstanceState != null)
        {
            lat = Double.parseDouble ( savedInstanceState.getString ("lat", "50.9080"));
            lon = Double.parseDouble ( savedInstanceState.getString ("lon", "-1.4"));
            zl = Integer.parseInt ( savedInstanceState.getString ("zl", "16") );
        }

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//        lat = Double.parseDouble ( prefs.getString("lat", "50.9080") );
//        lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
//        zl = Integer.parseInt ( prefs.getString("zl", "16") );

        setContentView(R.layout.activity_main);

        mv = findViewById(R.id.mpMain);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(zl);
        mv.getController().setCenter(new GeoPoint(lat,lon));
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }
        if(item.getItemId() == R.id.choosecoord)
        {
            Intent intent = new Intent(this,MapChooseCoordinance.class);
            startActivityForResult(intent,1);
            return true;
        }
        if(item.getItemId() == R.id.appprefs)
        {
            Intent intent = new Intent(this,MyPrefsActivity.class);
            startActivityForResult(intent,2);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }

        if(requestCode==1)
        {
            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                lat = extras.getDouble("com.example.latnum");
                lon = extras.getDouble("com.example.lonnum");

                mv.getController().setCenter(new GeoPoint(lat, lon));
            }
        }    }

    public void onClick(View v) {

    }


    public void onResume()
    {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lat = Double.parseDouble ( prefs.getString("lat", "50.9080") );
        lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        zl = Integer.parseInt ( prefs.getString("zl", "16") );
        mv.getController().setCenter(new GeoPoint(lat, lon));
        mv.getController().setZoom(zl);
    }


    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString ("lat", Double.toString(lat));
        editor.putString ("lon", Double.toString(lon));
        editor.putString ("zl", Integer.toString(zl));
        editor.commit();
    }



    public void onSaveInstanceState (Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("lat", Double.toString(lat));
        savedInstanceState.putString("lon", Double.toString(lon));
        savedInstanceState.putString("zl", Integer.toString(zl));
    }

}