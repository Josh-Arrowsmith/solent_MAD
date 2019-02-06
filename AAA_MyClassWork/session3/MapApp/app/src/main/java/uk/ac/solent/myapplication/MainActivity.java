package uk.ac.solent.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(this);

        mv = findViewById(R.id.mpMain);


        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
    }

    @Override
    public void onClick(View v) {
        EditText lat = findViewById(R.id.etLat);
        EditText lon = findViewById(R.id.etLong);

        mv.getController().setCenter(new GeoPoint(Double.parseDouble(lat.getText().toString()),Double.parseDouble(lon.getText().toString())));
    }
}
