package com.example.a_martmobprog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a_martmobprog.databinding.ActivityEditprofileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Editprofile extends AppCompatActivity {

    private ActivityEditprofileBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            String nameUser = intent.getStringExtra("userNameKey");
            String emailUser = intent.getStringExtra("emailKey");
            String phoneUser = intent.getStringExtra("phoneKey");
            String unitUser = intent.getStringExtra("unitKey");
            int imgUser = intent.getIntExtra("imgKey", 0);

            // Set received data to corresponding views
            binding.editName.setText(nameUser);
            binding.editEmail.setText(emailUser);
            binding.editPhone.setText(phoneUser);
            binding.editUnit.setText(unitUser);

            //  set other views as needed
        }

        // Set click listener directly in the binding
        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToProfileFragment();
            }
        });

        // Handle the Save button click
        binding.saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
            }
        });
    }

    private void goBackToProfileFragment() {

        finish();
    }

    private void saveUserData() {
        String name = binding.editName.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();
        String phone = binding.editPhone.getText().toString().trim();
        String unit = binding.editUnit.getText().toString().trim();

        // Update user data in Firestore
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();
            Map<String, Object> userData = new HashMap<>();
            userData.put("name", name);
            userData.put("email", email);
            userData.put("phone", phone);
            userData.put("unit", unit);

            db.collection("users").document(userId)
                    .update(userData)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Editprofile.this, "User data updated successfully", Toast.LENGTH_SHORT).show();

                                goBackToProfileFragment();
                            } else {
                                Toast.makeText(Editprofile.this, "Failed to update user data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    // use this method to start EditProfileActivity from other classes or fragments
    public static void start(Context context) {
        Intent intent = new Intent(context, Editprofile.class);
        context.startActivity(intent);
    }
}
