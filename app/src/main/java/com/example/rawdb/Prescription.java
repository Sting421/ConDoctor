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

public class Prescription extends AppCompatActivity {

    ImageButton backButton, sendButton;
    EditText medicineName,patientName,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prescription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backButton = findViewById(R.id.preBackBtn);
        sendButton = findViewById(R.id.preSendBtn);

        medicineName = findViewById(R.id.presMedicine);
        patientName = findViewById(R.id.presPatientName);
        description = findViewById(R.id.presDescription);

        Intent myIntent = getIntent();
        User userpassed = (User) myIntent.getSerializableExtra("object");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Prescription.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();
            }


        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Prescription.this);


               if(myDB.patientChecker(patientName.getText().toString(), userpassed.getUsername())){
                  Intent intent = new Intent(Prescription.this, Dashboard_Act.class);
                   intent.putExtra("object", userpassed);

                   Toast.makeText(Prescription.this, "Prescription Sent Successfully", Toast.LENGTH_SHORT).show();
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   startActivity(intent);
                   finish();
               }

               else{
                   Toast.makeText(Prescription.this, "No patient Found", Toast.LENGTH_SHORT).show();
               }
            }


        });

    }
}