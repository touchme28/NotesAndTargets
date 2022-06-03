package com.example.notesandtargets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.notesandtargets.fragments.NoteFragment;
import com.example.notesandtargets.fragments.TargetFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView BottomNavView;

    NoteFragment noteFragment = new NoteFragment();
    TargetFragment targetFragment = new TargetFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        BottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_note:
                        openFragment(noteFragment);
                        return true;
                    case R.id.nav_target:
                        openFragment(targetFragment);
                        return true;
                }
                return false;
            }


        });

        openFragment(noteFragment);
    }

    void initViews(){
        BottomNavView = findViewById(R.id.BottomNavView);
    }

    void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}