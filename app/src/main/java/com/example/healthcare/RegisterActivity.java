package com.example.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

//FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference();


public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edPassword, edEmail,edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextLTDFullname);
        edPassword = findViewById(R.id.editTextLTDPincode);
        edEmail = findViewById(R.id.editTextLTDAddress);
        edConfirm = findViewById(R.id.editTextLTDContact);
        btn = findViewById(R.id.buttonLTDBooking);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0|| password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (password.compareTo(confirm)==0){
                        if (isValid(password)){
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and Special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public static boolean isValid(String passwordhere){

            // Ensure the password has at least 4 characters
            return passwordhere.length() >= 4;


    }
}


//
//package com.example.healthcare;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    EditText edUsername, edPassword, edEmail, edConfirm;
//    Button btn;
//    TextView tv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        edUsername = findViewById(R.id.editTextLTDFullname);
//        edPassword = findViewById(R.id.editTextLTDPincode);
//        edEmail = findViewById(R.id.editTextLTDAddress);
//        edConfirm = findViewById(R.id.editTextLTDContact);
//        btn = findViewById(R.id.buttonLTDBooking);
//        tv = findViewById(R.id.textViewExistingUser);
//
//        tv.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
//
//        btn.setOnClickListener(view -> {
//            String username = edUsername.getText().toString();
//            String email = edEmail.getText().toString();
//            String password = edPassword.getText().toString();
//            String confirm = edConfirm.getText().toString();
//
//            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
//            } else {
//                if (password.equals(confirm)) {
//                    if (isValid(password)) {
//                        // Save user to Firebase
//                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//                        usersRef.child(username).setValue(new User(username, email, password))
//                                .addOnSuccessListener(aVoid -> {
//                                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                })
//                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Password must contain at least 4 characters", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public static boolean isValid(String password) {
//        // Password validation (min 4 characters)
//        return password.length() >= 4;
//    }
//}
