package com.example.ashleyturnbull_comp304sec003_lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class PatientEditActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private EditText firstName, lastName, department, nurseID, room;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        textView = findViewById(R.id.textView);
        displayDatabase();
    }

    public void createPatientButton(View view) {
        firstName = (EditText)findViewById(R.id.txtFirstName);
        lastName = (EditText)findViewById(R.id.txtLastName);
        department = (EditText)findViewById(R.id.txtDepartment);
        nurseID = (EditText)findViewById(R.id.txtNurseID);
        room = (EditText)findViewById(R.id.txtRoom);
        if (firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0 && department.getText().toString().length() != 0
                &&nurseID.getText().toString().length() != 0 &&room.getText().toString().length() != 0) {
            String firstNameValue = firstName.getText().toString();
            String lastNameValue = lastName.getText().toString();
            String departmentValue = department.getText().toString();
            int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
            String roomValue = room.getText().toString();
            patientViewModel.insert(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));
            finish();
        }
        else {
            Toast.makeText(PatientEditActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayDatabase() {
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> result) {
                String output="";
                for(Patient patient : result) {
                    output+= patient.getFirstName() +"\n";
                }
                textView.setText(output);
            }
        });
    }
}