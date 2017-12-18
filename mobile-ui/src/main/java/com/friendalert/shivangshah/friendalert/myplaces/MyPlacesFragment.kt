package com.friendalert.shivangshah.friendalert.myplaces

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import dagger.android.support.AndroidSupportInjection
import java.util.ArrayList
import javax.inject.Inject


/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesFragment : Fragment(), MyPlacesContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    lateinit var googleMap : GoogleMap
    lateinit var searchButton : Button
    lateinit var searchEditText : EditText

    var PLACE_AUTOCOMPLETE_REQUEST = 4000

    var hashMapMarker: HashMap<Int, Marker> = HashMap()
    var hashMapCircle: HashMap<Int, Circle> = HashMap()

    @Inject lateinit var myPlacePresenter : MyPlacesContract.Presenter

    override fun setPresenter(presenter: MyPlacesContract.Presenter) {
        myPlacePresenter = presenter;
    }

    fun instantiate(@Nullable arguments: Bundle): MyPlacesFragment {
        val myPlaceFragment = MyPlacesFragment()
        myPlaceFragment.setArguments(arguments)
        return myPlaceFragment
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_myplaces, container, false)

        searchButton = view.findViewById(R.id.searchButton)
        searchEditText = view.findViewById(R.id.searchEditText)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        searchEditText.setOnClickListener { v ->
            try {
                var intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                .build(activity);
                activity!!.startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST);
            } catch (e: GooglePlayServicesRepairableException) {
                // TODO: Handle the error.
            } catch (e: GooglePlayServicesRepairableException) {
                // TODO: Handle the error.
            }
        }

        return view;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST) {
            if (resultCode == RESULT_OK) {
                var place = PlaceAutocomplete.getPlace(activity, data);
                Log.i(TAG, "Place: " + place.getName());

                var nickname = place.name
                var address = place.address;
                var city = place.locale;
                var latitude = place.latLng.latitude.toString()
                var longitude = place.latLng.longitude.toString()

                var myPlaceViewModelData = MyPlaceRequestModel(0,"", nickname.toString(), address.toString(), "Hackensack", "NJ", latitude, longitude, 1)

                myPlacePresenter.createMyPlace(myPlaceViewModelData)

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                var status = PlaceAutocomplete.getStatus(activity, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        googleMap.setOnMarkerClickListener(this)

        myPlacePresenter.start()
    }

    override fun onMarkerClick(p0: Marker?): Boolean {

        hashMapMarker.entries

        var myPlaceId = 0

        for(entry in hashMapMarker.entries){
            if(entry.value == p0){
                myPlaceId = entry.key
            }
        }

        myPlacePresenter.deleteMyPlace(myPlaceId)


        return true
    }


    override fun showMyPlaces(myPlaces: List<MyPlaceModel>) {

        for(myPlaceObj in myPlaces){

            val latlng = LatLng(myPlaceObj.latitude.toDouble(), myPlaceObj.longitude.toDouble())

            var markerOptions = MarkerOptions().position(latlng).title(myPlaceObj.nickname).icon(BitmapDescriptorFactory.defaultMarker(189f))
            val marker = googleMap.addMarker(markerOptions)
            hashMapMarker.put(myPlaceObj.base_camp_id, marker)

            myPlaceObj.radius = "5.0"
            var circleOptions = CircleOptions().center(latlng).radius(myPlaceObj.radius.toDouble() * 1609.34).fillColor(Color.GREEN).strokeColor(Color.GREEN).strokeWidth(2f);
            val circle = googleMap.addCircle(circleOptions)
            hashMapCircle.put(myPlaceObj.base_camp_id, circle)

        }

        updateCamera()

    }

    override fun addMyPlace(myPlace: MyPlaceModel) {

        val latlng = LatLng(myPlace.latitude.toDouble(), myPlace.longitude.toDouble())

        var markerOptions = MarkerOptions().position(latlng).title(myPlace.nickname).icon(BitmapDescriptorFactory.defaultMarker(189f))
        val marker = googleMap.addMarker(markerOptions)
        hashMapMarker.put(myPlace.base_camp_id, marker)

        myPlace.radius = "5.0"
        var circleOptions = CircleOptions().center(latlng).radius(myPlace.radius.toDouble() * 1609.34).fillColor(Color.GREEN).strokeColor(Color.GREEN).strokeWidth(8f);
        val circle = googleMap.addCircle(circleOptions)
        hashMapCircle.put(myPlace.base_camp_id, circle)

        updateCamera()

    }

    override fun deleteMyPlace(myPlace: MyPlaceModel) {

        val marker = hashMapMarker[myPlace.base_camp_id]
        marker?.remove()
        hashMapMarker.remove(myPlace.base_camp_id)

        val circle = hashMapCircle[myPlace.base_camp_id]
        circle?.remove()
        hashMapCircle.remove(myPlace.base_camp_id)

        updateCamera()
    }

    private fun updateCamera(){

        var builder = LatLngBounds.Builder()
        val values = hashMapCircle.values
        for(circle in values){
            //builder.include(marker.getPosition());

            var targetNorthEast = SphericalUtil.computeOffset(circle.center, circle.radius * Math.sqrt(2.0), 45.0);
            var targetSouthWest = SphericalUtil.computeOffset(circle.center, circle.radius * Math.sqrt(2.0), 225.0);

            builder.include(targetNorthEast)
            builder.include(targetSouthWest)
        }

        var bounds = builder.build();

        var padding = 100;
        var cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cu);


    }

    override fun showProgress() {
        Log.d("show","progress")
    }

    override fun hideProgress() {
        Log.d("hide","progress")
    }

    override fun hideMyPlaces() {
        Log.d("hide","myPlaces")
    }

    override fun showErrorState() {
        Log.d("show","error state")
    }

    override fun hideErrorState() {
        Log.d("hide","error state")
    }

    override fun showEmptyState() {
        Log.d("show","empty state")
    }

    override fun hideEmptyState() {
        Log.d("hide","empty state")
    }

    override fun onStop() {

        myPlacePresenter.stop()
        super.onStop()
    }
}