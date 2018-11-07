package edu.ccm.polyniakseth.shoplist;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        String [] categories = {"Sports", "Electronics", "Office Supplies"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,R.id.categories, categories));
    }

    // Switch when clicked on category
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        switch(position)

        {
            case 0:
                startActivity(new Intent(MainActivity.this, SportsActivity.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this,ElectronicActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, OfficeSuppliesActivity.class));
        }
    }}
