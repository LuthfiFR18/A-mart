package com.example.a_martmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.a_martmobprog.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private TextView userName, email, phoneNum, apartUnit;
    private ImageView profilePic;
    private ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using data binding
        FragmentProfileBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);

        // Initialize views
        userName = binding.profileName;
        email = binding.userEmail;
        phoneNum = binding.profilePhone;
        apartUnit = binding.profileUnit;
        profilePic = binding.profileImg;

        // Set click listener directly in the binding
        binding.editProfile.setOnClickListener(view -> showUserData());

        actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        // Hide the ActionBar in the ProfileFragment
        if (actionBar != null) {
            actionBar.hide();
        }

        // Load user data from Firestore
        loadUserData();

        return binding.getRoot();
    }

    private void loadUserData() {
        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get the current user's ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Retrieve user data from Firestore
        db.collection("users").document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // DocumentSnapshot contains the data
                            String userNameText = document.getString("name");
                            String emailText = document.getString("email");
                            String phoneNumText = document.getString("phone");
                            String apartUnitText = document.getString("unit");

                            // Update views with retrieved data
                            userName.setText(userNameText);
                            email.setText(emailText);
                            phoneNum.setText(phoneNumText);
                            apartUnit.setText(apartUnitText);
                        } else {
                            // Document does not exist
                            // Handle the case where the user data is not found
                        }
                    } else {
                        // Handle errors while fetching user data
                    }
                });
    }

    private void showUserData() {
        Intent intent = new Intent(requireContext(), Editprofile.class);
        // Pass the existing user data to the EditProfile activity
        intent.putExtra("userNameKey", userName.getText().toString());
        intent.putExtra("emailKey", email.getText().toString());
        intent.putExtra("phoneKey", phoneNum.getText().toString());
        intent.putExtra("unitKey", apartUnit.getText().toString());
        intent.putExtra("imgKey", R.drawable.rectangle);
        startActivity(intent);
    }
}
