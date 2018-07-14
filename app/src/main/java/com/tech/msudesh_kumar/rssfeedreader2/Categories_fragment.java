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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Categories_fragment extends Fragment {

    SwipeRefreshLayout srl;
    RecyclerView r1;
    List<String> categories;
    LinearLayoutManager lml;
    Categories_recycler_adapter rca;
    MainActivity mA;
    Cursor cr1;

    String category_name;

    public class Categories_recycler_adapter extends RecyclerView.Adapter<Categories_recycler_adapter.ViewHolder>
    {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.categories_row,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            category_name = categories.get(position);
            holder.tv1.setText(category_name);
            holder.ll1.setTag(category_name);
            holder.ll1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mA, category_name, Toast.LENGTH_SHORT).show();
                    FragmentManager frm = getFragmentManager();
                    FragmentTransaction frt = frm.beginTransaction();
                    Bundle b = new Bundle();
                    b.putString("category",category_name);
                    Sites_fragment sf = new Sites_fragment();
                    sf.setArguments(b);
                    frt.replace(R.id.main_container,sf);
                    frt.addToBackStack(null);
                    frt.commit();
                    /*if(v.getTag().equals("Science"))
                    {
                        FragmentManager frm = getFragmentManager();
                        FragmentTransaction frt = frm.beginTransaction();
                        Sites_fragment sf = new Sites_fragment();
                        frt.replace(R.id.main_container,sf);
                        frt.addToBackStack(null);
                        frt.commit();
                    }*/
                }
            });
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1;
            LinearLayout ll1;
            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.categories_row_textview1);
                ll1 = itemView.findViewById(R.id.categories_row_linearLayout1);
            }
        }
    }

    public Categories_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categories_fragment, container, false);
        srl = v.findViewById(R.id.categories_fragment_swipeRefresher);
        r1 = v.findViewById(R.id.categories_fragment_recycler);

        mA = (MainActivity) getActivity();
        /*ActionBar ab = mA.getActionBar();
        ab.setTitle("Categories");*/

        android.support.v7.app.ActionBar ab = mA.getSupportActionBar();
        ab.setTitle("Categories");

        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        TextView tvr = toolbar.findViewById(R.id.toolbar_title_textview);
        tvr.setText("Categories");

        /*b1 = toolbar.findViewById(R.id.)

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        setHasOptionsMenu(true);

        categories = new ArrayList<String>();
        cr1 = mA.mDB.queryCategories();
        if (cr1!=null)
        {
          while(cr1.moveToNext()==true)
          {
              categories.add(cr1.getString(0));
          }
        }
        /*categories.add("All");
        categories.add("Science");
        categories.add("Tech");
        categories.add("Environment");
        categories.add("Sports");
        categories.add("Politics");*/

        lml = new LinearLayoutManager(getActivity());
        r1.setLayoutManager(lml);
        rca = new Categories_recycler_adapter();
        r1.setAdapter(rca);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*lml = new LinearLayoutManager(getActivity());
                r1.setLayoutManager(lml);
                rca = new Categories_recycler_adapter();
                r1.setAdapter(rca);
                rca.notifyDataSetChanged();
                srl.setRefreshing(false);*/
                FragmentManager frm = mA.getSupportFragmentManager();
                FragmentTransaction frt = frm.beginTransaction();
                Categories_fragment cf = new Categories_fragment();
                frt.replace(R.id.main_container,cf);
                //frt.addToBackStack(null);
                frt.commit();
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main_actions,menu);

        MenuItem m1 = menu.findItem(R.id.add_rss_site);
        m1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(mA, "Add site", Toast.LENGTH_SHORT).show();
                Add_Site_Dialog asd = new Add_Site_Dialog();
                asd.show(getActivity().getSupportFragmentManager(),null);
                return true;
            }
        });

        MenuItem m2 = menu.findItem(R.id.add_collection);
        m2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(mA, "Add collection", Toast.LENGTH_SHORT).show();
                Add_category_dialog acd = new Add_category_dialog();
                acd.show(getActivity().getSupportFragmentManager(),null);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
