package com.example.ashleyturnbull_comp304sec003_lab4;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NurseRepository {
    public NurseDao nurseDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Nurse>> allNurses;
    public NurseRepository(Application application) {
        NurseDatabase db = NurseDatabase.getDatabase(application);
        nurseDao = db.nurseDao();
        allNurses = nurseDao.getAllNurses();
    }

    public LiveData<List<Nurse>> getAllNurses() {
        return allNurses;
    }

    public void insert(Nurse nurse) {
        NurseDatabase.databaseWriteExecutor.execute(() -> {nurseDao.insert(nurse);});
    }
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public LiveData<Nurse> findbyNurseID(int nurseID) {
        return nurseDao.getByNurseID(nurseID);
    }
}
