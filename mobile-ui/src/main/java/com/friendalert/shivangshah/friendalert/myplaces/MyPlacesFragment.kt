package com.friendalert.shivangshah.friendalert.myplaces

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceViewData
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesFragment : Fragment(), MyPlacesContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    lateinit var autocompleteFragment : SupportPlaceAutocompleteFragment
    lateinit var googleMap : GoogleMap
    @Inject lateinit var mapper : MyPlaceMapper

    var hashMapMarker: HashMap<Int, Marker> = HashMap()

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_myplaces, container, false)

        autocompleteFragment = childFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as SupportPlaceAutocompleteFragment

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.

                var nickname = place.name
                var address = place.address;
                var city = place.locale;
                var latitude = place.latLng.latitude.toString()
                var longitude = place.latLng.longitude.toString()

                var myPlaceViewModelData = MyPlaceViewModelData(0,"", nickname.toString(), address.toString(), "Hackensack", "NJ", latitude, longitude, 1)

                myPlacePresenter.createMyPlace(mapper.mapFromViewModel(myPlaceViewModelData))
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status)
            }
        })


        return view;
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


    override fun showMyPlaces(myPlace: List<MyPlaceViewData>) {

        for(myPlaceObj in myPlace){

            val latlng = LatLng(myPlaceObj.latitude.toDouble(), myPlaceObj.longitude.toDouble())

            var markerOptions = MarkerOptions().position(latlng).title(myPlaceObj.nickname)
            val marker = googleMap.addMarker(markerOptions)
            hashMapMarker.put(myPlaceObj.base_camp_id, marker)

        }

        updateCamera()

    }

    override fun addMyPlace(myPlace: MyPlaceViewData) {

        val latlng = LatLng(myPlace.latitude.toDouble(), myPlace.longitude.toDouble())

        var markerOptions = MarkerOptions().position(latlng).title(myPlace.nickname)
        val marker = googleMap.addMarker(markerOptions)
        hashMapMarker.put(myPlace.base_camp_id, marker)

        updateCamera()

    }

    override fun deleteMyPlace(myPlace: MyPlaceViewData) {

        val marker = hashMapMarker[myPlace.base_camp_id]
        marker?.remove()
        hashMapMarker.remove(myPlace.base_camp_id)

        updateCamera()
    }

    private fun updateCamera(){

        var builder = LatLngBounds.Builder()
        val values = hashMapMarker.values
        for(marker in values){
            builder.include(marker.getPosition());
        }

        var bounds = builder.build();

        var padding = 100;
        var cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);

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