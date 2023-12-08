package com.example.a_martmobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.a_martmobprog.databinding.ActivityMainBinding;
import com.example.a_martmobprog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.navView.setOnItemSelectedListener(item ->{

            if (item.getItemId() == R.id.navigation_home){
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.navigation_products){
                replaceFragment(new ProductFragment());
            }
            else if (item.getItemId() == R.id.navigation_profile){
                replaceFragment(new ProfileFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_view, fragment);
        fragmentTransaction.commit();
    }
}