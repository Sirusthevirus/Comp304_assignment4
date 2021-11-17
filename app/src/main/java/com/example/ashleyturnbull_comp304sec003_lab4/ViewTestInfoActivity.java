package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewTestInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private PatientViewModel patientViewModel;
    private TestViewModel testViewModel;
    private EditText editTextPatientID, editTextPatientName, editTextNurseID, editTextBPL, editTextBPH, editTextTemp, editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        //Spinner

        //ArrayList<String> patientsNames = new ArrayList<String>();
        ArrayList<Integer> testIDs = new ArrayList<Integer>();

//        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
//            @Override
//            public void onChanged(@Nullable List<Patient> result) {
//
//                for(Patient patient : result) {
//                    patientsNames.add(patient.getFirstName());
//                }
//            }
//        });

        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> result) {
                Toast.makeText(ViewTestInfoActivity.this, "Size:  \n" + result.size(), Toast.LENGTH_LONG).show();
                for(Test test : result) {
                    testIDs.add(test.getTestID());
                }

            }
        });

        Spinner spinner = findViewById(R.id.spinner_Tests);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testIDs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String string = adapterView.getItemAtPosition(i).toString();
        int testID = Integer.parseInt((string));
        Test tempTest = testViewModel.getAllTests().getValue().get(i);
        editTextPatientID.setText(tempTest.getPatientID());
        editTextNurseID.setText((tempTest.getNurseID()));
        editTextBPL.setText(tempTest.getBPL());
        editTextBPH.setText(tempTest.getBPH());
        editTextTemp.setText(""+tempTest.getTemperature());
        editTextDate.setText(tempTest.getTestDate());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
    }
}