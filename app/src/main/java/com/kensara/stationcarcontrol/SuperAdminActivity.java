package com.kensara.stationcarcontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kensara.stationcarcontrol.Fragments.Administrateur.Create_Employee_Frag;
import com.kensara.stationcarcontrol.Fragments.Administrateur.Create_User_Fragment;
import com.kensara.stationcarcontrol.Fragments.Administrateur.List_employe_Frag;
import com.kensara.stationcarcontrol.Fragments.Administrateur.List_user_Fragment;
import com.kensara.stationcarcontrol.Fragments.BienvenueFragment;

public class SuperAdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Create_User_Fragment.CreateUserListenner,
        Create_Employee_Frag.CreateEmployeListener{
    //comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_admin, (Fragment) new BienvenueFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.super_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new BienvenueFragment();

        if (id == R.id.nav_add_pompiste){
            fragment = new Create_Employee_Frag();
        }else if (id == R.id.nav_create_User){
            fragment = new Create_User_Fragment();
        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_admin, fragment)
                .commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void userCreated() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_admin, (Fragment) new List_user_Fragment())
                .commit();

    }

    @Override
    public void onCreateEmploye() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_admin, (Fragment) new List_employe_Frag())
                .commit();
    }
}
