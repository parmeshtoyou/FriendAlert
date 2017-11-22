package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.myplaces.source.MyPlaceDataStoreFactory
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import com.friendalert.shivangshah.domain.myplaces.MyPlaceResponse
import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import io.reactivex.Single
import javax.inject.Inject

/**
 * Contains the MyPlaceDataStore Factory (generates either remote or cache MyPlaceDataStore) using retrieveDataStore
 * remote data store will call api
 * cache data store will call local db
 */
class MyPlaceDataRepository @Inject constructor(private val factory: MyPlaceDataStoreFactory,
                                                private val myPlacesMapper: MyPlacesMapper,
                                                private val myPlaceMapper: MyPlaceMapper,
                                                private val myPlaceResponseEntityMapper: MyPlaceResponseEntityMapper) :
        MyPlaceRepository {

    override fun createMyPlace(myPlace: MyPlace): Single<MyPlaceResponse> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.createMyPlace(myPlaceMapper.mapToEntity(myPlace)).map {
            myPlaceResponseEntity: MyPlaceResponseEntity ->
                myPlaceResponseEntityMapper.mapFromEntity(myPlaceResponseEntity)
        }
    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponse> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.deleteMyPlace(myPlaceId).map {
            myPlaceResponseEntity: MyPlaceResponseEntity ->
            myPlaceResponseEntityMapper.mapFromEntity(myPlaceResponseEntity)
        }
    }

    override fun getMyPlaces(userId: String): Single<MyPlaces> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getMyPlaces(userId)
                .map {
                    responseEntity: MyPlacesResponseEntity -> myPlacesMapper.mapFromEntity(responseEntity)
                }
    }
}