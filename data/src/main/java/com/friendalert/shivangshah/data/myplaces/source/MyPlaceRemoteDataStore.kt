package com.friendalert.shivangshah.data.myplaces.source

import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceDataStore
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implements MyPlaceDataStore
 * Calls MyPlaceRemote, which is implemented by MyPlaceRemoteImpl, to get myPlaces from the api
 */
class MyPlaceRemoteDataStore @Inject constructor(private val myPlaceRemote: MyPlaceRemote) :
        MyPlaceDataStore {

    override fun createMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {
        return myPlaceRemote.createMyPlace(myPlace)
    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponseModel> {
        return myPlaceRemote.deleteMyPlace(myPlaceId)
    }

    override fun getMyPlaces(userId: String): Single<MyPlacesResponseModel> {
        return myPlaceRemote.getMyPlaces(userId)
    }

    override fun editMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {
        return myPlaceRemote.editMyPlace(myPlace)
    }

}