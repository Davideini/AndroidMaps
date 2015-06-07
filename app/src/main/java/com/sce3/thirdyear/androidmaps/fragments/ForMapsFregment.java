package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.maps.FindByAddressActivity;
import com.sce3.thirdyear.maps.data.Address;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForMapsFregment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForMapsFregment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForMapsFregment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_for_maps_fregment, container, false);
        Button btn = (Button) view.findViewById(R.id.btnForSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText address = (EditText) getActivity().findViewById(R.id.etSearchField);

                Intent i = new Intent(getActivity(), FindByAddressActivity.class);
                i.putExtra(Address.FOR_DEPARTMANTS, true);
                i.putExtra(Address.FORMATTED_ADDRESS, address.getText().toString());
                getActivity().startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
