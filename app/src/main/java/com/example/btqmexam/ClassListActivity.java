package com.example.btqmexam;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ClassListActivity extends AppCompatActivity {

    ImageView backIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        Toolbar toolbar = findViewById(R.id.myToolbar);

        setSupportActionBar(toolbar);


        backIV = findViewById(R.id.backIV);

        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClassListActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
     
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout){

            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.about){
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.routin){
            Toast.makeText(this, "Routin", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}