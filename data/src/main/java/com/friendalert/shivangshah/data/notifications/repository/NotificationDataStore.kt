package com.friendalert.shivangshah.data.notifications.repository

import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by shivangshah on 11/11/17.
 */
interface NotificationDataStore {

    fun getNotifications(userId: String): Single<NotificationResponseModel>

}