package com.friendalert.shivangshah.data.myplaces.repository

import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import io.reactivex.Single

/**
 * Interface for Data Store
 * Implemented by MyPlaceRemoteDataStore and MyPlaceCacheDataStore
 */
interface MyPlaceDataStore {

    fun getMyPlaces(userId: String): Single<MyPlacesResponseEntity>

}