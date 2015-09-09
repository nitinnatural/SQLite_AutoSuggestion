package com.androidyug.nitin.autosuggestion;

import java.util.UUID;

/**
 * Created by Nitin Neo on 9/8/2015.
 */
public class Student {
    private UUID mId;
    private String mName;
    private String mAge;
    private String mDepartment;

    public Student() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id){
        this.mId = id;
    }


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String mAge) {
        this.mAge = mAge;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String mDepartment) {
        this.mDepartment = mDepartment;
    }
}
