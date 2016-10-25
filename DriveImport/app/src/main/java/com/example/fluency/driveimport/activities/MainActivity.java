package com.example.fluency.driveimport.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.fluency.driveimport.R;
import com.example.fluency.driveimport.adapters.ViewPagerAdapter;
import com.example.fluency.driveimport.async.AsyncResult;
import com.example.fluency.driveimport.async.DownloadDocTask;
import com.example.fluency.driveimport.fragments.CountriesFragment;
import com.example.fluency.driveimport.fragments.LanguagesFragment;
import com.example.fluency.driveimport.models.Language;
import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG_MAIN_ACTIVITY = "MainActivity";
    public static final String KEY_START_TAB = "keyStartTab";
    private static final int REQUEST_CODE_SELECT = 102;
    private static final int REQUEST_CODE_RESOLUTION = 103;
    private static final String FIREBASE_ROOT_URL = "https://project-5176964787746948725.firebaseio.com/";
    private static final String FIREBASE_ROOT_CHILD_LANGUAGES = "SingleLanguageList";

    private LanguagesFragment langFragment;
    private CountriesFragment countriesFragment;
    private GoogleApiClient googleApiClient;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private FloatingActionButton fabDrive;
    private FloatingActionButton fabFirebase;
    private int selectedTab;

    private DriveId mFileId;
    private Firebase firebaseRoot;
    private Firebase firebaseLanguagesRef;
    private Language[] languagesList;

    private RecyclerView countriesRecyclerView;
    private RecyclerView langRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        initViewPager();
        initFabs();
        buildGoogleApiClient();
        initFirebaseDataBase();
    }

    /*close connection to Google Play Services*/
    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.import_tab);
        fabDrive = (FloatingActionButton) findViewById(R.id.fabOpenDriveFile);
        fabFirebase = (FloatingActionButton) findViewById(R.id.fabImportFirebase);
        countriesRecyclerView = (RecyclerView) findViewById(R.id.countries_fragment_rv);
        langRecyclerView = (RecyclerView) findViewById(R.id.lang_fragment_rv);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.import_view_pager);
        setupPagerFragments();
    }

    private void setupPagerFragments() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        langFragment = new LanguagesFragment();
        countriesFragment = new CountriesFragment();
        viewPagerAdapter.addFragment(langFragment, "Languages");
        viewPagerAdapter.addFragment(countriesFragment, "Countries");

        viewPager.setAdapter(viewPagerAdapter);

        initTabLayout();
    }

    public void initTabLayout(){
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
        fabDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Selected Tab = " + selectedTab, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Log.d(TAG_MAIN_ACTIVITY, "onClick: googleClient = " + googleApiClient);
                googleApiClient.connect();
            }
        });

        fabFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueToFirebase();
            }
        });
    }

    private void initFirebaseDataBase(){
        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase(FIREBASE_ROOT_URL);
        firebaseLanguagesRef = firebaseRoot.child(FIREBASE_ROOT_CHILD_LANGUAGES);
    }

    private void setValueToFirebase() {
//        for (int i = 0; i < languageArrayList.size(); i++) {
////            firebaseLanguagesRef.child(languagesList[i].getName()).setValue(languagesList[i]);
//            firebaseLanguagesRef.child(languageArrayList.get(i).getName()).setValue(languageArrayList.get(i));
//            Log.i(TAG_MAIN_ACTIVITY, "setValueToFirebase: name" + languageArrayList.get(i).getName());
//        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG_MAIN_ACTIVITY, "in onConnected() - we're connected, let's do the work in the background...");
        // build an intent that we'll use to start the open file activity
        IntentSender intentSender = Drive.DriveApi
                .newOpenFileActivityBuilder()
                //these mimetypes enable these folders/files types to be selected
                .setMimeType(new String[] { DriveFolder.MIME_TYPE, "text/plain", "application/vnd.google-apps.spreadsheet"})
                .build(googleApiClient);
        try {
            startIntentSenderForResult(
                    intentSender, REQUEST_CODE_SELECT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.i(TAG_MAIN_ACTIVITY, "Unable to send intent", e);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        switch (i) {
            case 1:
                Log.i(TAG_MAIN_ACTIVITY, "Connection suspended - Cause: " + "Service disconnected");
                break;
            case 2:
                Log.i(TAG_MAIN_ACTIVITY, "Connection suspended - Cause: " + "Connection lost");
                break;
            default:
                Log.i(TAG_MAIN_ACTIVITY, "Connection suspended - Cause: " + "Unknown");
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG_MAIN_ACTIVITY, "Connection failed - result: " + connectionResult.toString());
        if (!connectionResult.hasResolution()) {
            // display error dialog
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        try {
            Log.i(TAG_MAIN_ACTIVITY, "trying to resolve the Connection failed error...");
            // tries to resolve the connection failure by trying to restart this activity
            connectionResult.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (IntentSender.SendIntentException e) {
            Log.i(TAG_MAIN_ACTIVITY, "Exception while starting resolution activity", e);
        }
    }

    /*build the google api client*/
    private void buildGoogleApiClient() {
        Log.i(TAG_MAIN_ACTIVITY, "Building the client");
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    /* receives returned result - called by the open file activity when it's exited
   by user pressing Select. This passes the request code, result code and data back
   which is received here*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG_MAIN_ACTIVITY, "in onActivityResult() - triggered on pressing Select");
        switch (requestCode) {
            case REQUEST_CODE_SELECT:
                if (resultCode == RESULT_OK) {
                    /*get the selected item's ID*/
                    Log.i(TAG_MAIN_ACTIVITY, "onActivityResult: request code SELECT ran" + data);

                    mFileId = (DriveId) data.getParcelableExtra(
                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);

                    Log.i(TAG_MAIN_ACTIVITY, "file id " + mFileId.getResourceId());
                    Log.i(TAG_MAIN_ACTIVITY, "Selected folder's ID: " + mFileId.encodeToString());
                    Log.i(TAG_MAIN_ACTIVITY, "Selected folder's Resource ID: " + mFileId.getResourceId());

                    new DownloadDocTask(new AsyncResult() {
                        @Override
                        public void onResult(JSONObject object) {
                            processJson(object);
                        }
                    }).execute("https://spreadsheets.google.com/tq?key=" + mFileId.getResourceId());
                }

                break;
            case REQUEST_CODE_RESOLUTION:
                if (resultCode == RESULT_OK) {
                    Log.i(TAG_MAIN_ACTIVITY, "in onActivityResult() - resolving connection, connecting...");
                    googleApiClient.connect();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void processJson(JSONObject object) {
        Log.i(TAG_MAIN_ACTIVITY, "processJson: " + object);
        if (selectedTab == 0) {
            langFragment.populateLanguageRecyclerView(object);
        } else {
            countriesFragment.populateCountriesRecyclerView(object);
        }
    }
}
