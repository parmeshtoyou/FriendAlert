package com.friendalert.shivangshah.data.notifications

import com.friendalert.shivangshah.data.notifications.source.NotificationDataStoreFactory
import com.friendalert.shivangshah.domain.notifications.NotificationRepository
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class NotificationDataRepository @Inject constructor(private val factory: NotificationDataStoreFactory) :
        NotificationRepository {

    override fun getNotifications(userId: String): Single<NotificationResponseModel> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getNotifications(userId)
    }

}