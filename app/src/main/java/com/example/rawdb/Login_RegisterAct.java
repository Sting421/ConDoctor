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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class Login_RegisterAct extends AppCompatActivity implements Serializable{


    ImageButton registerButton, loginButton;

    EditText logTFUsername, logTFPassword;

    MyDatabaseHelper myDB;
    User user;
    Login_RegisterAct loginReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDB = new MyDatabaseHelper(Login_RegisterAct.this);

        initializer(); //find view id setters

        user = new User();
        loginReg = new Login_RegisterAct();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_RegisterAct.this, Register.class));
//


            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //with db
                if(String.valueOf(logTFUsername.getText()).isEmpty() || String.valueOf(logTFPassword.getText()).isEmpty()){
                    Toast.makeText(Login_RegisterAct.this, "All Fields must be fill!", Toast.LENGTH_SHORT).show();

                }else{

                    if(myDB.userChecker(String.valueOf(logTFUsername.getText()), String.valueOf(logTFPassword.getText()))){
                        user.username = String.valueOf(logTFUsername.getText());
                        Intent intent = new Intent(Login_RegisterAct.this,Dashboard_Act.class);
                        intent.putExtra("username_key",logTFUsername.getText());
                        intent.putExtra("object", (Serializable) user);
                        //Toast.makeText(Login_RegisterAct.this, username.getText(), Toast.LENGTH_SHORT).show();
                        intent.putExtra("object", user);
                        intent.putExtra("login", loginReg);
                        //  Toast.makeText(Login_RegisterAct.this, tfUsername, Toast.LENGTH_SHORT).show();
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

    private void initializer(){
        logTFUsername = findViewById(R.id.txtUsername);
        logTFPassword = findViewById(R.id.txtPassword);

        registerButton = findViewById(R.id.registerBtn);
        loginButton = findViewById(R.id.loginBtn);


    }

}