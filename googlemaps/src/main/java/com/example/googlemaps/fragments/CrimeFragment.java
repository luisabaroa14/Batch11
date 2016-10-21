package com.example.googlemaps.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.googlemaps.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CrimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeFragment extends DialogFragment {


    private String mDireccion;
    private String mDate;
    private String mTipo;

    public CrimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CrimeFragment.
     */
//    public static CrimeFragment newInstance(String direccion, String date, String tipoCrimen) {
//        CrimeFragment fragment = new CrimeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("direccion", direccion);
//        bundle.putString("date", date);
//        bundle.putString("key", tipoCrimen);
//        fragment.setArguments(bundle);
//        return fragment;
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDireccion = getArguments().getString("direccion");
            mDate = getArguments().getString("date");
            mTipo = getArguments().getString("key");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("hola");
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);


        return view;
    }


}
