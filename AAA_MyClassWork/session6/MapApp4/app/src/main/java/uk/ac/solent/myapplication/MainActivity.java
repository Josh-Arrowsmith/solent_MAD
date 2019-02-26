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
    boolean setCheck;

    String savedCOORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) //when app is first created
    {
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

        setContentView(R.layout.activity_main);

        mv = findViewById(R.id.mpMain);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(zl);
        mv.getController().setCenter(new GeoPoint(lat,lon));
        mv.setMultiTouchControls(true);
    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        mv.getController().setZoom(zl);
//        mv.getController().setCenter(new GeoPoint(lat,lon));
//    }

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
        if(item.getItemId() == R.id.poi)
        {
            Intent intent = new Intent(this,POIActivity.class);
            startActivityForResult(intent,3);
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
                boolean regularmap = extras.getBoolean("com.example.regularmap");
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");

                if(regularmap == true)
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
                if(hikebikemap == true)
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
                setCheck = true;
                Bundle extras=intent.getExtras();
                lat = extras.getDouble("com.example.latnum");
                lon = extras.getDouble("com.example.lonnum");

                mv.getController().setCenter(new GeoPoint(lat, lon));
            }
        }
        if(requestCode==3)
        {
            if (resultCode==RESULT_OK)
            {

            }
        }
    }

    public void onClick(View v) {

    }

    public void onStop() //when app is moved to background
    {
        super.onStop();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString ("lat", Double.toString(mv.getMapCenter().getLatitude()));
        editor.putString ("lon", Double.toString(mv.getMapCenter().getLongitude()));
        editor.putString ("zl", Integer.toString(mv.getZoomLevel()));
        editor.commit();
    }

    public void onStart() //when app comes back from background
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lat = Double.parseDouble(prefs.getString("lat", "50.9080"));
        lon = Double.parseDouble(prefs.getString("lon", "-1.4"));
        zl = Integer.parseInt(prefs.getString("zl", "16"));
        mv.getController().setCenter(new GeoPoint(lat, lon));
        mv.getController().setZoom(zl);
    }

    public void onPause() //when activity goes to background
    {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString ("lat", Double.toString(mv.getMapCenter().getLatitude()));
        editor.putString ("lon", Double.toString(mv.getMapCenter().getLongitude()));
        editor.putString ("zl", Integer.toString(mv.getZoomLevel()));
        editor.commit();
    }

    public void onResume() //when activity comes to foreground
    {
        super.onResume();
        if (setCheck == false) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            lat = Double.parseDouble(prefs.getString("lat", "50.9080"));
            lon = Double.parseDouble(prefs.getString("lon", "-1.4"));
            zl = Integer.parseInt(prefs.getString("zl", "16"));
            mv.getController().setCenter(new GeoPoint(lat, lon));
            mv.getController().setZoom(zl);
            setCheck = false;
        } else {
            setCheck = false;
        }
    }

    public void onDestroy() //when app is killed
    {
        super.onDestroy();
    }

    public void onSaveInstanceState (Bundle savedInstanceState)
    {
        savedInstanceState.putString("lat", Double.toString(mv.getMapCenter().getLatitude()));
        savedInstanceState.putString("lon", Double.toString(mv.getMapCenter().getLongitude()));
        savedInstanceState.putString("zl", Integer.toString(mv.getZoomLevel()));
        super.onSaveInstanceState(savedInstanceState);
    }
}