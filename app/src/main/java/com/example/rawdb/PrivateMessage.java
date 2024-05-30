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

public class PrivateMessage extends AppCompatActivity {
    ImageButton sendButton, backButton;
    EditText doctorName,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_private_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        doctorName = findViewById(R.id.privateSendTo);
        message = findViewById(R.id.privateMessageInput);

        sendButton = findViewById(R.id.privateSend);
        backButton = findViewById(R.id.privateBack);
        Intent myIntent = getIntent();
        User userpassed = (User) myIntent.getSerializableExtra("object");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrivateMessage.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();
            }


        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(PrivateMessage.this);

                if(myDB.userChecker(doctorName.getText().toString())){
                    Intent intent = new Intent(PrivateMessage.this, Dashboard_Act.class);
                    intent.putExtra("object", userpassed);

                    Toast.makeText(PrivateMessage.this, "Private Sent Successfully", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    startActivity(intent);
                    finish();
                }

                else{
                    Toast.makeText(PrivateMessage.this, "No doctor Found", Toast.LENGTH_SHORT).show();
                }
            }


        });



    }
}