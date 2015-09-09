package com.androidyug.nitin.autosuggestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Nitin Neo on 9/8/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "student.db";
    private static final int DB_VERSION = 1;

    // Column names
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_DEPARTMENT = "department";

    // Table name
    public static final String TABLE_NAME = "studentdata";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table studentdata (_id text primary key , "
                + "name text not null, " + "age text not null," + "department text not null);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create table again
        onCreate(db);
    }



    // support methods for CRUD operation
    public void createStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId().toString());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_DEPARTMENT, student.getDepartment());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> readNames(){
        ArrayList<String> names = new ArrayList<String>();
        String selectQuery = "SELECT  name FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                names.add(name);
            } while (cursor.moveToNext());
        }
        return names;
    }

    public ArrayList<Student> readStudents(String keyword){
        ArrayList<Student> students = new ArrayList<Student>();

        String queryString = "SELECT  * FROM " + TABLE_NAME + " WHERE name LIKE " + "'%" + keyword + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setId(UUID.fromString(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setAge(cursor.getString(2));
                student.setDepartment(cursor.getString(3));
                students.add(student);
            } while(cursor.moveToNext());
        }

        return students;
    }




}
