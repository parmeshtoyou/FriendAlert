package com.friendalert.shivangshah.data.source

import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.data.repository.NotificationCache
import com.friendalert.shivangshah.data.repository.NotificationDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class NotificationCacheDataStore @Inject constructor(private val notificationCache: NotificationCache) :
        NotificationDataStore {

    override fun clearNotifications(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveNotifications(notifications: List<NotificationEntity>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotifications(): Single<List<NotificationEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}