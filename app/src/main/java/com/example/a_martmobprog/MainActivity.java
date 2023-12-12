package com.example.a_martmobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a_martmobprog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("");

        ;

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

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
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Log.d("MenuItemClicked", "Item ID: " + itemId);

        if (itemId == R.id.action_bar_fav) {
            startActivity(new Intent(this, FavoriteActivity.class));
            return true; // Item is handled
        } else if (itemId == R.id.action_bar_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true; // Item is handled
        }

        return super.onOptionsItemSelected(item); // Item not handled
    }

}

