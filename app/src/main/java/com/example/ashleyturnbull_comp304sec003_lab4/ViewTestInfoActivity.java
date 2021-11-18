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

        //ArrayList<String> patientsNames = new ArrayList<String>();
        ArrayList<Integer> testIDs = new ArrayList<Integer>();

        //Spinner
        Spinner spinner = findViewById(R.id.spinner_Tests);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testIDs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        editTextPatientName = (EditText)findViewById(R.id.editText_Name);
        editTextPatientID = (EditText)findViewById(R.id.editText_PatientID);
        editTextNurseID = (EditText)findViewById(R.id.editText_NurseID);
        editTextBPL = (EditText)findViewById(R.id.editText_BPL);
        editTextBPH = (EditText)findViewById(R.id.editText_BPH);
        editTextTemp = (EditText)findViewById(R.id.editText_Temperature);
        editTextDate = (EditText)findViewById(R.id.editTextDateOfTest);
        try {
            populateData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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
                //Toast.makeText(ViewTestInfoActivity.this, "Size:  \n" + result.size(), Toast.LENGTH_LONG).show();
                for(Test test : result) {
                    testIDs.add(test.getTestID());
                }

            }
        });


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

    public void populateData() throws InterruptedException {
        Thread.sleep(1000);
        editTextPatientName.setText("Test1");
        editTextPatientID.setText("1");
        editTextNurseID.setText("1");
        editTextBPL.setText("1");
        editTextBPH.setText("1");
        editTextTemp.setText("1");
        editTextDate.setText("2021/11/18");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
    }
}