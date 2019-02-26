package uk.ac.solent.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.util.GeoPoint;

public class MapChooseCoordinance extends AppCompatActivity implements View.OnClickListener {

    String latnum = String.valueOf(-1.4);
    String lonnum = String.valueOf(50.9080);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_choose_coordinance);

        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(this);
    }

    public void onClick(View btnGo) {
        Intent intent = new Intent();
        Bundle coordbundle = new Bundle();

        EditText lat = findViewById(R.id.etLat);
        EditText lon = findViewById(R.id.etLong);

        latnum = lat.getText().toString();
        lonnum = lon.getText().toString();

        coordbundle.putString("com.example.latnum",latnum);
        coordbundle.putString("com.example.lonnum",lonnum);
        intent.putExtras(coordbundle);
        setResult(0,intent);
        finish();
    }
}
