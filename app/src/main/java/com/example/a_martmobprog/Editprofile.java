package com.example.a_martmobprog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a_martmobprog.databinding.ActivityEditprofileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Editprofile extends AppCompatActivity {

    private ActivityEditprofileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("EditProfile", "User ID: " + userId);

        Intent intent = getIntent();
        if (intent != null) {
            String nameUser = intent.getStringExtra("userNameKey");
            String emailUser = intent.getStringExtra("emailKey");
            String phoneUser = intent.getStringExtra("phoneKey");
            String unitUser = intent.getStringExtra("unitKey");
            String password = intent.getStringExtra("passwordKey");
            int imgUser = intent.getIntExtra("imgKey", 0);

            // Set received data to corresponding views
            binding.editName.setText(nameUser);
            binding.editEmail.setText(emailUser);
            binding.editPhone.setText(phoneUser);
            binding.editUnit.setText(unitUser);
            binding.editPassword.setText(password);

            // Set click listener for the save button
            binding.saveEdit.setOnClickListener(view -> {
                // Update Firestore data and Firebase Authentication
                updateFirestoreUserData(userId);
                updateFirebaseAuthentication(emailUser, password);
                goBackToProfileFragment();
            });
        }

        // Set click listener directly in the binding for the back button
        binding.backbtn.setOnClickListener(view -> goBackToProfileFragment());
    }

    private void goBackToProfileFragment() {
        // Navigate back to the ProfileFragment or perform any necessary actions
        // For example, you can use finish() to close the EditProfileActivity
        finish();
    }

    private void updateFirestoreUserData(String userId) {
        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a map with the updated user data
        Map<String, Object> updatedUserData = new HashMap<>();
        updatedUserData.put("name", binding.editName.getText().toString().trim());
        updatedUserData.put("phone", binding.editPhone.getText().toString().trim());
        updatedUserData.put("email", binding.editEmail.getText().toString().trim());
        updatedUserData.put("unit", binding.editUnit.getText().toString().trim());
        updatedUserData.put("password", binding.editPassword.getText().toString().trim());
        // Add other fields as needed

        DocumentReference userDocumentRef = db.collection("users").document(userId);

        // Update the user document in Firestore
        userDocumentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Document exists, update the fields
                    userDocumentRef.update(updatedUserData)
                            .addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    Log.d("EditProfile", "User data updated");
                                } else {
                                    Log.e("EditProfile", "Error updating user data", updateTask.getException());
                                }
                            });
                } else {
                    // Document does not exist, create it
                    userDocumentRef.set(updatedUserData, SetOptions.merge())
                            .addOnCompleteListener(createTask -> {
                                if (createTask.isSuccessful()) {
                                    Log.d("EditProfile", "User document created");
                                } else {
                                    Log.e("EditProfile", "Error creating user document", createTask.getException());
                                }
                            });
                }
            } else {
                Log.e("EditProfile", "Error checking document existence", task.getException());
            }
        });
    }

    private void updateFirebaseAuthentication(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            // Update email
            user.updateEmail(email);

            // Update password only if it is not empty or null
            if (password != null && !password.isEmpty()) {
                user.updatePassword(password);
            }
        }
    }

    // You can use this method to start EditProfileActivity from other classes or fragments
    public static void start(Context context) {
        Intent intent = new Intent(context, Editprofile.class);
        context.startActivity(intent);
    }
}
