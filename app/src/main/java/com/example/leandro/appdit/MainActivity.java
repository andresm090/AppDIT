package com.example.leandro.appdit;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.Result;

import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ResultCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    protected ArrayList<Geofence> mGeofenceList;
    protected GoogleApiClient mGoogleApiClient;

    private PendingIntent mGeofenceRequestIntent;

    final int REQUEST_PERMISSION_GEO = 101;
    final int REQUEST_PERMISSION_CALL_PHONE = 1;
    final String permissionstr = Manifest.permission.ACCESS_FINE_LOCATION;
    final String callpermision = Manifest.permission.CALL_PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button btnMail = (Button) findViewById(R.id.btnMail);
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("mailto:informaticatw@ing.unp.edu.ar");
                Intent mail = new Intent(Intent.ACTION_SENDTO, uri);
                mail.putExtra(Intent.EXTRA_SUBJECT,
                        "Consulta");
                startActivity(mail);
            }
        });

        Button btnLlamar = (Button) findViewById(R.id.btnLlamar);
        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+542804428402"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        mGeofenceList = new ArrayList<Geofence>();

        int permissionCheck = ContextCompat.checkSelfPermission(this, permissionstr);



        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            Log.i("Main", "The value" + String.valueOf(permissionCheck));
            Log.i("Main", "The value" + String.valueOf(PackageManager.PERMISSION_GRANTED));

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionstr)) {

                //this.showPermissionDialog();
                ActivityCompat.requestPermissions(this, new String[]{permissionstr}, REQUEST_PERMISSION_GEO);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permissionstr}, REQUEST_PERMISSION_GEO);
            }

        } else {
            Log.i("Main", "The value" + String.valueOf(permissionCheck));
            Log.e("MAIN", "NO TENES PERMISOS");
            Log.i("Main", "The system value" + String.valueOf(PackageManager.PERMISSION_GRANTED));
        }

        permissionCheck = ContextCompat.checkSelfPermission(this, callpermision);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            Log.i("Main", "The value" + String.valueOf(permissionCheck));
            Log.i("Main", "The value" + String.valueOf(PackageManager.PERMISSION_GRANTED));

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, callpermision)) {

                //this.showPermissionDialog();
                ActivityCompat.requestPermissions(this, new String[]{callpermision}, REQUEST_PERMISSION_CALL_PHONE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{callpermision}, REQUEST_PERMISSION_CALL_PHONE);
            }

        } else {
            Log.i("Main", "The value" + String.valueOf(permissionCheck));
            Log.e("MAIN", "NO TENES PERMISOS");
            Log.i("Main", "The system value" + String.valueOf(PackageManager.PERMISSION_GRANTED));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_mis_materias) {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        } else {
            if (id == R.id.nav_mis_eventos) {
                Intent i = new Intent(this, CalendarActivity.class);
                startActivity(i);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        //if (mGoogleApiClient != null){
          //  if (mGoogleApiClient.isConnected()) {
            //    mGoogleApiClient.disconnect();
            //}
        //}
    }


    @Override
    public void onConnectionSuspended(int cause) {
        Log.i("Main", "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("Main", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions[0].equals(permissionstr) && grantResults[0] == 0) {

            populateGeofenceList();

            buildGoogleApiClient();

            mGoogleApiClient.connect();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        //builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        Intent intent = new Intent(this, Servicio.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onConnected(Bundle bundle) {

        mGeofenceRequestIntent = getGeofencePendingIntent();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.GeofencingApi.addGeofences(
                mGoogleApiClient,
                getGeofencingRequest(),
                mGeofenceRequestIntent
        );


    }

    @Override
    public void onResult(Result result) {
        Log.e("Main",result.toString());
        //getApplicationContext().startService(intent);
    }


    public void populateGeofenceList() {
        mGeofenceList.add(new Geofence.Builder()
                //Identifica CASA LEANDRO
                .setRequestId("1")
                .setCircularRegion(-43.275458, -65.309690,100) //coords de casa
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(150000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .build());

        mGeofenceList.add(new Geofence.Builder()
                //Identifica el DIT
                .setRequestId("2")
                .setCircularRegion(-43.257369, -65.307699, 100)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(150000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .build());

        mGeofenceList.add(new Geofence.Builder()
                //Identifica el edificio de aulas
                .setRequestId("3")
                .setCircularRegion(-43.250092, -65.308152, 100)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(150000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .build());

    }

}
