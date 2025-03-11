package com.example.mobileweek6;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8752")));
        getSupportActionBar().setTitle("My Default Action Bar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_menu_call) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.id_menu_next) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.id_menu_settings) {
            Toast.makeText(this, "Settings Action", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void launch_second_activity(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}