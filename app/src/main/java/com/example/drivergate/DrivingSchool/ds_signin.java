package com.example.drivergate.DrivingSchool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.drivergate.R;
import com.example.drivergate.reset_password;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ds_signin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference drivingSchool;
    private EditText DS_Username,DS_password;
    private Button DS_signin;
    private  String DS_Id;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_signin);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        drivingSchool = database.getReference("Ds_schools");
        progressBar = findViewById(R.id.progressBar);

        DS_signin = findViewById(R.id.drive_signin);  //buttons
        DS_Username = findViewById(R.id.ds_username);
        DS_password = findViewById(R.id.drive_password);

        DS_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //button event click listener
                String uname = DS_Username.getText().toString().trim();
                String pass = DS_password.getText().toString().trim();

                if(uname.equals("")||pass.equals("")){
                    Toast.makeText(ds_signin.this, "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }
                else{
                    progressBar.bringToFront();
                    progressBar.setVisibility(View.VISIBLE);
                    userLogin();
                }
            }
        });
    }

    private void userLogin(){
        mAuth.signInWithEmailAndPassword(DS_Username.getText().toString().trim(), DS_password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), ds_dashboard.class));
                    finish();
                } else {
                    Toast.makeText(ds_signin.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void goToReset(View view) {
        Intent intent = new Intent(ds_signin.this, reset_password.class);
        startActivity(intent);
    }

    public void DS_register(View view) {
        Intent intent = new Intent(ds_signin.this, ds_register.class);
        startActivity(intent);
    }
}