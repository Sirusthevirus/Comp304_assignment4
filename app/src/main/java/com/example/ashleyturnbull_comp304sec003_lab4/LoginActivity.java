package com.example.ashleyturnbull_comp304sec003_lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    private EditText editTextUsername, editTextPassword;

    Nurse nurse;

    public static final String MyPREFERENCES = "Assignment4Prefs" ;
    public static final String NurseKey = "nurseID";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editText_Username);
        editTextPassword = findViewById(R.id.editText_Password);
        //
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //
        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
                String output="";
                for(Nurse nurse : nurses) {
                    output+= nurse.getFirstName() + " "+ nurse.getNurseID() +" "+nurse.getPassword() +"\n";
                }
                Toast.makeText(LoginActivity.this, "Inserted:  \n" + output, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void attempt_Login(View view){
        int username = Integer.parseInt(editTextUsername.getText().toString());
        String password = editTextPassword.getText().toString();
        //Toast.makeText(LoginActivity.this, "Username: " + username + " " + "Password: " + password, Toast.LENGTH_SHORT).show();

        List<Nurse> nurseList = nurseViewModel.getAllNurses().getValue();

        Boolean nurseFound = false;

        for (Nurse n : nurseList){
            if((n.getNurseID() == username) && (n.getPassword().equals(password))){
                nurseFound = true;
                break;
            }
        }

        if(nurseFound){
            Toast.makeText(LoginActivity.this, "Correct Details Entered", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(NurseKey, username);
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}