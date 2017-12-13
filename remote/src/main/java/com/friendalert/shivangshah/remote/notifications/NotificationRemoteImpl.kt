package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.data.notifications.repository.NotificationRemote
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class NotificationRemoteImpl @Inject constructor(private val notificationService: NotificationService) :
        NotificationRemote {

    override fun getNotifications(userId: String): Single<NotificationResponseModel> {
        return notificationService.getNotifications(userId)
    }

}