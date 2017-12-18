package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlaceRemoteImpl @Inject constructor(private val myPlaceService: MyPlaceService) :
        MyPlaceRemote {

    override fun createMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {

        return myPlaceService.createMyPlace(myPlace)
    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponseModel> {

        return myPlaceService.deleteMyPlace(myPlaceId)

    }

    override fun getMyPlaces(userId:String): Single<MyPlacesResponseModel> {

        return myPlaceService.getMyPlaces(userId)

    }

    override fun editMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {

        return myPlaceService.editMyPlace(myPlace)

    }
}