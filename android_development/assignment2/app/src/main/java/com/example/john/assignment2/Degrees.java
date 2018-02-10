package com.example.john.assignment2;

import android.content.Context;

public class Degrees {
    private Context context;
    protected String[] degrees;
    protected String[] majors;

    public Degrees(Context context){
        this.context = context;
        degrees = context.getResources().getStringArray(R.array.advanced_degrees);
    }
    public void setMajors(int degreeIndex){ //could also be the value of the index as String
        switch (degreeIndex){
            case 0:
                majors = context.getResources().getStringArray(R.array.doctor_of_philosophy);
                break;
            case 1:
                majors = context.getResources().getStringArray(R.array.doctor_of_education);
                break;
            case 2:
                majors = context.getResources().getStringArray(R.array.master_of_arts);
                break;
            case 3:
                majors = context.getResources().getStringArray(R.array.master_of_science);
                break;
            case 4:
                majors = context.getResources().getStringArray(R.array.master_of_fine_arts);
                break;
        }
    }
    public String[] getMajors(){
        return majors;
    }
}
