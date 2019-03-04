package uk.ac.solent.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;

public class MapChooseActivity extends ListActivity
{

    String[] names, details;

    boolean regularmap = false;
    boolean hikebikemap = false;
    Intent intent = new Intent();
    Bundle maptypebundle = new Bundle();


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] {"Regular Mode", "Hike/Bike Mode"};
        details = new String[] {"Displays roads and building outlines", "Displays regular mode items along with cycle/walking paths too"};
        MapChooseActivity.MyAdapter adapter = new MapChooseActivity.MyAdapter();
        setListAdapter(adapter);
    }



    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        if(index == 0) {
            regularmap = true;
            hikebikemap = false;
        }
        if(index == 1) {
            regularmap = false;
            hikebikemap = true;
        }

        maptypebundle.putBoolean("com.example.regularmap",regularmap);
        maptypebundle.putBoolean("com.example.hikebikemap",hikebikemap);
        intent.putExtras(maptypebundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(MapChooseActivity.this, android.R.layout.simple_list_item_1, names);
        }


        public View getView(int index, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if(view==null)
            {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.poi_activity, parent, false);
            }
            TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                    (TextView)view.findViewById(R.id.poi_desc);
            title.setText(names[index]);
            detail.setText(details[index]);
            return view;
        }
    }

    public void onPause() {
        super.onPause();
        mapTypeToBundle();
    }

    public void onStop() {
        super.onStop();
        mapTypeToBundle();
    }

    public void onDestroy() {
        super.onDestroy();
        mapTypeToBundle();
    }

    public void mapTypeToBundle() {
        maptypebundle.putBoolean("com.example.regularmap",regularmap);
        maptypebundle.putBoolean("com.example.hikebikemap",hikebikemap);
        intent.putExtras(maptypebundle);
        setResult(0,intent);
        finish();
    }
}
