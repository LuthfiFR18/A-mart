package com.example.a_martmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.example.a_martmobprog.databinding.FragmentProductBinding;
import com.example.a_martmobprog.databinding.FragmentProductBinding;


public class ProductFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProductFragment() {

    }


    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

   FragmentProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentProductBinding.inflate(inflater,container,false);

        String[]productName = {"Product 1","Product 2","Product 3","Product 4","Product 5","Product 6","Product 7","Product 8"};
        String[]productPrice = {"Rp3.000,00","Rp4.000,00","Rp5.000,00","Rp6.000,00","Rp7.000,00","Rp8.000,00","Rp9.000,00","Rp10.000,00"};
        int[] productImage = {R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle,R.drawable.rectangle};

        GridAdapter gridAdapter = new GridAdapter(requireContext(),productName,productPrice,productImage);
        binding.gridView.setAdapter(gridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(requireActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}