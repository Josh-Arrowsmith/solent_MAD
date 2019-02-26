package uk.ac.solent.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.util.GeoPoint;

public class MapChooseCoordinance extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_choose_coordinance);

        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(this);
    }

    public void onClick(View btnGo) {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        EditText lat = findViewById(R.id.etLat);
        EditText lon = findViewById(R.id.etLong);

        String latnum = lat.getText().toString();
        String lonnum = lon.getText().toString();

        bundle.putString("com.example.latnum",latnum);
        bundle.putString("com.example.lonnum",lonnum);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
