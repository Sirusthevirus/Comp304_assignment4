package com.example.ashleyturnbull_comp304sec003_lab4;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class PatientRepository {
    public PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;
    public PatientRepository(Application application) {
        PatientDatabase db = PatientDatabase.getDatabase(application);
        patientDao = db.patientDao();
        allPatients = patientDao.getAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public void insert(Patient patient) {
        PatientDatabase.databaseWriteExecutor.execute(() -> {patientDao.insert(patient);});
    }

    public LiveData<Patient> findbyPatientID(int patientID) {
        return patientDao.getByPatientID(patientID);
    }
}
