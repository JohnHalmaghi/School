package com.example.john.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity {

    private static final int INTENT_REQUEST = 58; //random int for intnent passback varification
    private static final String VALUES = "MyValuesFile";
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText email;
    private EditText phoneNumber;
    private TextView major;
    private Button selectMajor;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        setWidgets();
        loadValuesFromPreferences();

        selectMajor.setOnClickListener((View v) -> {
            Intent toMajorSelection = new Intent(this, MajorSelectionActivity.class);
            startActivityForResult(toMajorSelection, INTENT_REQUEST);
        });
        done.setOnClickListener((View v) -> {
            saveValuesToPreferences();
            finish();
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != INTENT_REQUEST) return;
        if(resultCode == RESULT_CANCELED) return;
        if(data.getStringExtra("buttonPressed") == "cancel"){
            finish();
            startActivity(getIntent());
            return;
        }
        else if(data.getStringExtra("major") != null){
            major.setText(getResources().getString(R.string.major_textField) + " " + data.getStringExtra("major"));
            return;
        }

    }

    protected void saveValuesToPreferences(){
        SharedPreferences values = getSharedPreferences(VALUES, 0);
        SharedPreferences.Editor editor = values.edit();
        editor.putString("firstName", String.valueOf(firstName.getText()));
        editor.putString("lastName", String.valueOf(lastName.getText()));
        editor.putString("age", String.valueOf(age.getText()));
        editor.putString("email", String.valueOf(email.getText()));
        editor.putString("phoneNumber", String.valueOf(phoneNumber.getText()));
        editor.putString("major", String.valueOf(major.getText()));

        editor.commit();
    }

    private void setWidgets(){
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        major = (TextView) findViewById(R.id.major);
        selectMajor = (Button) findViewById(R.id.select_major);
        done = (Button)findViewById(R.id.done_button);
    }

    private void loadValuesFromPreferences(){
        SharedPreferences values = getSharedPreferences(VALUES, 0);

        firstName.setText(values.getString("firstName", ""));
        lastName.setText(values.getString("lastName", ""));
        age.setText(values.getString("age", ""));
        email.setText(values.getString("email", ""));
        phoneNumber.setText(values.getString("phoneNumber", ""));
        major.setText(values.getString("major", ""));
    }
}

