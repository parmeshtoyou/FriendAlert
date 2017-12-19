package com.friendalert.shivangshah.friendalert.notifications

import com.friendalert.shivangshah.model.notifications.response.NotificationModel

/**
 * Created by shivangshah on 12/18/17.
 */
interface NotificationClickedListener {

    fun notificationClicked(position: Int, notification: NotificationModel?)

}