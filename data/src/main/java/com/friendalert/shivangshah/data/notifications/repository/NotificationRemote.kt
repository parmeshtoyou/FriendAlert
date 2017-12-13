package com.friendalert.shivangshah.data.notifications.repository

import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface NotificationRemote {

    /**
     * Retrieve a list of Notifications, from the service
     */
    fun getNotifications(userId: String): Single<NotificationResponseModel>

}