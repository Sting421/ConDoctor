package com.example.rawdb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewPatientActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton backBtn;
    ImageView empty_imageview;
    TextView no_data;
    int curCount = 0;

    MyDatabaseHelper myDB;
    ArrayList<String> patient_id, patient_name, patient_age, patient_gender,patient_condition;
    CustomAdapter customAdapter;
    User userpassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        backBtn = findViewById(R.id.backBtn);
        Intent myIntent = getIntent();
         userpassed = (User) myIntent.getSerializableExtra("object");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(ViewPatientActivity.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
             //   userpassed.testUserMethod(ViewPatientActivity.this, userpassed.username);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(ViewPatientActivity.this);
        patient_id = new ArrayList<>();
        patient_name = new ArrayList<>();
        patient_age = new ArrayList<>();
        patient_gender = new ArrayList<>();
        patient_condition = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ViewPatientActivity.this,this, patient_id, patient_name, patient_age,
                patient_gender,patient_condition);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewPatientActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData(userpassed.getUsername());
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            cursor.move(curCount);
            while (cursor.moveToNext()){
                patient_id.add(cursor.getString(0));
                patient_name.add(cursor.getString(1));
                patient_age.add(cursor.getString(2));
                patient_gender.add(cursor.getString(3));
                patient_condition.add(cursor.getString(4));
                curCount++;
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ViewPatientActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ViewPatientActivity.this, ViewPatientActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}