package com.example.john.assignment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MajorsFragment extends ListFragment implements AdapterView.OnItemClickListener{

    String [] majors;
    String prefix;
    String major;
    final static String ARG_POSITION = "position";
    View view;
    Button cancelButton;
    Button backButton;
    private static final String cancelButtonString = "cancel";
    private static final String backButtonString = "back";


    public MajorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_majors, container, false);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        backButton = (Button) view.findViewById(R.id.back_button);
        ButtonPressedListener listener = (ButtonPressedListener)getActivity();
        cancelButton.setOnClickListener(view -> {
            listener.buttonPressed(cancelButtonString);
        });
        backButton.setOnClickListener(view1 -> {
            listener.buttonPressed(backButtonString);
        });
        //backButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onStart(){
        super.onStart();

        Bundle args = getArguments();
        if(args != null){
            setDegree(args.getInt(ARG_POSITION));
        }
    }

    public void setDegree(int degreeIndex){
        switch (degreeIndex){
            case 0:
                majors = getResources().getStringArray(R.array.doctor_of_philosophy);
                prefix = "Ph.D";
                break;
            case 1:
                majors = getResources().getStringArray(R.array.doctor_of_education);
                prefix = "Ed.D";
                break;
            case 2:
                majors = getResources().getStringArray(R.array.master_of_arts);
                prefix = "MA";
                break;
            case 3:
                majors = getActivity().getResources().getStringArray(R.array.master_of_science);
                prefix = "MS";
                break;
            case 4:
                majors = getResources().getStringArray(R.array.master_of_fine_arts);
                prefix = "MFA";
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, majors);
        setListAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        major = majors[position];
        MajorSelectionListener listener = (MajorSelectionListener)getActivity();
        listener.majorSelected(prefix+" "+major);
    }


    public interface MajorSelectionListener{
        public void majorSelected(String majorSelected);
    }

    public interface ButtonPressedListener{
        public void buttonPressed(String buttonPressed);
    }

}
