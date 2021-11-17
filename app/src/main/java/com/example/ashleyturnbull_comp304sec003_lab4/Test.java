package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testID;

    @NonNull
    private int patientID;

    @NonNull
    private int nurseID;

    @NonNull
    private int BPL;

    @NonNull
    private int BPH;

    @NonNull
    private float temperature;

    @NonNull
    private String testDate;

    public Test(@NonNull int patientID, @NonNull int nurseID, @NonNull int BPL, @NonNull int BPH, @NonNull float temperature, @NonNull String testDate) {
        this.patientID = patientID;
        this.nurseID = nurseID;
        this.BPH = BPH;
        this.BPL = BPL;
        this.temperature = temperature;
        this.testDate = testDate;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getTestID() {
        return testID;
    }

    @NonNull
    public int getPatientID() {
        return patientID;
    }

    @NonNull
    public int getNurseID() {
        return nurseID;
    }

    @NonNull
    public int getBPL() {
        return BPL;
    }

    @NonNull
    public int getBPH() {
        return BPH;
    }

    @NonNull
    public float getTemperature() {
        return temperature;
    }

    @NonNull
    public String getTestDate() {
        return testDate;
    }
}
