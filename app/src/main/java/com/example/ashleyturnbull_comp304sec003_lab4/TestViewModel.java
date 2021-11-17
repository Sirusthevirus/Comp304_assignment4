package com.example.ashleyturnbull_comp304sec003_lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository repository;
    private LiveData<List<Test>> allTests;
    public TestViewModel(@NonNull Application application){
        super(application);
        repository = new TestRepository(application);
        allTests = repository.getAllTests();
    }

    public void insert(Test test){
        repository.insert(test);
    }
    public void update(Test test){
        repository.update(test);
    }
    public void deleteAllTests(){
        repository.deleteAllTests();
    }
    public LiveData<List<Test>> getAllTests(){
        return allTests;
    }
}
