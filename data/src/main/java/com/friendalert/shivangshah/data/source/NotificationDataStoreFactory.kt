package com.friendalert.shivangshah.data.source

import com.friendalert.shivangshah.data.repository.NotificationCache
import com.friendalert.shivangshah.data.repository.NotificationDataStore
import javax.inject.Inject

/**
 * Create an instance of a BufferooDataStore
 */
open class NotificationDataStoreFactory @Inject constructor(
        private val notificationCache: NotificationCache,
        private val notificationCacheDataStore: NotificationCacheDataStore,
        private val notificationRemoteDataStore: NotificationRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): NotificationDataStore {
        if (notificationCache.isCached() && !notificationCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): NotificationDataStore {
        return notificationCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): NotificationDataStore {
        return notificationRemoteDataStore
    }

}