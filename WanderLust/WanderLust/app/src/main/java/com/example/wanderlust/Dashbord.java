package com.example.wanderlust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.TimeKeyListener;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class Dashbord extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private CardView Train,Bus,Flight,Ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        Train=findViewById(R.id.train_cardview);
        Bus=findViewById(R.id.bus_cardview);
        Flight=findViewById(R.id.flight_cardview);
        Ship=findViewById(R.id.ship_cardview);

        Train.setOnClickListener(this);
        Bus.setOnClickListener(this);
        Flight.setOnClickListener(this);
        Ship.setOnClickListener(this);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.log_out:
            {
                finish();
                startActivity(new Intent(Dashbord.this,MainActivity.class));
                break;
            }
            case R.id.about_us:
            {
                startActivity(new Intent(Dashbord.this,AboutUs.class));
                break;
            }
            case R.id.profile:
            {
                startActivity(new Intent(Dashbord.this,Profile.class));
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bus_cardview:
            case R.id.train_cardview:
            case R.id.flight_cardview:
            case R.id.ship_cardview:
                startActivity(new Intent(Dashbord.this,TicketsBooking.class));
                break;

        }

    }
}