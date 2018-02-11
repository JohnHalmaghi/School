package com.example.john.assignment2;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class AdvancedDegreeFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private String[] advancedDegrees;
    private Button cancelButton;
    private static final String CANCEL_BUTTON_STRING = "cancel";
    View view;

    public AdvancedDegreeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advanced_degree, container, false);
        MajorsFragment.ButtonPressedListener listener = (MajorsFragment.ButtonPressedListener)getActivity();
        cancelButton = (Button)view.findViewById(R.id.cancel_button_adv);
        cancelButton.setOnClickListener(view -> {
            listener.buttonPressed(CANCEL_BUTTON_STRING);
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        advancedDegrees = getResources().getStringArray(R.array.advanced_degrees);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, advancedDegrees);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       DegreeSelectionListener listener = (DegreeSelectionListener)getActivity();
       listener.degreeChosen(position);
    }
    public interface DegreeSelectionListener{
        void degreeChosen(int degreeIndex);
    }
}
