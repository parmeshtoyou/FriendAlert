package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel
import com.friendalert.shivangshah.model.notifications.response.NotificationModel

/**
 * Created by shivangshah on 12/18/17.
 */
class NotificationPresentationModel {

    private var notifications = ArrayList<NotificationModel>()
    private var toBeMarkedAsReadNotification : NotificationModel? = null

    fun setNotifications(notifications: ArrayList<NotificationModel>) {
        this.notifications = notifications
    }

    fun getNotifications() : ArrayList<NotificationModel>{
        return this.notifications
    }

    fun setToBeMarkedAsRead(notificationModel: NotificationModel){
        toBeMarkedAsReadNotification = notificationModel
    }

    fun markNotificationAsRead(){
        for (notification in this.notifications){
            if(notification.broadcast_id == toBeMarkedAsReadNotification!!.broadcast_id){
                notification.isRead = true
                break
            }
        }
    }
}