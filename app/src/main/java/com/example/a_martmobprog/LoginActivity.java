package com.example.a_martmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a_martmobprog.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        sessionManager = new SessionManager(getApplicationContext());

        // Check if "Remember Me" is enabled
        User rememberMeUser = sessionManager.getRememberMeDetails();
        if (rememberMeUser.isRememberMe()) {
            // Use the stored email and password to pre-fill the fields
            binding.loginEmail.setText(rememberMeUser.getEmail());
            binding.loginPass.setText(rememberMeUser.getPassword());
            binding.checkboxRememberMe.setChecked(true);
        }

        binding.btnmasuk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        binding.txtregistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        String email = binding.loginEmail.getText().toString();
        String pass = binding.loginPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {
                auth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                // If "Remember Me" is checked, save credentials
                                if (binding.checkboxRememberMe.isChecked()) {
                                    sessionManager.saveRememberMeDetails(email, pass, true);
                                } else {
                                    // If "Remember Me" is not checked, clear saved credentials
                                    sessionManager.saveRememberMeDetails("", "", false);
                                }

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                binding.loginPass.setError("Empty fields are not allowed");
            }
        } else if (email.isEmpty()) {
            binding.loginEmail.setError("Empty fields are not allowed");
        } else {
            binding.loginEmail.setError("Please enter correct email");
        }
    }
}
