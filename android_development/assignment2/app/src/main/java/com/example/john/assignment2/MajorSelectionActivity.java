package com.example.john.assignment2;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class MajorSelectionActivity extends AppCompatActivity implements AdvancedDegreeFragment.DegreeSelectionListener,
                                                                         MajorsFragment.MajorSelectionListener,
                                                                         MajorsFragment.ButtonPressedListener{

    protected String majorSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_selection);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction degreeFragmentTransaction = fragmentManager.beginTransaction();
        AdvancedDegreeFragment advancedDegreeFragment = new AdvancedDegreeFragment();
        degreeFragmentTransaction.replace(R.id.fragment_container, advancedDegreeFragment);
        degreeFragmentTransaction.commit();
    }

    @Override
    public void degreeChosen(int degreeIndex) {
        MajorsFragment majorsFrag = new MajorsFragment();
        Bundle args = new Bundle();
        args.putInt(MajorsFragment.ARG_POSITION, degreeIndex);
        majorsFrag.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, majorsFrag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void majorSelected(String majorSelected) {
        majorSelection = majorSelected;
        Intent toPassBack = getIntent();
        toPassBack.putExtra("major", majorSelected);
        setResult(RESULT_OK, toPassBack);
        finish();
    }

    @Override
    public void buttonPressed(String buttonPressed) {
        if(buttonPressed == "cancel"){
            Intent toPassBack = getIntent();
            toPassBack.putExtra("buttonPressed", "cancel");
            setResult(RESULT_OK, toPassBack);
            finish();
        }
        if(buttonPressed == "back"){
            AdvancedDegreeFragment advancedDegreeFragment = new AdvancedDegreeFragment();
            FragmentTransaction toAdvancedDegreeFragment = getSupportFragmentManager().beginTransaction();
            toAdvancedDegreeFragment.replace(R.id.fragment_container, advancedDegreeFragment);
            toAdvancedDegreeFragment.commit();
        }
    }
}