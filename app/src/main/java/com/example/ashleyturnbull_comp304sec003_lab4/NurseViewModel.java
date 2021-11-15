package com.example.ashleyturnbull_comp304sec003_lab4;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NurseViewModel extends AndroidViewModel{
    private NurseRepository nurseRepository;
    private LiveData<List<Nurse>> allNurses;
    public NurseViewModel(Application application) {
        super((application));
        nurseRepository = new NurseRepository(application);
        allNurses = nurseRepository.getAllNurses();
    }

    public LiveData<Nurse> findByNurseID(int nurseID) {
        return nurseRepository.findbyNurseID(nurseID);
    }

    public void insert(Nurse nurse) {
        nurseRepository.insert(nurse);
    }

    public LiveData<List<Nurse>> getAllNurses() {
        return allNurses;
    }
}
