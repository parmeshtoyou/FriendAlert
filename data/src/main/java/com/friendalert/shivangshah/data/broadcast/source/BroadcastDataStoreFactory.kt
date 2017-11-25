package com.friendalert.shivangshah.data.broadcast.source

import com.friendalert.shivangshah.data.broadcast.repository.BroadcastDataStore
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceDataStore
import com.friendalert.shivangshah.data.myplaces.source.MyPlaceRemoteDataStore
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastDataStoreFactory @Inject constructor(
        private val broadcastRemoteDataStore: BroadcastRemoteDataStore){

    fun retrieveDataStore(): BroadcastDataStore {

        return retrieveRemoteDataStore()
    }

    fun retrieveRemoteDataStore(): BroadcastDataStore {
        return broadcastRemoteDataStore
    }

}