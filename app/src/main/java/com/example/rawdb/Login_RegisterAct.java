package com.example.rawdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class Login_RegisterAct extends AppCompatActivity {


    FloatingActionButton registerButton, loginButton;
    EditText username, password;

    MyDatabaseHelper myDB;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         myDB = new MyDatabaseHelper(Login_RegisterAct.this);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

        registerButton = findViewById(R.id.registerBtn);
        loginButton = findViewById(R.id.loginBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Login_RegisterAct.this);
                myDB.addUser(username.getText().toString().trim(),
                        password.getText().toString().trim());


            }


        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //with db
                if(String.valueOf(username.getText()).isEmpty() && String.valueOf(username.getText()).isEmpty()){
                    Toast.makeText(Login_RegisterAct.this, "All Fields must be fill!", Toast.LENGTH_SHORT).show();

                }else{

                    if(myDB.userChecker(String.valueOf(username.getText()), String.valueOf(password.getText()))){
                        user.username = String.valueOf(username.getText());
                        Intent intent = new Intent(Login_RegisterAct.this,Dashboard_Act.class);
                        intent.putExtra("username_key",username.getText());
                        intent.putExtra("object", (Serializable) user);
                        //Toast.makeText(Login_RegisterAct.this, username.getText(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                         finish();
                    }
                    else{
                        Toast.makeText(Login_RegisterAct.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}