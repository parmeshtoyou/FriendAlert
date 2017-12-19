package com.friendalert.shivangshah.data.notifications.source

import com.friendalert.shivangshah.data.notifications.repository.NotificationDataStore
import com.friendalert.shivangshah.data.notifications.repository.NotificationLocal
import com.friendalert.shivangshah.data.notifications.repository.NotificationRemote
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/18/17.
 */
class NotificationLocalDataStore @Inject constructor(private val notificationLocal: NotificationLocal) :
        NotificationDataStore {

    override fun getReadNotifications(): Single<ArrayList<String>> {
        return notificationLocal.getReadNotifications()
    }

    override fun markAsRead(notificationId: String): Single<Boolean> {
        return notificationLocal.markAsRead(notificationId)
    }

    override fun getNotifications(userId: String): Single<NotificationResponseModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}