package com.friendalert.shivangshah.friendalert.notifications

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import org.w3c.dom.Text


class NotificationDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var toolbar : Toolbar? = null

    lateinit var googleMap : GoogleMap

    private var timestampTextView : TextView? = null
    private var messageTextView : TextView? = null
    private var callTextView : TextView? = null
    private var phoneTextView : TextView? = null
    private var locationTextView : TextView? = null

    private var notification : NotificationModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.getWindow()
            val background = this.getResources().getDrawable(R.drawable.main_gradient)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent))
            window.setBackgroundDrawable(background)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)

        val intent = intent
        var notificationJson = intent.getStringExtra("Notification")

        notification = Gson().fromJson(notificationJson, NotificationModel::class.java)

        toolbar = findViewById<Toolbar>(R.id.appToolbar)
        setSupportActionBar(toolbar)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        timestampTextView = findViewById<TextView>(R.id.timestampTextView)
        messageTextView = findViewById<TextView>(R.id.messageTextView)
        callTextView = findViewById<TextView>(R.id.callTextView)
        phoneTextView = findViewById<TextView>(R.id.phoneTextView)
        locationTextView = findViewById<TextView>(R.id.locationTextView)

        getSupportActionBar()!!.setHomeButtonEnabled(true);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        setupUI()

    }

    override fun onMapReady(p0: GoogleMap?) {

        googleMap = p0!!

        var markerOptions = MarkerOptions().position(LatLng(notification!!.latitude.toDouble(), notification!!.longitude.toDouble())).title("").icon(BitmapDescriptorFactory.defaultMarker(189f))
        googleMap.addMarker(markerOptions)

        var cu = CameraUpdateFactory.newLatLngZoom(LatLng(notification!!.latitude.toDouble(), notification!!.longitude.toDouble()), 10f)
        googleMap.animateCamera(cu);
        googleMap.setMaxZoomPreference(10f)

    }

    fun setupUI(){

        supportActionBar!!.title = notification!!.first_name + " " + notification!!.last_name

        timestampTextView!!.text = notification!!.timestamp
        messageTextView!!.text = notification!!.message
        callTextView!!.text = "Call " + notification!!.first_name
        phoneTextView!!.text = notification!!.phone_number
        locationTextView!!.text = "Location"

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){

            android.R.id.home -> finish()

        }

        return super.onOptionsItemSelected(item)
    }
}
