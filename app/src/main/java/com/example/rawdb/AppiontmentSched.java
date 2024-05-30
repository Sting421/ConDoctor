package com.example.rawdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppiontmentSched extends AppCompatActivity {
    ImageButton backButton;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appiontment_sched);

        calendarView = findViewById(R.id.calendarView);


        // Set the calendar to go up to May 30
        long maxDate = new java.util.GregorianCalendar(2024, 4, 30).getTimeInMillis();
        calendarView.setMaxDate(maxDate);

        Intent myIntent = getIntent();
        User userpassed = (User) myIntent.getSerializableExtra("object");

        backButton = findViewById(R.id.appointBackBtn);

        // Set up the date change listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Note: month is 0-based (0 = January, 1 = February, etc.)
                if (year == 2024 && month == 4) { // May
                    switch (dayOfMonth) {
                        case 2:

                            showToast("Checkup with patient #1");
                            break;
                        case 7:
                            showToast("Brain operation for patient #2");
                            break;
                        case 10:
                            showToast("Doctors meeting in Fuente");
                            break;
                        default:
                            showToast("No events for this day");
                            break;
                    }
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppiontmentSched.this, Dashboard_Act.class);
                intent.putExtra("object", userpassed);
                startActivity(intent);
                finish();
            }


        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}