package com.tech.msudesh_kumar.rssfeedreader2;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_category_dialog extends DialogFragment{

    EditText et1;
    Button b1;
    Button b2;
    MainActivity mA;
    Dialog d;

    public Add_category_dialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_category_dialog, null);
        et1 = v.findViewById(R.id.add_category_dialog_editText1);
        b1 = v.findViewById(R.id.add_category_dialog_button1);
        b2 = v.findViewById(R.id.add_category_dialog_button2);


        mA = (MainActivity) getActivity();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Category added", Toast.LENGTH_SHORT).show();
                mA.mDB.insertCategories(et1.getText().toString());
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        ab.setView(v);
        d = ab.create();
        return d;
    }
}
