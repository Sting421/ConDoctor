package com.example.rawdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    EditText regUsername, regPassword, regConPassword, regFullname, regOB, regSpecialization;

    ImageButton regSubmit;

    MyDatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDB = new MyDatabaseHelper(Register.this);
        initializer();

        regSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(regUsername.getText().toString().isEmpty() || regPassword.getText().toString().isEmpty() ||
                        regConPassword.getText().toString().isEmpty() || regSpecialization.getText().toString().isEmpty() ||
                        regFullname.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "All fields must be fill!", Toast.LENGTH_SHORT).show();

                }else if(!regConPassword.getText().toString().equals(regPassword.getText().toString())){
                    Toast.makeText(Register.this, "Password Don't match!", Toast.LENGTH_SHORT).show();
                } else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Register.this);
                    myDB.addUser(regUsername.getText().toString().trim(),
                            regPassword.getText().toString().trim(),
                            regFullname.getText().toString().trim(),
                            regOB.getText().toString().trim(),
                            regSpecialization.getText().toString().trim());
                    startActivity(new Intent(Register.this, Login_RegisterAct.class));
                }

            }
        });
    }

    private void initializer(){
        //register
        regUsername = findViewById(R.id.regUsername);
        regPassword = findViewById(R.id.regPassword);
        regConPassword = findViewById(R.id.regConPassword);
        regFullname = findViewById(R.id.regFullname);
        regOB = findViewById(R.id.regDOB);
        regSpecialization = findViewById(R.id.regSpecialization);

        //Buttons
        regSubmit = findViewById(R.id.regSubmitBtn);




    }



}