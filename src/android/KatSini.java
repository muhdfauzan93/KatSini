package com.location.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.location.Location;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.location.plugin.GPSListener;
import android.widget.Toast;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KatSini extends CordovaPlugin implements GPSListener {

    private final String TAG = "Cordova";

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private GPSLocation gpsLocation;
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        this.callbackContext = callbackContext;
        if (action.equals("currentLocation")) {
            gpsLocation = new GPSLocation(this.cordova.getActivity(), Hello.this);
            gpsLocation.startLocationUpdates();

            return true;

        } else {

            return false;

        }
    }

    @Override
    public void setLocation(double latitude, double longitude) {
        gpsLocation.stopLocationUpdates();
        JSONObject location = new JSONObject();
        try{
        location.put("latitude", latitude);
        location.put("longitude", longitude);
    } catch (JSONException e) {
            android.util.Log.e(TAG, "Error: "+e );

        }

        // ndroid.util.Log.e(TAG, "setLocation: "+latitude + "// longitude //" + longitude );
        callbackContext.success(location);
    }

    @Override
    public void onDestroy() {
        gpsLocation.stopLocationUpdates();
    }
}
