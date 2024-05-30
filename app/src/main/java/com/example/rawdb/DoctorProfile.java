package com.example.rawdb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DoctorProfile extends AppCompatActivity {


    User userpassed;
    MyDatabaseHelper myDB;

    TextView username, fullname, specialization,DOB;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent myIntent = getIntent();
        userpassed = (User) myIntent.getSerializableExtra("object");

        myDB = new MyDatabaseHelper(DoctorProfile.this);
        username = findViewById(R.id.proUsername);
        fullname = findViewById(R.id.proFullname);
        DOB = findViewById(R.id.proDOB);
        specialization = findViewById(R.id.proSpecialization);
        backButton = findViewById(R.id.backButton);


        storeDataInArrays();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorProfile.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();

            }


        });

    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readProfile(userpassed.getUsername());
        if(cursor.getCount() == 0){
            Toast.makeText(DoctorProfile.this, "No records Found", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                username.setText(cursor.getString(1));
                fullname.setText(cursor.getString(3));
                DOB.setText(cursor.getString(4));
                specialization.setText(cursor.getString(5));
            }
        }
    }
}