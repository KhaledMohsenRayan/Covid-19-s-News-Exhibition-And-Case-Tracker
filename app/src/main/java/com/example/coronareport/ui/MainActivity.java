package com.example.coronareport.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.coronareport.R;
import com.example.coronareport.databinding.MainActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        NavController navController = Navigation.findNavController(this, R.id.NavHostFragment);
        BottomNavigationView bottomNav = binding.bottomNavBar;
        NavigationUI.setupWithNavController(bottomNav, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getLabel().equals("SplashFragment")){
                    binding.bottomNavBar.setVisibility(View.GONE);
                }else {
                    binding.bottomNavBar.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}