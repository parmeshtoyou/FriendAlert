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
import javax.inject.Inject


/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesFragment : Fragment(), MyPlacesContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, MyPlaceActionListener {

    lateinit var googleMap : GoogleMap
    lateinit var searchEditText : EditText

    var PLACE_AUTOCOMPLETE_REQUEST = 4000

    var hashMapMyPlace: HashMap<Int, MyPlaceViewModel> = HashMap()

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

                // TODO : Show create new myplace popup

                var myPlaceViewModelData = MyPlaceRequestModel(0,"", nickname.toString(), address.toString(), "Hackensack", "NJ", latitude, longitude, 1, "5")

                var actionMyPlaceFragment = ActionMyPlaceDialogFragment().newInstance(myPlaceViewModelData, true, this)
                actionMyPlaceFragment.show(activity!!.supportFragmentManager, "")

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

        hashMapMyPlace.entries

        var myPlace: MyPlaceModel? = null

        for(entry in hashMapMyPlace.entries){
            if(entry.value.marker == p0){
                myPlace = entry.value.myPlace
            }
        }

        var myPlaceRequestModel = MyPlaceRequestModel(myPlace!!.base_camp_id,myPlace.fk_user_id, myPlace.nickname, myPlace.address, myPlace.city, myPlace.state, myPlace.latitude, myPlace.longitude, myPlace.active, myPlace.radius)

        var actionMyPlaceFragment = ActionMyPlaceDialogFragment().newInstance(myPlaceRequestModel, false, this)
        actionMyPlaceFragment.show(activity!!.supportFragmentManager, "")

        return true
    }


    override fun showMyPlaces(myPlaces: List<MyPlaceModel>) {

        for(myPlaceObj in myPlaces){

            val latlng = LatLng(myPlaceObj.latitude.toDouble(), myPlaceObj.longitude.toDouble())

            var customBitmapDescriptorFactory = CustomBitmapDescriptorFactory.getHsvFromColor("#107F93")

            var markerOptions = MarkerOptions().position(latlng).title(myPlaceObj.nickname).icon(BitmapDescriptorFactory.defaultMarker(customBitmapDescriptorFactory[0]))
            val marker = googleMap.addMarker(markerOptions)

            var circleOptions = CircleOptions().center(latlng).radius(myPlaceObj.radius.toDouble() * 1609.34).fillColor(Color.TRANSPARENT).strokeColor(Color.parseColor("#107F93")).strokeWidth(10f);
            val circle = googleMap.addCircle(circleOptions)

            hashMapMyPlace.put(myPlaceObj.base_camp_id, MyPlaceViewModel(marker, circle, myPlaceObj))

        }

        updateCamera()

    }

    override fun addMyPlace(myPlace: MyPlaceModel) {

        val latlng = LatLng(myPlace.latitude.toDouble(), myPlace.longitude.toDouble())

        var markerOptions = MarkerOptions().position(latlng).title(myPlace.nickname).icon(BitmapDescriptorFactory.defaultMarker(189f))
        val marker = googleMap.addMarker(markerOptions)

        var circleOptions = CircleOptions().center(latlng).radius(myPlace.radius.toDouble() * 1609.34).fillColor(Color.TRANSPARENT).strokeColor(Color.parseColor("#107F93")).strokeWidth(10f);
        val circle = googleMap.addCircle(circleOptions)

        hashMapMyPlace.put(myPlace.base_camp_id, MyPlaceViewModel(marker, circle, myPlace))

        updateCamera()

    }

    override fun deleteMyPlace(myPlace: MyPlaceModel) {

        val marker = hashMapMyPlace[myPlace.base_camp_id]!!.marker
        marker?.remove()

        val circle = hashMapMyPlace[myPlace.base_camp_id]!!.circle
        circle?.remove()

        hashMapMyPlace.remove(myPlace.base_camp_id)

        updateCamera()
    }

    override fun editMyPlace(myPlace: MyPlaceModel) {

        // update viewmodel object with updated myplace object (updated nickname/radius)
        hashMapMyPlace[myPlace.base_camp_id]!!.myPlace = myPlace

        // remove old initials_circle from google map
        hashMapMyPlace[myPlace.base_camp_id]!!.circle.remove()

        // create new initials_circle with updated radius
        var circleOptions = CircleOptions().center(LatLng(myPlace.latitude.toDouble(), myPlace.longitude.toDouble())).radius(myPlace.radius.toDouble() * 1609.34).fillColor(Color.TRANSPARENT).strokeColor(Color.parseColor("#107F93")).strokeWidth(10f);

        // add new initials_circle to google map
        val circle = googleMap.addCircle(circleOptions)

        // update viewmodel object with new initials_circle
        hashMapMyPlace[myPlace.base_camp_id]!!.circle = circle

        updateCamera()

    }

    private fun updateCamera(){

        if(hashMapMyPlace.values.count() > 0){

            var builder = LatLngBounds.Builder()
            val values = hashMapMyPlace.values
            for(myPlaceValue in values){

                var targetNorthEast = SphericalUtil.computeOffset(myPlaceValue.circle.center, myPlaceValue.circle.radius * Math.sqrt(2.0), 45.0);
                var targetSouthWest = SphericalUtil.computeOffset(myPlaceValue.circle.center, myPlaceValue.circle.radius * Math.sqrt(2.0), 225.0);

                builder.include(targetNorthEast)
                builder.include(targetSouthWest)
            }

            var bounds = builder.build();

            var padding = 100;
            var cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.animateCamera(cu);

        }
    }

    override fun createMyPlacePressed(myPlace: MyPlaceRequestModel) {
        myPlacePresenter.createMyPlace(myPlace)
    }

    override fun editMyPlacePressed(myPlace: MyPlaceRequestModel) {
        myPlacePresenter.editMyPlace(myPlace)
    }

    override fun deleteMyPlacePressed(myPlace: MyPlaceRequestModel) {
        myPlacePresenter.deleteMyPlace(myPlace.base_camp_id)
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