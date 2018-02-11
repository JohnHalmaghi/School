package com.example.john.assignment2;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
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
    private static final String CANCEL_BUTTON_STRING = "cancel";
    private static final String BACK_BUTTON_STRING = "back";


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
            listener.buttonPressed(CANCEL_BUTTON_STRING);
        });

        backButton.setOnClickListener(view1 -> {
            listener.buttonPressed(BACK_BUTTON_STRING);
        });
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
                prefix = getResources().getString(R.string.phd_prefix);
                break;
            case 1:
                majors = getResources().getStringArray(R.array.doctor_of_education);
                prefix = getResources().getString(R.string.edd_prefix);
                break;
            case 2:
                majors = getResources().getStringArray(R.array.master_of_arts);
                prefix = getResources().getString(R.string.ma_prefix);
                break;
            case 3:
                majors = getActivity().getResources().getStringArray(R.array.master_of_science);
                prefix = getResources().getString(R.string.ms_prefix);
                break;
            case 4:
                majors = getResources().getStringArray(R.array.master_of_fine_arts);
                prefix = getResources().getString(R.string.mfa_prefix);
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
        void majorSelected(String majorSelected);
    }

    public interface ButtonPressedListener{
        void buttonPressed(String buttonPressed);
    }

}
