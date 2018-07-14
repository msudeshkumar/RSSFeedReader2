package com.tech.msudesh_kumar.rssfeedreader2;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Site_Dialog extends DialogFragment{

    EditText et1;
    Spinner s1;
    String category;
    Button b1;
    Button b2;
    TextView tv1;
    TextView tv2;
    Dialog d=null;
    MainActivity mA;

    List<String> categories;
    //List<String> sites;

    public Add_Site_Dialog() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_site_dialog,null);
        et1 = v.findViewById(R.id.add_site_dialog_editText1);
        s1 = v.findViewById(R.id.add_site_dialog_spinner1);
        b1 = v.findViewById(R.id.add_site_dialog_button1);
        b2 = v.findViewById(R.id.add_site_dialog_button2);
        tv1 = v.findViewById(R.id.add_site_dialog_textView2);
        tv2 = v.findViewById(R.id.add_site_dialog_textView3);

        mA = (MainActivity) getActivity();

        categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Science");
        categories.add("Tech");
        categories.add("Environment");
        categories.add("Sports");
        categories.add("Politics");

        /*sites = new ArrayList<String>();
        sites.add("The Verge");
        sites.add("Wired");
        sites.add("Ars Technica");
        sites.add("Techcrunch");
        sites.add("Techrepublic");*/

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(spinnerAdapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Site Added", Toast.LENGTH_SHORT).show();
                if(!(category.isEmpty()))
                {
                    //mA.mDB.insertSite(sites.get(0),et1.getText().toString(),category);
                    mA.mDB.insertSite(et1.getText().toString().substring((et1.getText().toString().indexOf("w.")+2),(et1.getText().toString().indexOf(".com"))).trim(),et1.getText().toString(),category);
                }
                else
                {
                    Toast.makeText(mA, "Please choose a category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                //tv1.setText(category);
                tv2.setText(category);
                Toast.makeText(mA, category, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ab.setView(v);
        d = ab.create();
        return d;
    }

    /*public String parseSiteName(String link) throws IOException, XmlPullParserException {
        String title;
        URL url = new URL(link);
        InputStream in = url.openConnection().getInputStream();
        XmlPullParser xmlpullparser = Xml.newPullParser();
        xmlpullparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlpullparser.setInput(in, null);

        return null;
    }*/
}
