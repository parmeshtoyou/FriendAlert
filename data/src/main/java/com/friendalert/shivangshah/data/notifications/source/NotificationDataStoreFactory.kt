package com.friendalert.shivangshah.data.notifications.source

import com.friendalert.shivangshah.data.notifications.repository.NotificationDataStore
import javax.inject.Inject

/**
 * Create an instance of a BufferooDataStore
 */
open class NotificationDataStoreFactory @Inject constructor(
        private val notificationRemoteDataStore: NotificationRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): NotificationDataStore {
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): NotificationDataStore {
        return notificationRemoteDataStore
    }

}