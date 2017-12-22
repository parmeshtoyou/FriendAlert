package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel
import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlacesContract {

    interface View : BaseView<MyPlacesContract.Presenter> {

        fun showMyPlaces(myPlace: List<MyPlaceModel>)

        fun showNoMyPlacesAvailable(message: String)

        fun addMyPlace(myPlace: MyPlaceModel)

        fun deleteMyPlace(myPlace: MyPlaceModel)

        fun editMyPlace(myPlace: MyPlaceModel)

    }

    interface Presenter : BasePresenter {

        fun retrieveMyPlaces()

        fun createMyPlace(myPlaceRequestModel: MyPlaceRequestModel)

        fun deleteMyPlace(myPlaceId: Int)

        fun editMyPlace(myPlaceRequestModel: MyPlaceRequestModel)

    }

}