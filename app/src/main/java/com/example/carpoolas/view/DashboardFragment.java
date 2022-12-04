package com.example.carpoolas.view;

import static com.example.carpoolas.controller.MainActivity.IS_SHOWN;
import static com.example.carpoolas.controller.MainActivity.curState;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentDashboardBinding;
import com.example.carpoolas.model.Listing;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;


public class DashboardFragment extends Fragment implements IDashboardView {

    FragmentDashboardBinding binding;
    Listener listener;
    FragmentManager fmanager;
    static String curRole;
    static String curEnd;
    String brief;
    ArrayList<Listing> listy = new ArrayList<>();


    public DashboardFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);
        return this.binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> dash = new ArrayList<>();
        //dash.add("No listings available :'(");

        //Import listings into an arrayList
        Iterator<Listing> listingsIterator = ((MainActivity)getActivity()).getListings().listings.iterator();
        while (listingsIterator.hasNext()){
            Listing listing = listingsIterator.next();
            listy.add(listing);
            curRole = listing.getRole();
            curEnd = listing.getEndLocation();
            if (curRole.equals("Driver")) {
                brief = "Listing: \nA " + curRole + " is going to " + curEnd + "";
            }
            if (curRole.equals("Passenger")){
                brief = "Listing: \nA " + curRole + " wants to go to " + curEnd + "";
            }
            dash.add(brief);
            }
        if(((MainActivity)getActivity()).getListings().isEmpty()){
            //dash.add("yes");
            dash.add("No listings available :'(");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        ListView listView = binding.listview;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String selectedPost = (String) listView.getItemAtPosition(position);
                Snackbar.make(view,"You selected : " + selectedPost,Snackbar.LENGTH_SHORT).show();
                //DetailedListingFragment detailedFragment = new DetailedListingFragment(curRole);

                //TODO send info to DetailedListingFragment
                //Listing listy= dash.get(position);
                if (!(((MainActivity)getActivity()).getListings().isEmpty())) {
                    curRole = listy.get(position++).getRole();

                    DashboardFragment.this.listener.goToDetailedPost(DashboardFragment.this, curRole);
                }
            }
        });
        //listView.setOnItemClickListener();
//        listView.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        ((MainActivity)getActivity()).areControlsShown(curState);
    }
}