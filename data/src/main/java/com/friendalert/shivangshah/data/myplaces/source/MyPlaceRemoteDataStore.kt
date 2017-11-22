package com.friendalert.shivangshah.data.myplaces.source

import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntity
import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceDataStore
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implements MyPlaceDataStore
 * Calls MyPlaceRemote, which is implemented by MyPlaceRemoteImpl, to get myPlaces from the api
 */
class MyPlaceRemoteDataStore @Inject constructor(private val myPlaceRemote: MyPlaceRemote) :
        MyPlaceDataStore {

    override fun createMyPlace(myPlace: MyPlaceEntity): Single<MyPlaceResponseEntity> {
        return myPlaceRemote.createMyPlace(myPlace)
    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponseEntity> {
        return myPlaceRemote.deleteMyPlace(myPlaceId)
    }

    override fun getMyPlaces(userId: String): Single<MyPlacesResponseEntity> {
        return myPlaceRemote.getMyPlaces(userId)
    }


}