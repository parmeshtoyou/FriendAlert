package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.myplaces.source.MyPlaceDataStoreFactory
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Contains the MyPlaceDataStore Factory (generates either remote or cache MyPlaceDataStore) using retrieveDataStore
 * remote data store will call api
 * cache data store will call local db
 */
class MyPlaceDataRepository @Inject constructor(private val factory: MyPlaceDataStoreFactory) :
        MyPlaceRepository {

    override fun createMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {

        val dataStore = factory.retrieveDataStore()
        return dataStore.createMyPlace(myPlace)

    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponseModel> {

        val dataStore = factory.retrieveDataStore()
        return dataStore.deleteMyPlace(myPlaceId)

    }

    override fun getMyPlaces(userId: String): Single<MyPlacesResponseModel> {

        val dataStore = factory.retrieveDataStore()
        return dataStore.getMyPlaces(userId)

    }

    override fun editMyPlace(myPlace: MyPlaceRequestModel): Single<MyPlaceResponseModel> {

        val dataStore = factory.retrieveDataStore()
        return dataStore.editMyPlace(myPlace)

    }
}