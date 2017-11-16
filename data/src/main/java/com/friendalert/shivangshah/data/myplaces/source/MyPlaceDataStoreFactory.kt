package com.friendalert.shivangshah.data.myplaces.source

import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceDataStore
import javax.inject.Inject

/**
 * Contains remote and cache MyPlace data stores and appropriately returns either one
 */
class MyPlaceDataStoreFactory @Inject constructor(
        private val myPlaceRemoteDataStore: MyPlaceRemoteDataStore){

    fun retrieveDataStore(): MyPlaceDataStore {

        return retrieveRemoteDataStore()
    }

    fun retrieveRemoteDataStore(): MyPlaceDataStore {
        return myPlaceRemoteDataStore
    }

}