package com.friendalert.shivangshah.data.myplaces.repository

import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntity
import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import io.reactivex.Single

/**
 * Interface for Data Store
 * Implemented by MyPlaceRemoteDataStore and MyPlaceCacheDataStore
 */
interface MyPlaceDataStore {

    fun getMyPlaces(userId: String): Single<MyPlacesResponseEntity>

    fun createMyPlace(myPlace: MyPlaceEntity) : Single<MyPlaceResponseEntity>

    fun deleteMyPlace(myPlaceId: Int) : Single<MyPlaceResponseEntity>
}