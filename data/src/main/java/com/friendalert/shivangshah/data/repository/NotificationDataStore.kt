package com.friendalert.shivangshah.data.repository

import com.friendalert.shivangshah.data.model.NotificationEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by shivangshah on 11/11/17.
 */
interface NotificationDataStore {

    fun clearNotifications(): Completable

    fun saveNotifications(notifications: List<NotificationEntity>): Completable

    fun getNotifications(): Single<List<NotificationEntity>>

}