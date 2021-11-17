package com.example.ashleyturnbull_comp304sec003_lab4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TestRepository {
    private TestDao testDao;
    private LiveData<List<Test>> allTests;

    public TestRepository(Application application){
        TestDatabase database = TestDatabase.getInstance(application);
        testDao = database.testDao();
        allTests = testDao.getAllTests();
    }

    public void insert(Test test){
        new InsertTestAsyncTask(testDao).execute(test);
    }

    public void update(Test test){
        new UpdateTestAsyncTask(testDao).execute(test);
    }

    public void deleteAllTests(){
        new DeleteAllTestAsyncTask(testDao).execute();
    }

    public LiveData<List<Test>> getAllTests(){
        return allTests;
    }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void>{
        private TestDao testDao;

        private InsertTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests){
            testDao.insert(tests[0]);
            return null;
        }
    }
    private static class UpdateTestAsyncTask extends AsyncTask<Test, Void, Void>{
        private TestDao testDao;

        private UpdateTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests){
            testDao.update(tests[0]);
            return null;
        }
    }
    private static class DeleteAllTestAsyncTask extends AsyncTask<Test, Void, Void>{
        private TestDao testDao;

        private DeleteAllTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests){
            testDao.deleteAll();
            return null;
        }
    }
}
