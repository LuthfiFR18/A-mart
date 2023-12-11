package com.example.a_martmobprog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.a_martmobprog.EditProfileFragment;
import com.example.a_martmobprog.R;


public class ProfileFragment extends Fragment implements View.OnClickListener{

    Fragment editProfileFragment;
    FragmentManager fragmentManager = getParentFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button editBtn = view.findViewById(R.id.editProfile);
        editBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
       editProfileFragment = new EditProfileFragment();
        //fragmentTransaction.replace(R.id.container, editProfileFragment);
        fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
    }
}
