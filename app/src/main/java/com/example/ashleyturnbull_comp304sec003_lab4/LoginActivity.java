package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    private Button btnLogin;
    private EditText editTextUsername, editTextPassword;

    Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editText_Username);
        editTextPassword = findViewById(R.id.editText_Password);
        btnLogin = findViewById(R.id.button_Login);
        //
        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        //
        //Create nurse Dhevan and insert in backend
        nurse = new Nurse("Dhevan", "Lau", "Diseases", "12345");
        nurse.setNurseID(301130935);
        nurseViewModel.insert(nurse);
        //
        btnLogin.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                int username = Integer.parseInt(editTextUsername.getText().toString());
                String password = editTextPassword.getText().toString();

                LiveData<List<Nurse>> nurseData = nurseViewModel.getAllNurses();
                List<Nurse> nurseList = nurseData.getValue();
                Boolean nurseFound = false;

                for (Nurse n : nurseList){
                    if((n.getNurseID() == username) && (n.getPassword() == password)){
                        nurseFound = true;
                        break;
                    }
                }
                if(nurseFound == true){
                    login_Successful(v);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login_Successful(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }
}