package com.tech.msudesh_kumar.rssfeedreader2;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sites_fragment extends Fragment {

    SwipeRefreshLayout srl;
    RecyclerView r1;
    Bundle b;
    String category;
    List<String> sites;
    LinearLayoutManager lml;
    Sites_recycler_adapter sra;
    MainActivity mA;
    Cursor cr1;

    public class Sites_recycler_adapter extends RecyclerView.Adapter<Sites_recycler_adapter.ViewHolder>
    {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.sites_row,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final String site = sites.get(position);
            holder.tv1.setText(site);
            holder.ll1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mA, site, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return sites.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout ll1;
            TextView tv1;

            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.sites_row_textview1);
                ll1 = itemView.findViewById(R.id.sites_row_linearLayout1);
            }
        }
    }

    public Sites_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sites_fragment, container, false);
        srl = v.findViewById(R.id.sites_fragment_swipeRefresher);
        r1 = v.findViewById(R.id.sites_fragment_recycler);

        b = getArguments();
        category = b.getString("category");

        mA = (MainActivity) getActivity();
        /*ActionBar ab = mA.getActionBar();
        ab.setTitle("sites");*/
        setHasOptionsMenu(true);

        android.support.v7.app.ActionBar ab = mA.getSupportActionBar();
        ab.setTitle("sites");

        Toolbar toolbar = mA.findViewById(R.id.main_toolbar);
        TextView tvr = toolbar.findViewById(R.id.toolbar_title_textview);
        tvr.setText("Sites");

        /*MenuInflater menuinflater = getActivity().getMenuInflater();
        menuinflater.inflate(R.menu.activity_main_actions, menu);

        MenuItem m1 = menu.findItem(R.id.create_collection);
        m1.setVisible(false);*/

        sites = new ArrayList<String>();
        /*sites.add("The Verge");
        sites.add("Ars Technica");
        sites.add("Wired");
        sites.add("Techcruch");
        sites.add("Techrepublic");*/

        cr1 = mA.mDB.querySites(category);
        if(cr1!=null)
        {
            while (cr1.moveToNext()==true)
            {
                sites.add(cr1.getString(0));
            }
        }

        lml = new LinearLayoutManager(getActivity());
        r1.setLayoutManager(lml);
        sra = new Sites_recycler_adapter();
        r1.setAdapter(sra);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentManager frm = getFragmentManager();
                FragmentTransaction frt = frm.beginTransaction();
                Sites_fragment sf = new Sites_fragment();
                Bundle b = new Bundle();
                b.putString("category",category);
                sf.setArguments(b);
                frt.replace(R.id.main_container,sf);
                frt.commit();
                srl.setRefreshing(false);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        /*MenuInflater menuinflater = getActivity().getMenuInflater();
        menuinflater.inflate(R.menu.activity_main_actions, menu);*/

        inflater.inflate(R.menu.activity_main_actions, menu);
        MenuItem m1 = menu.findItem(R.id.add_collection);
        m1.setVisible(false);
        MenuItem m2 = menu.findItem(R.id.add_rss_site);
        m2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(mA, "Add site", Toast.LENGTH_SHORT).show();
                Add_Site_Dialog asd = new Add_Site_Dialog();
                asd.show(getActivity().getSupportFragmentManager(),null);
                return true;
            }
        });

    }
}
