package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.data.notifications.repository.NotificationRemote
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import javax.inject.Inject


class NotificationRemoteImpl @Inject constructor(private val notificationService: NotificationService) :
        NotificationRemote {

    override fun getNotifications(userId: String): Single<NotificationResponseModel> {
        return notificationService.getNotifications(userId)
    }

}