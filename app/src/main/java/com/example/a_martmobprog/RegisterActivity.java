package com.example.a_martmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a_martmobprog.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivityRegisterBinding binding;
    private boolean isPasswordMismatch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();

        auth = FirebaseAuth.getInstance();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void setListener() {
        binding.txtmasuk.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });

        binding.btnDaftar1.setOnClickListener(v -> {
            String emailregis = binding.regisEmail.getText().toString().trim();
            String password = binding.regisPass.getText().toString().trim();
            String confirmPassword = binding.ConfirmPass.getText().toString().trim();
            TextView passwordMatchText = binding.passwordMatchText;

            if (password.isEmpty()) {
                if (password.equals(confirmPassword)) {
                    isPasswordMismatch = false;
                    passwordMatchText.setText("Password sesuai");
                    passwordMatchText.setTextColor(getResources().getColor(R.color.black));
                } else {
                    isPasswordMismatch = true;
                    passwordMatchText.setText("Password tidak sesuai");
                    passwordMatchText.setTextColor(getResources().getColor(R.color.red));
                }
            } else {
                auth.createUserWithEmailAndPassword(emailregis, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Registration successful, now save additional user information
                                    String name = "John Doe"; // Change "John Doe" to the actual name
                                    saveUserInfoToFirestore(emailregis, name);

                                    // Pass data to ProfileFragment and start the ProfileFragment activity
                                    startProfileFragmentActivity(emailregis, name);

                                    Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("RegisterActivity", "Register Failed: " + task.getException().getMessage(), task.getException());
                                    Toast.makeText(RegisterActivity.this, "Register Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void saveUserInfoToFirestore(String email, String name) {
        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user object
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("name", name);

        // Add a new document with a generated ID
        db.collection("users").document(auth.getCurrentUser().getUid())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("RegisterActivity", "User information added to Firestore");
                        } else {
                            Log.e("RegisterActivity", "Error adding user information to Firestore", task.getException());
                        }
                    }
                });
    }

    private void startProfileFragmentActivity(String email, String name) {
        Intent intent = new Intent(RegisterActivity.this, ProfileFragment.class);
        intent.putExtra("emailKey", email);
        intent.putExtra("userNameKey", name);
        startActivity(intent);
    }
}
