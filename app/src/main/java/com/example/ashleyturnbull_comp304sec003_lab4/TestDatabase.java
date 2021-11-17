package com.example.ashleyturnbull_comp304sec003_lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Test.class, version = 1, exportSchema = false)
public abstract class TestDatabase extends RoomDatabase {

    private static TestDatabase instance;

    public abstract TestDao testDao();

    public static synchronized TestDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TestDatabase.class, "test_database")
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TestDao testDao;

        private PopulateDbAsyncTask(TestDatabase db){
            testDao = db.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            //If you want to pre insert data add it here
            Test test = new Test(2, 301130935, 90, 120, 36, "17 Nov 2021");
            test.setTestID(1);
            testDao.insert(test);
            return null;
        }
    }

}
