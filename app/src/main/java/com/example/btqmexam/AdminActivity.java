package com.example.btqmexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    ImageView backIV_admin;
    EditText newPasswordET,re_typeNewPasswordET,newNameET;
    Button setAdminPaswordButtn;

    RecyclerView recyclerViewAdmin;
    AdminRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar Admintoolbar = findViewById(R.id.myToolbarAdmin);
        setSupportActionBar(Admintoolbar);


        backIV_admin = findViewById(R.id.backIV_admin);
        recyclerViewAdmin = findViewById(R.id.adminRecyclerView);
        recyclerViewAdmin.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<modelAdmin> options = new FirebaseRecyclerOptions.Builder<modelAdmin>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("User"),modelAdmin.class).build();

        adapter = new AdminRecyclerViewAdapter(options);
        recyclerViewAdmin.setAdapter(adapter);

        backIV_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(AdminActivity.this, "Back Admin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_admin){

            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.settings_admin){
            showLoginDialog();
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.routin_admin){
            Toast.makeText(this, "Routin", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nitice_admin){
            Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void showLoginDialog() {


        AlertDialog.Builder alert;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);


        }else {

            alert = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.reset_admin_password_dialog_layout,null);

        newNameET = view.findViewById(R.id.new_nameET);
        newPasswordET = view.findViewById(R.id.new_passwordET);
        re_typeNewPasswordET = view.findViewById(R.id.re_enter_new_password_ET);

        setAdminPaswordButtn = view.findViewById(R.id.setAdminPasswordButtnID);


        alert.setView(view);
        alert.setCancelable(true);

        setAdminPaswordButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = newNameET.getText().toString();
                String newPassword =   newPasswordET.getText().toString();
                String re_newPassword =   re_typeNewPasswordET.getText().toString();

                if (newPassword.isEmpty()){
                    newPasswordET.setError("Name is requird");
                }
                else if (re_newPassword.isEmpty()){
                    re_typeNewPasswordET.setError("Class is requird");
                }
                else if (newName.isEmpty()){
                    newNameET.setError("Name is requird");
                }
                else if (newPassword.equals(re_newPassword)){

                    HashMap<String,Object> map = new HashMap<>();
                    map.put("CurrentPassword",newPassword);
                    map.put("CurrentName",newName);

                    FirebaseDatabase.getInstance().getReference().child("AdminPassword")
                            .setValue(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    newNameET.setText("");
                                    newPasswordET.setText("");
                                    re_typeNewPasswordET.setText("");
                                    Toast.makeText(AdminActivity.this, "Successfully Changed", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminActivity.this, "Error :"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });




        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }
}