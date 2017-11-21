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
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceView
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener



/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesFragment : Fragment(), MyPlacesContract.View {

    lateinit var autocompleteFragment : SupportPlaceAutocompleteFragment

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

    override fun onStart() {
        super.onStart()

        myPlacePresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_myplaces, container, false)

        autocompleteFragment = childFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as SupportPlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i("", "Place: " + place.name)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status)
            }
        })


        return view;
    }

    override fun showProgress() {
        Log.d("show","progress")
    }

    override fun hideProgress() {
        Log.d("hide","progress")
    }

    override fun showMyPlaces(myPlace: List<MyPlaceView>) {
        Log.d("show","my places")
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
}