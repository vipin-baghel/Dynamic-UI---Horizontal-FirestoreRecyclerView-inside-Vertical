package com.king.value;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //loading the default fragment
        loadFragment(new Fragment_home());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);

        if (isNetworkConnected() ){

        }else{
            Toast.makeText(this,"No Internet !!",Toast.LENGTH_LONG).show();
        }
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;

        }
        else{
            Toast.makeText(this,"Error loading Fragment",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new Fragment_home();
                break;

            case R.id.navigation_compare:
                fragment = new Fragment_compare();
                break;

            case R.id.navigation_notifications:
                fragment = new Fragment_notifications();
                break;
        }


        return loadFragment(fragment);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
