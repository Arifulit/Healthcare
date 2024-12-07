package com.example.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.util.Calendar;


public class BookAppointmentActivity extends AppCompatActivity {

    // UI Elements
    private EditText edFullname, edAddress, edContact, edPincode;
    private TextView tvTitle;
    private Button dateButton, timeButton, btnBook, btnBack;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        // Initialize UI elements
        tvTitle = findViewById(R.id.textViewAppTitle);
        edFullname = findViewById(R.id.editTextAppFullname);
        edAddress = findViewById(R.id.editTextAppAddress);
        edContact = findViewById(R.id.editTextAppContact);
        edPincode = findViewById(R.id.editTextAppPincode);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        // Disable editing for certain fields
        edFullname.setKeyListener(null);
        edAddress.setKeyListener(null);
        edContact.setKeyListener(null);
        edPincode.setKeyListener(null);

        // Get intent data
        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullname = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String fees = intent.getStringExtra("text5");

        // Set data to UI elements
        tvTitle.setText(title);
        edFullname.setText(fullname);
        edAddress.setText(address);
        edContact.setText(contact);
        edPincode.setText("Cons Fees " + fees + "/-");

        // Initialize date and time pickers
        initDatePicker();
        initTimePicker();

        // Set listeners
        dateButton.setOnClickListener(view -> datePickerDialog.show());
        timeButton.setOnClickListener(view -> timePickerDialog.show());
        btnBack.setOnClickListener(view -> startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class)));

        // Book Appointment
        btnBook.setOnClickListener(view -> bookAppointment(title, fullname, address, contact, fees));
    }

    // Initialize Date Picker
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1; // Month is zero-based
            dateButton.setText(day + "/" + month + "/" + year);
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000); // Set minimum date to tomorrow
    }

    // Initialize Time Picker
    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hour, minute) ->
                timeButton.setText(String.format("%02d:%02d", hour, minute));

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hour, minute, true);
    }

    // Book Appointment Logic (No Database or SharedPreferences Check)
    private void bookAppointment(String title, String fullname, String address, String contact, String fees) {
        // Create an AlertDialog to confirm appointment booking
        AlertDialog.Builder builder = new AlertDialog.Builder(BookAppointmentActivity.this);
        builder.setCancelable(false);

        // Always book the appointment and show a success message
        String date = dateButton.getText().toString();
        String time = timeButton.getText().toString();
        String message = "Appointment Successful!! \nDate: " + date + "\nTime: " + time;

        // Set the message and the positive button in the dialog
        builder.setTitle("Appointment Booked")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                });

        // Create and show the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Show Toast message with Date and Time
        Toast.makeText(BookAppointmentActivity.this, "Appointment Successful\nDate: " + date + "\nTime: " + time, Toast.LENGTH_LONG).show();
    }
}
