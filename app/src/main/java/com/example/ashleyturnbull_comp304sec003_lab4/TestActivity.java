package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    private TestViewModel testViewModel;
    private EditText editTextPatientID, editTextNurseID, editTextBPL, editTextBPH, editTextTemp, editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        editTextPatientID = findViewById(R.id.editText_EnterPatientID);
        editTextNurseID = findViewById((R.id.editText_EnterNurseID));
        editTextBPL = findViewById(R.id.editText_EnterBPL);
        editTextBPH = findViewById(R.id.editText_EnterBPH);
        editTextTemp = findViewById(R.id.editText_EnterTemp);
        editTextDate = findViewById(R.id.editText_EnterDateOfTest);

    }

    public void createTest(View view){
        String patientIDVal = editTextPatientID.getText().toString();
        String nurseIDVal = editTextNurseID.getText().toString();
        String bplVal = editTextBPL.getText().toString();
        String bphVal = editTextBPH.getText().toString();
        String tempVal = editTextTemp.getText().toString();
        String dateVal = editTextDate.getText().toString();
        if(patientIDVal.length() != 0 && nurseIDVal.length() != 0 && bplVal.length() != 0
                && bphVal.length() != 0 && tempVal.length() != 0 && dateVal.length() != 0){
            Test testTemp = new Test(Integer.parseInt(patientIDVal),Integer.parseInt(nurseIDVal), Integer.parseInt(bplVal),
                    Integer.parseInt(bphVal), Float.parseFloat(tempVal), dateVal);
            testViewModel.insert(testTemp);
            Toast.makeText(TestActivity.this, "Test Info Successfully Entered", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(TestActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
        }

    }
}