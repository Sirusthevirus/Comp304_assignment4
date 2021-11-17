package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "Assignment4Prefs" ;
    public static final String NurseKey = "nurseID";
    SharedPreferences sharedpreferences;

    private int nurseProfileID;
    private TextView textViewNurseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        nurseProfileID = sharedpreferences.getInt(NurseKey, 0);
        textViewNurseID = findViewById(R.id.textViewNurseID);
        textViewNurseID.setText("Nurse ID: "+nurseProfileID);
    }

    public void addPatients(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }

    public void editPatients(View view){
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }
}