package com.example.rawdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    TextView patientCount,welcomeText;

    MyDatabaseHelper myDB;


    String doctorname = "";

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
        patientList = findViewById(R.id.viewPatient);

        patientCount =findViewById(R.id.textView17);
        Intent myIntent = getIntent();
      //  patientCount.setText(myDB.patientCounter(doctorname) + " Patient Appointments");
        User userpassed = (User) myIntent.getSerializableExtra("object");

        //userpassed.testUserMethod(this,userpassed.getUsername());
        welcomeText = findViewById(R.id.editTextName);
        welcomeText.setText(userpassed.getUsername());
        patientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard_Act.this, ViewPatientActivity.class));
                finish();

            }


        });

    }
}