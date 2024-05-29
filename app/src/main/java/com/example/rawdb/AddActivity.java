package com.example.rawdb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    EditText tfName, tfAge, tfGender,tfCondition;
    Button add_button;
    MainActivity main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tfName = findViewById(R.id.tfName);
        tfAge = findViewById(R.id.tfAge);
        tfGender = findViewById(R.id.tfGender);
        tfCondition = findViewById(R.id.tfCondition);
        add_button = findViewById(R.id.add_button);

        Intent myIntent = getIntent();
        User userpassed = (User) myIntent.getSerializableExtra("object");


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addPatient(tfName.getText().toString().trim(),
                        tfAge.getText().toString().trim(),
                        tfGender.getText().toString().trim(),
                        tfCondition.getText().toString().trim(),
                        userpassed.username);
                Intent intent = new Intent(AddActivity.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);

                finish();

            }


        });
    }
}
