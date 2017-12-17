package com.friendalert.shivangshah.friendalert.broadcast


import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.provider.ContactsContract
import android.support.annotation.Nullable
import android.support.v13.app.FragmentCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.facebook.FacebookSdk.getApplicationContext
import com.friendalert.shivangshah.friendalert.DataLoadingListener
import com.friendalert.shivangshah.friendalert.PermissionUtils
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.broadcast.BroadcastContract
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class BroadcastFragment : Fragment(), BroadcastContract.View, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, FragmentCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback, LocationListener {

    lateinit var sendBroadcastButton : Button
    lateinit var noteEditText: EditText

    private val PLAY_SERVICES_REQUEST = 1000
    private val REQUEST_CHECK_SETTINGS = 2000

    private var mGoogleApiClient: GoogleApiClient? = null

    var permissions: ArrayList<String> = ArrayList()
    var permissionUtils: PermissionUtils? = null

    var dataLoadingListener: DataLoadingListener? = null

    var isPermissionGranted: Boolean = false

    lateinit var mLocationRequest: LocationRequest

    @Inject lateinit var broadcastPresenter : BroadcastContract.Presenter

    fun instantiate(@Nullable arguments: Bundle): BroadcastFragment {
        val broadcastFragment = BroadcastFragment()
        broadcastFragment.setArguments(arguments)
        return broadcastFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_broadcast, container, false)

        createLocationRequest()
        permissionUtils = PermissionUtils(context!!, this)

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        sendBroadcastButton = view.findViewById<Button>(R.id.sendBroadcastButton)
        sendBroadcastButton.setOnClickListener { v ->
            permissionUtils!!.check_permission(permissions,"Need GPS permission for getting your location",PLAY_SERVICES_REQUEST)
        }

        noteEditText = view.findViewById<EditText>(R.id.noteEditText)

        return view
    }

    override fun setPresenter(presenter: BroadcastContract.Presenter) {
        broadcastPresenter = presenter
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        dataLoadingListener = activity as DataLoadingListener
        super.onAttach(context)
    }

    override fun showSuccess() {

        dataLoadingListener!!.dataLoadingStop()
        Toast.makeText(getApplicationContext(),"Broadcast sent to nearby friends!",Toast.LENGTH_LONG).show();

    }

    override fun showFailure() {

        dataLoadingListener!!.dataLoadingStop()
        Toast.makeText(getApplicationContext(),"Broadcast failed to send. Please try again.",Toast.LENGTH_LONG).show();

    }

    override fun onLocationChanged(p0: Location?) {
        mGoogleApiClient!!.disconnect()

        broadcastPresenter.createBroadcast("", noteEditText.text.toString(), p0!!.latitude.toString(), p0!!.longitude.toString())
        dataLoadingListener!!.dataLoadingStart()
    }

    private fun getLocation() {

        if (isPermissionGranted) {

            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, this);
            } catch (e: SecurityException) {
                e.printStackTrace()
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val states = LocationSettingsStates.fromIntent(data)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> when (resultCode) {
                Activity.RESULT_OK ->
                    // All required changes were successfully made
                    getLocation()
                Activity.RESULT_CANCELED -> {
                }
                else -> {
                }
            }// The user was asked to change settings, but chose not to
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionUtils!!.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    override fun onConnected(p0: Bundle?) {
        getLocation();
    }

    override fun onConnectionSuspended(p0: Int) {

        mGoogleApiClient!!.connect();

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + p0.getErrorCode());
    }

    override fun PermissionGranted(request_code: Int) {
        Log.i("PERMISSION","GRANTED");
        isPermissionGranted=true;

        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }
    }

    override fun PartialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>) {
        Log.i("PERMISSION PARTIALLY","GRANTED");
    }

    override fun PermissionDenied(request_code: Int) {
        Log.i("PERMISSION","DENIED");
    }

    override fun NeverAskAgain(request_code: Int) {
        Log.i("PERMISSION","NEVER ASK AGAIN");
    }


    private fun checkPlayServices(): Boolean {

        val googleApiAvailability = GoogleApiAvailability.getInstance()

        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(activity)

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(activity, resultCode,
                        PLAY_SERVICES_REQUEST).show()
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show()
                activity?.finish()
            }
            return false
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        checkPlayServices()
    }

    fun buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = GoogleApiClient.Builder(context!!)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build()
        }
        mGoogleApiClient!!.connect()

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
        builder.setAlwaysShow(true) //this is the key ingredient

        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())

        result.setResultCallback(object : ResultCallback<LocationSettingsResult> {
            override fun onResult(result: LocationSettingsResult) {
                val status = result.status

                when (status.statusCode) {
                    LocationSettingsStatusCodes.SUCCESS -> {
                        getLocation()
                    }
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS)
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }// All location settings are satisfied. The client can initialize location
                // requests here.
                // Location settings are not satisfied. However, we have no way to fix the
                // settings so we won't show the dialog.
            }
        })
    }

    fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.setInterval(10000)
        mLocationRequest.setFastestInterval(5000)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }

    override fun onStop() {

        broadcastPresenter.stop()
        super.onStop()
    }

}// Required empty public constructor
