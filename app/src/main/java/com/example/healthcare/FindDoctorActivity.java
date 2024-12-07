package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.SearchView;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class FindDoctorActivity extends AppCompatActivity {

    private String[][] doctors = new DoctorDetailsActivity().doctors; // Access the doctors array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        // Initialize SearchView
        SearchView searchView = findViewById(R.id.searchView);

        // Set up SearchView functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // When the user presses the "Enter" key, perform the search
                for (int i = 0; i < doctors.length; i++) {
                    String doctorName = doctors[i][0];
                    // Start the search from the 14th character onward
                    if (doctorName.length() > 14 && doctorName.substring(14).toLowerCase().contains(query.toLowerCase())) {
                        // When a match is found, show the doctor details in a popup dialog
                        showDoctorDetails(i);
                        return true;  // Stop further searching once a match is found
                    }
                }
                // If no match is found, show a Toast message to inform the user
                Toast.makeText(FindDoctorActivity.this, "No doctor found!", Toast.LENGTH_SHORT).show();
                searchView.setQuery("", false);
                return true;  // Return true to indicate the query is handled
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Do nothing when the user types, since the search only triggers on "Enter"
                return false;
            }
        });

        // Set up navigation for cards
        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(view -> startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class)));

        CardView familyPhysician = findViewById(R.id.cardFDFamilyPhysician);
        familyPhysician.setOnClickListener(view -> navigateToDoctorDetails("Family Physician"));

        CardView dietician = findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(view -> navigateToDoctorDetails("Dietician"));

        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(view -> navigateToDoctorDetails("Dentist"));

        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(view -> navigateToDoctorDetails("Surgeon"));

        CardView cardiologist = findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(view -> navigateToDoctorDetails("Cardiologist"));
    }

    // Helper method to show doctor details in a popup dialog
    private void showDoctorDetails(int doctorIndex) {
        // Prepare the doctor details to be displayed in the dialog
        String doctorDetails =  doctors[doctorIndex][0] + "\n" +
                doctors[doctorIndex][1] + "\n" +
                doctors[doctorIndex][2] + "\n" +
                doctors[doctorIndex][3] + "\n" +
                doctors[doctorIndex][4];

        // Create an AlertDialog to show the doctor details
        new AlertDialog.Builder(FindDoctorActivity.this)
                .setTitle("Doctor Details")
                .setMessage(doctorDetails)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();  // Dismiss the dialog when the user clicks OK
                    }
                })
                .create()
                .show();
    }

    // Helper method for navigation
    private void navigateToDoctorDetails(String title) {
        Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
