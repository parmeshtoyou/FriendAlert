package com.friendalert.shivangshah.data.user.source

import com.friendalert.shivangshah.data.user.repository.UserCache
import com.friendalert.shivangshah.data.user.repository.UserDataStore
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
open class UserDataStoreFactory @Inject constructor(private val userCache: UserCache,
                                                    private val userCacheDataStore: UserCacheDataStore,
                                                    private val userRemoteDataStore: UserRemoteDataStore) {

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): UserDataStore {
        return userCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): UserDataStore {
        return userRemoteDataStore
    }

}