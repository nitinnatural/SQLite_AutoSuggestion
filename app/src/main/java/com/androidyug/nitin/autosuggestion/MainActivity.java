package com.androidyug.nitin.autosuggestion;

import android.app.ActionBar;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";

    private AutoCompleteTextView mTextField;
    private ArrayAdapter<String> mAdapterNames;

    private ArrayAdapter<Student> mListAdapter;
    private ListView listView;
    DbHelper mDbHelper;

    ArrayList<Student> mStudents;


    // method to create dummy sqlite entry
    private void createDummyStudent(String name, String age, String department){
        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        s.setDepartment(department);
        mDbHelper.createStudent(s);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DbHelper(this);


        // some dummy entry into the database
        // it will get multiplied with every start of app.
        createDummyStudent("Krace", "25", "android");
        createDummyStudent("Krane", "27", "Web Developer");
        createDummyStudent("Sunith", "25", "ios");
        createDummyStudent("Sutith", "24", "Android");
        createDummyStudent("Deeptiman", "24", "andriod");
        createDummyStudent("Deepti", "19", "Android Dev");
        createDummyStudent("Prashannth", "25", "android");
        createDummyStudent("Prashant", "25", "IOS");
        createDummyStudent("Suresh", "38", "Founder");
        createDummyStudent("Subham", "31", "Management");
        createDummyStudent("Navin", "25", "android");
        createDummyStudent("Mohit", "25", "ios");
        createDummyStudent("Mohit", "23", "Android");
        createDummyStudent("Mohit", "25", "web developer");
        createDummyStudent("Mohini", "25", "ios");
        createDummyStudent("Manas", "25", "ios");
        createDummyStudent("Jyoti", "25", "HR");
        createDummyStudent("Jyaya", "25", "Team leader");
        createDummyStudent("Juhi", "25", "Developer");
        createDummyStudent("Juhu", "21", "Developer");
        createDummyStudent("Nevedha", "24", "developer");





        // get the defined string-array
        ArrayList<String> names = mDbHelper.readNames();
        mAdapterNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        mTextField = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        mTextField.setAdapter(mAdapterNames);

        mTextField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyword = (String) parent.getItemAtPosition(position);

                mStudents = mDbHelper.readStudents(keyword);

                listView = (ListView) findViewById(R.id.lv_result);
                mListAdapter = new ListAdapter(mStudents);

                listView.setAdapter(mListAdapter);
                Log.d(LOG_TAG, "" + mStudents.size());
                Toast.makeText(MainActivity.this, ""+keyword, Toast.LENGTH_SHORT).show();
            }
        });
    }


    class ListAdapter extends ArrayAdapter<Student>{

        public ListAdapter(ArrayList<Student> objects) {
            super(MainActivity.this, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.row, null);
            }

            Student s = getItem(position);

            TextView nameTextView = (TextView) convertView.findViewById(R.id.tvName);
            nameTextView.setText(s.getName().toString());

            TextView ageTextView = (TextView) convertView.findViewById(R.id.tvAge);
            ageTextView.setText(s.getAge().toString());

            TextView departmentTextView = (TextView) convertView.findViewById(R.id.tvDepartment);
            departmentTextView.setText(s.getDepartment().toString());

            return convertView;
        }
    }



}
