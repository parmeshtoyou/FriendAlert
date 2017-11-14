package com.friendalert.shivangshah.data

import com.friendalert.shivangshah.data.mapper.NotificationMapper
import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.data.source.NotificationDataStoreFactory
import com.friendalert.shivangshah.domain.model.Notification
import com.friendalert.shivangshah.domain.repository.NotificationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class NotificationDataRepository @Inject constructor(private val factory: NotificationDataStoreFactory,
                                                 private val notificationMapper: NotificationMapper) :
        NotificationRepository {
    override fun clearNotifications(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveNotifications(notifications: List<Notification>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotifications(): Single<List<Notification>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveNotificationEntities(notifications: List<NotificationEntity>): Completable {
        return factory.retrieveCacheDataStore().saveNotifications(notifications)
    }

}