package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "Assignment4Prefs" ;
    public static final String NurseKey = "nurseID";
    SharedPreferences sharedpreferences;

    private int nurseProfileID;
    private TextView textViewNurseID, textViewNurseName, textViewNurseDepartment;

    private NurseViewModel nurseViewModel;
    private Nurse nurse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        nurseProfileID = sharedpreferences.getInt(NurseKey, 0);
        textViewNurseID = findViewById(R.id.textViewNurseID);
        textViewNurseName = findViewById(R.id.textNurseName);
        textViewNurseDepartment = findViewById(R.id.textViewDepartment);

        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
                String output="";
                for(Nurse n : nurses) {
                    if(n.getNurseID() == nurseProfileID) {
                        textViewNurseName.setText("Welcome: "+n.getFirstName());
                        textViewNurseID.setText("Nurse ID: "+n.getNurseID());
                        textViewNurseDepartment.setText("Department: " + n.getDepartment());
                    }
                }
            }
        });

    }

    private Nurse findNurse(int id){
        List<Nurse> nurseList = nurseViewModel.getAllNurses().getValue();

        for (Nurse n : nurseList){
            if(n.getNurseID() == id){
                return n;
            }
        }
        return null;
    }

    public void addPatients(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }

    public void editPatients(View view){
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }

    public void viewTestInfo(View view){
        Intent intent = new Intent(this, ViewTestInfoActivity.class);
        startActivity(intent);
    }

    public void enterTestInfo(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}