package com.friendalert.shivangshah.domain.notifications

import com.friendalert.shivangshah.domain.notifications.Notification
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface NotificationRepository {

    fun clearNotifications(): Completable

    fun saveNotifications(notifications: List<Notification>): Completable

    fun getNotifications(): Single<List<Notification>>

}