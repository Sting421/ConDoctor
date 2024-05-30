package com.example.rawdb;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AppiontmentSched extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);

        // Set the calendar to go up to May 30
        long maxDate = new java.util.GregorianCalendar(2024, 4, 30).getTimeInMillis();
        calendarView.setMaxDate(maxDate);

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
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}