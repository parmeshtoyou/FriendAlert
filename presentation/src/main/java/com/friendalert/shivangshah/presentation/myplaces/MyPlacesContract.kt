package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlacesContract {

    interface View : BaseView<MyPlacesContract.Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showMyPlaces(myPlace: List<MyPlaceView>)

        fun hideMyPlaces()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveMyPlaces()

    }

}