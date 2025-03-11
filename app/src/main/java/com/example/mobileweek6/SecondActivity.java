package com.example.mobileweek6;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> countries;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(findViewById(R.id.my_toolbar)); // Used to set custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adds a back button, parent (where we go back to) defined in manifest

        listView = findViewById(R.id.lv_countries);
        listView.setNestedScrollingEnabled(true);
        String[] ar = getResources().getStringArray(R.array.countries);
        countries = new ArrayList<>(Arrays.asList(ar));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchitem = menu.findItem(R.id.mi_search);
        SearchView searchview = (SearchView) searchitem.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter_countries(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter_countries(newText);
                return false;
            }
        });

        return true;
    }

    private void filter_countries(String prefix) {
        Toast.makeText(this.getApplicationContext(), prefix, Toast.LENGTH_SHORT).show();
        List<String> filtered_countries;
        filtered_countries = countries.stream().filter(s ->s.toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filtered_countries);
        listView.setAdapter(adapter);
    }
}