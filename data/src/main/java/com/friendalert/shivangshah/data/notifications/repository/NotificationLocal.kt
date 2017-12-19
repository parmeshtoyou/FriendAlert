package com.friendalert.shivangshah.data.notifications.repository

import io.reactivex.Single

/**
 * Created by shivangshah on 12/18/17.
 */
interface NotificationLocal {

    fun markAsRead(notificationId: String) : Single<Boolean>

    fun getReadNotifications(): Single<ArrayList<String>>
}