package com.friendalert.shivangshah.data.notifications.source

import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.friendalert.shivangshah.data.notifications.repository.NotificationCache
import com.friendalert.shivangshah.data.notifications.repository.NotificationDataStore
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
        return notificationCache.clearNotifications()
    }

    override fun saveNotifications(notifications: List<NotificationEntity>): Completable {
        return notificationCache.saveNotifications(notifications)
                .doOnComplete {
                    notificationCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    override fun getNotifications(): Single<List<NotificationEntity>> {
        return notificationCache.getNotifications()
    }


}