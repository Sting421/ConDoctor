package com.example.rawdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard_Act extends AppCompatActivity {
    ImageButton patientList;
    Button ViewerBtn;
    TextView patientCount, welcomeText;

    MyDatabaseHelper myDB;
    String doctorname = "";

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDB = new MyDatabaseHelper(Dashboard_Act.this);
        patientList = findViewById(R.id.viewPatientsBtn);
        ViewerBtn = findViewById(R.id.ViewerBtn);

        Intent myIntent = getIntent();
        User userpassed = (User) myIntent.getSerializableExtra("object");

        userpassed.testUserMethod(this);
        doctorname = userpassed.getUsername();


        patientCount =findViewById(R.id.textView17);
        patientCount.setText(myDB.patientCounter(doctorname) + " Patient Appointments");


        //userpassed.testUserMethod(this,userpassed.getUsername());
        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Hello Doctor "+userpassed.getUsername().toUpperCase());
        patientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Act.this, AddActivity.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();

            }


        });
        ViewerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Act.this, ViewPatientActivity.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();

            }


        });

    }
}