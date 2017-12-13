package com.friendalert.shivangshah.data.friends.source

import com.friendalert.shivangshah.data.friends.repository.FriendsDataStore
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsDataStoreFactory @Inject constructor(
        private val friendsRemoteDataStore: FriendsRemoteDataStore,
        private val contactsLocalDataStore: ContactsLocalDataStore){

    fun retrieveDataStore(local:Boolean): FriendsDataStore {

        if(local)
            return retrieveLocalDataStore()
        else
            return retrieveRemoteDataStore()
    }

    fun retrieveRemoteDataStore(): FriendsDataStore {
        return friendsRemoteDataStore
    }

    fun retrieveLocalDataStore(): FriendsDataStore {
        return contactsLocalDataStore
    }

}