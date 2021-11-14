package com.example.ashleyturnbull_comp304sec003_lab4;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nurse_table")
public class Nurse {
    @PrimaryKey(autoGenerate = true)
    private int nurseID;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String department;

    @NonNull
    private String password;

    public Nurse(@NonNull String firstName, @NonNull String lastName, @NonNull String department, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public int getNurseID() {
        return nurseID;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getDepartment() {
        return department;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
