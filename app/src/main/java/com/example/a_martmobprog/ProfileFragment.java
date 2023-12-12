package com.example.a_martmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.a_martmobprog.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    TextView userName, Email, phoneNum, apartUnit;
    ImageView profilePic;

    private ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using data binding
        FragmentProfileBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);

        // Set click listener directly in the binding
        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserData();
            }
        });

        actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        // Hide the ActionBar in the ProfileFragment
        if (actionBar != null) {
            actionBar.hide();
        }
        return binding.getRoot();
    }

    public void showUserData() {
        Intent intent = new Intent(requireContext(), Editprofile.class);
        intent.putExtra("userNameKey", "John Doe");
        intent.putExtra("emailKey", "john.doe@example.com");
        intent.putExtra("phoneKey", "123-456-7890");
        intent.putExtra("unitKey", "Apartment 123");
        intent.putExtra("imgKey", R.drawable.rectangle);
        startActivity(intent);
    }
}
