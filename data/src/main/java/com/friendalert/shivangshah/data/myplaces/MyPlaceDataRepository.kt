package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.myplaces.source.MyPlaceDataStoreFactory
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import io.reactivex.Single
import javax.inject.Inject

/**
 * Contains the MyPlaceDataStore Factory (generates either remote or cache MyPlaceDataStore) using retrieveDataStore
 * remote data store will call api
 * cache data store will call local db
 */
class MyPlaceDataRepository @Inject constructor(private val factory: MyPlaceDataStoreFactory,
                                                private val myPlacesMapper: MyPlacesMapper) :
        MyPlaceRepository {
    override fun getMyPlaces(userId: String): Single<MyPlaces> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getMyPlaces(userId)
                .map {
                    responseEntity: MyPlacesResponseEntity -> myPlacesMapper.mapFromEntity(responseEntity)
                }
    }
}