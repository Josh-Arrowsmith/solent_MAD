package uk.ac.solent.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;

public class POIActivity extends ListActivity {

    String[] names, details;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] { "The Crown", "The Cowherds", "The Two Brothers", "Piccolo Mondo" };
        details = new String[] { "pub, 2.5 miles north", "pub, 1.5 miles north",
                "pub, 3.5 miles northeast" , "Italian restaurant, 0.5 miles west" };
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // handle list item selection
    }


    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(POIActivity.this, android.R.layout.simple_list_item_1, names);
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
}
