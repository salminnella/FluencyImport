package com.example.fluency.driveimport.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fluency.driveimport.R;
import com.example.fluency.driveimport.adapters.ViewPagerAdapter;
import com.example.fluency.driveimport.fragments.CountriesFragment;
import com.example.fluency.driveimport.fragments.LanguagesFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String KEY_START_TAB = "keyStartTab";

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private FloatingActionButton fabDrive;
    private FloatingActionButton fabFirebase;
    private int selectedTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewPager();

        initFabs();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.import_view_pager);
        setupPagerFragments();
    }

    private void setupPagerFragments() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        LanguagesFragment langFragment = new LanguagesFragment();
        CountriesFragment countriesFragment = new CountriesFragment();
        viewPagerAdapter.addFragment(langFragment, "Languages");
        viewPagerAdapter.addFragment(countriesFragment, "Countries");

        viewPager.setAdapter(viewPagerAdapter);

        initTabLayout();
    }

    public void initTabLayout(){
        tabLayout = (TabLayout) findViewById(R.id.import_tab);

        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    selectedTab = tab.getPosition();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            setStartingTab();
        }

    }

    private void setStartingTab(){
        if(getIntent().getIntExtra(KEY_START_TAB,-1) != -1){
            tabLayout.getTabAt(getIntent().getIntExtra(KEY_START_TAB,-1)).select();
        }
    }


    private void initFabs() {
        fabDrive = (FloatingActionButton) findViewById(R.id.fabOpenDriveFile);
        fabDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Selected Tab = " + selectedTab, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fabFirebase = (FloatingActionButton) findViewById(R.id.fabImportFirebase);
        fabFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
