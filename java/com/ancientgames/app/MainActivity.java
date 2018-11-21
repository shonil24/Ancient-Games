package com.ancientgames.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar ;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //set title text color of toolbar i.e ancient game to white color//

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);// a layout for nav items//
        navigationView = (NavigationView) findViewById(R.id.navigation_view);// a view on layout to be placed and viewed//


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        //actionbar 2nd arg Drawer obj ,3rd arg toolbar obj
           //4th,4th arg for opening and closing nav drawer
        //This adds 3 horizontal lines button on main from where u toggle nav drawer//
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Goto Code Menu -> Overrride methods -> search onbackpressed -> click ok {Pressing back button executes code within this function}
        //This as clicking back button even if nav drawer is open exits app directly.Instead only nav should be close 1st and  then Again pressing
        //backbutton should close the app.Making this so if nav drawer is open 1st back button click closes nav drawer and then 2nd click closes app

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) { //if nav drawer open from left(gravity start means from left)

            drawerLayout.closeDrawer(GravityCompat.START); //if open close it to left side of screen
        }
        else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //when user selects any of nav drawer items code below inside itemselected is executed
        int id = item.getItemId(); //returns item id of item selected by user

        switch (id) {
            case R.id.schedule_id:
                Toast.makeText(getApplicationContext(),"schedule",Toast.LENGTH_LONG).show();
                break;
            case R.id.points_id:
                Toast.makeText(getApplicationContext(),"points",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,PointsTable.class);
                startActivity(i);
                break;
            case R.id.history_id:
                Toast.makeText(getApplicationContext(),"history",Toast.LENGTH_LONG).show();
                break;
            case R.id.rules_id:
                Toast.makeText(getApplicationContext(),"rules",Toast.LENGTH_LONG).show();
                break;
            case R.id.players_id:
                Toast.makeText(getApplicationContext(),"players",Toast.LENGTH_LONG).show();
                Intent play = new Intent(MainActivity.this,Players.class);
                startActivity(play);
                break;
            case R.id.about_id:
                Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                Toast.makeText(getApplicationContext(),"user logged out",Toast.LENGTH_LONG).show();
                mAuth = FirebaseAuth.getInstance();
                //gets instant connect with firebase created obj
                mAuth.signOut();  //signout
                //startActivity(new Intent(MainActivity.this,LoginActivity.class)); (shortcut method)
                Intent inten = new Intent(MainActivity.this,LoginActivity.class);
                // Closing all the Activities in stack .bcoz even if u logout and pressed back button still mainactivity is displayed
                //we want after logout login screen must be displayed and after that pressing back should exit app instead showing last activity used e.g mainactivity
                inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //clears top activity from stack
                inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); //clears all the task

                // Add new Flag to start new Activity which will be launching activity (login activity)
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
                finish(); //finish logout instance so this will end logout and clear stack part
                break;
                //Toast displays the text written in quotes of makeText for each selection of item
                //getApplicationContext() = gets the current activity which is running or upon the user is
            //1st 4 are in one group so when any of 4 selected and it still remains selected even after after selecting
            //items other than this group i.e e.g share ,setings are of other group or submenu
        }
        //Close drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START); //(gravity start) means nav drawer close to left side of screen

        return true;
    }
}
