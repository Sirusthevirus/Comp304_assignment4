package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        private ListView patientListView;
        private PatientViewModel patientViewModel;
        private EditText firstName, lastName, department, nurseID, room;
        String Value1, Value2, Value3, Value4, Value5;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_patient_list);
                firstName = (EditText)findViewById(R.id.editText_FirstName);
                lastName = (EditText)findViewById(R.id.editText_LastName);
                department = (EditText)findViewById(R.id.editText_Department);
                nurseID = (EditText)findViewById(R.id.editText_NurseID);
                room = (EditText)findViewById(R.id.editText_Room);
                Value1 = "test1";

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
                Spinner spinner = findViewById(R.id.spn_Patients);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, patientsIDs);
                adapter.notifyDataSetChanged();
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);
                spinner.setSelection(1);
                populateData();
        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String string = parent.getItemAtPosition(position).toString();
                int patientID = Integer.parseInt((string));
                Patient patient = patientViewModel.findByPatientID(patientID).getValue();
                firstName.setText(patient.getFirstName());
                lastName.setText(patient.getLastName());
                department.setText(patient.getDepartment());
                nurseID.setText(patient.getNurseID());
                room.setText(patient.getRoom());
        }

        public void populateData() {
                firstName.setText("Test1");
                lastName.setText("Test1");
                department.setText("Test1");
                nurseID.setText("1");
                room.setText("Test1");
        }

        public void savePatient() {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
}