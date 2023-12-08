package com.example.a_martmobprog.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a_martmobprog.R;

public class EditProfileFragment extends Fragment implements View.OnClickListener{

    Fragment profileFragment;
    FragmentManager fragmentManager = getParentFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        profileFragment = new ProfileFragment();
        fragmentTransaction.replace(R.id.container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
