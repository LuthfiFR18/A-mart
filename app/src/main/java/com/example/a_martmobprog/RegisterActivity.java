package com.example.a_martmobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a_martmobprog.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    private void setListener(){
        binding.txtmasuk.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });
        binding.btnDaftar1.setOnClickListener(v -> {
            //register();

            String emailregis = binding.regisEmail.getText().toString().trim();
            String password = binding.regisPass.getText().toString().trim();
            String confirmPassword = binding.ConfirmPass.getText().toString().trim();
            TextView passwordMatchText = binding.passwordMatchText;


            if (password.isEmpty() ) {
                if (password.equals(confirmPassword)) {

                    isPasswordMismatch = false;
                    passwordMatchText.setText("Password sesuai");
                    passwordMatchText.setTextColor(getResources().getColor(R.color.black));

                } else {
                    isPasswordMismatch = true;
                    passwordMatchText.setText("Password tidak sesuai");
                    passwordMatchText.setTextColor(getResources().getColor(R.color.red));


                }
            }

            else {
                auth.createUserWithEmailAndPassword(emailregis,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Log.e("RegisterActivity", "Register Failed: " + task.getException().getMessage(), task.getException());
                            Toast.makeText(RegisterActivity.this, "Register Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


}