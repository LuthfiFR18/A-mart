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
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(layoutManager);

        List<HorizontalNavigationItems> itemList = new ArrayList<>();
        itemList.add(new HorizontalNavigationItems(R.drawable.rectangle, "Product 1","Rp4.000,00"));
        itemList.add(new HorizontalNavigationItems(R.drawable.rectangle, "Product 2", "Rp3.000,00"));
        itemList.add(new HorizontalNavigationItems(R.drawable.rectangle, "Product 3", "Rp5.000,00"));

        HorizontalAdapter adapter = new HorizontalAdapter(itemList);
        horizontalRecyclerView.setAdapter(adapter);

        // Add more items as needed
        adapter.setItems(itemList);

        String[] productName = {"Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"};
        String[] productPrice = {"Rp3.000,00", "Rp4.000,00", "Rp5.000,00", "Rp6.000,00", "Rp7.000,00", "Rp8.000,00"};
        int[] productImage = {R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle};

        GridView gridView = view.findViewById(R.id.gridViewDiscountitems);
        GridAdapter gridAdapter = new GridAdapter(requireContext(), productName, productPrice, productImage);
        gridView.setAdapter(gridAdapter);

        return view;
    }





}
