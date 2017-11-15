package com.friendalert.shivangshah.data.source

import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.data.repository.NotificationCache
import com.friendalert.shivangshah.data.repository.NotificationDataStore
import com.friendalert.shivangshah.data.repository.NotificationRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class NotificationRemoteDataStore @Inject constructor(private val notificationRemote: NotificationRemote) :
        NotificationDataStore {

    override fun clearNotifications(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveNotifications(notifications: List<NotificationEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getNotifications(): Single<List<NotificationEntity>> {
        return notificationRemote.getNotifications()
    }

}