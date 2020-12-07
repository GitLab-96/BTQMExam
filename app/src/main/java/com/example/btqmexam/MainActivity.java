package com.example.btqmexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    Button LoginButtn,registerBtn,teacherLoginButton,adminLoginButtn,registerConfirmBtn,getPasswordButtn;

    EditText userName,Password;
    EditText forgetUserName,forgetSecreteCode;

    EditText registerUserName,registerBranch,registerClass,registerSection,registerMobileNo,registerPassword;
    TextView forgetPasswrdTV,setForgetPasswordTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginButtn =findViewById(R.id.admin_login_id_Bttn);
        registerBtn  = findViewById(R.id.register_id_Bttn);
        forgetPasswrdTV = findViewById(R.id.forgateid_Bttn);
        forgetPasswrdTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgetDialog();
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showRegisterDialog();

            }
        });


        LoginButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoginDialog();

            }
        });
    }

    private void showForgetDialog() {


        AlertDialog.Builder alert;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);


        }else {

            alert = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.forget_dialog_layout,null);

        forgetUserName = view.findViewById(R.id.forgetName);
        forgetSecreteCode = view.findViewById(R.id.forgetPasswordET);
        setForgetPasswordTV = view.findViewById(R.id.setPasswordTV);
        getPasswordButtn = view.findViewById(R.id.getPasswordButtn);


        alert.setView(view);
        alert.setCancelable(true);

        getPasswordButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Forget", Toast.LENGTH_SHORT).show();
            }
        });




        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }




    private void showRegisterDialog() {

        AlertDialog.Builder alert;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);


        }else {

            alert = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.register_dialog_layout,null);


        registerUserName = view.findViewById(R.id.nameET);
        registerBranch = view.findViewById(R.id.branchET);
        registerClass = view.findViewById(R.id.classET);
        registerSection = view.findViewById(R.id.sectionET);
        registerMobileNo = view.findViewById(R.id.mobieNoET);
        registerPassword = view.findViewById(R.id.regPasswordET);
        registerBtn = view.findViewById(R.id.registerButtn);



        alert.setView(view);
        alert.setCancelable(true);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name =   registerUserName.getText().toString();
                String classs =   registerClass.getText().toString();
                String section =   registerSection.getText().toString();
                String mobileNo =   registerMobileNo.getText().toString();
                String branch =   registerBranch.getText().toString();
                String password =   registerPassword.getText().toString();

                if (name.isEmpty()){
                    registerUserName.setError("Name is requird");
                }
                else if (classs.isEmpty()){
                    registerClass.setError("Class is requird");
                }
                else if (section.isEmpty()){
                    registerSection.setError("Section is requird");
                }
                else if (mobileNo.isEmpty()){
                    registerMobileNo.setError("Phone no is requird");
                }
                else if (branch.isEmpty()){
                    registerBranch.setError("Branch is requird");
                }
                else if (password.isEmpty()){
                    registerPassword.setError("Password is requird");
                }else {

                    HashMap<String,Object> map = new HashMap<>();
                    map.put("Name",name);
                    map.put("Classs",classs);
                    map.put("Section",section);
                    map.put("MobileNo",mobileNo);
                    map.put("Branch",branch);
                    map.put("Password",password);

                    FirebaseDatabase.getInstance().getReference().child("User").push()
                            .setValue(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    registerUserName.setText("");
                                    registerClass.setText("");
                                    registerUserName.setText("");
                                    registerBranch.setText("");
                                    registerSection.setText("");
                                    registerPassword.setText("");
                                    registerMobileNo.setText("");
                                    Toast.makeText(MainActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error :"+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(MainActivity.this,ClassListActivity.class);
                    startActivity(intent);


                }



            }
        });





        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();



    }

    private void showLoginDialog() {

        AlertDialog.Builder alert;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);


        }else {

            alert = new AlertDialog.Builder(this);
        }
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout,null);


        userName = view.findViewById(R.id.IDET);
        Password = view.findViewById(R.id.PasswordET);
        adminLoginButtn = view.findViewById(R.id.loginButtnAdmin);
        teacherLoginButton = view.findViewById(R.id.loginButtnTeacher);


        alert.setView(view);
        alert.setCancelable(true);

        adminLoginButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "ADMIN", Toast.LENGTH_SHORT).show();
            }
        });

        teacherLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ClassListActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "TECAHER", Toast.LENGTH_SHORT).show();
            }
        });



        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }
}