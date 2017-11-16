package com.friendalert.shivangshah.data.myplaces.source

import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
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

    override fun getMyPlaces(): Single<List<MyPlaceEntity>> {
        return myPlaceRemote.getMyPlaces()
    }


}