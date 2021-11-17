package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ViewTestInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        //Spinner

        ArrayList<Integer> patientsIDs = new ArrayList<Integer>();

        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> result) {

                for(Patient patient : result) {
                    patientsIDs.add(patient.getPatientID());
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner_patients);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, patientsIDs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}