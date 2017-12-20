package com.friendalert.shivangshah.data.notifications

import com.friendalert.shivangshah.data.notifications.source.NotificationDataStoreFactory
import com.friendalert.shivangshah.domain.notifications.NotificationRepository
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import javax.inject.Inject

class NotificationDataRepository @Inject constructor(private val factory: NotificationDataStoreFactory) :
        NotificationRepository {

    override fun getNotifications(userId: String): Single<NotificationResponseModel> {

        val dataStore = factory.retrieveDataStore(true)
        return dataStore.getNotifications(userId)

    }

    override fun markAsRead(notificationId: String): Single<Boolean> {

        val dataStore = factory.retrieveDataStore(false)
        return dataStore.markAsRead(notificationId)

    }

    override fun getReadNotifications(): Single<ArrayList<String>> {

        val dataStore = factory.retrieveDataStore(false)
        return dataStore.getReadNotifications()

    }

}