package com.example.a_martmobprog;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set the custom action bar style

//        List<HorizontalNavigationItems> itemList = new ArrayList<>();
//        itemList.add(new HorizontalNavigationItems(R.drawable.rectangle, "Product 1"));
//        itemList.add(new HorizontalNavigationItems(R.drawable.rectangle, "Product 2"));
//
//        HorizontalAdapter adapter = new HorizontalAdapter(itemList);
//        horizontalRecyclerView.setAdapter(adapter);
//
//        // Add more items as needed
//        adapter.setItems(itemList);

        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        Log.d("MenuInflate", "onCreateOptionsMenu called");
//        inflater.inflate(R.menu.toolbar_menu, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int name = item.getItemId();
//        if (name == R.id.action_bar_profile){
//            startActivity(new Intent(getActivity(), .class));
//        }
//        //else{
//        // startActivity(new Intent(getActivity(), .class));
//        // }
//        return super.onOptionsItemSelected(item);



//    }
}