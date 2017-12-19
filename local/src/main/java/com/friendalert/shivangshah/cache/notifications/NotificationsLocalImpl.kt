package com.friendalert.shivangshah.cache.notifications

import com.friendalert.shivangshah.cache.PreferencesHelper
import com.friendalert.shivangshah.data.notifications.repository.NotificationLocal
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/18/17.
 */
class NotificationsLocalImpl @Inject constructor(private val preferencesHelper : PreferencesHelper) : NotificationLocal{


    override fun getReadNotifications(): Single<ArrayList<String>> {

        var set = preferencesHelper.getSharedPreferences().getStringSet("readNotificationIdsSet", HashSet<String>())

        return Single.just(ArrayList<String>(set))

    }

    override fun markAsRead(notificationId: String): Single<Boolean> {

        var set = preferencesHelper.getSharedPreferences().getStringSet("readNotificationIdsSet", HashSet<String>())

        if(!set.contains(notificationId)){

            set.add(notificationId)
            preferencesHelper.getSharedPreferences().edit().putStringSet("readNotificationIdsSet", set).commit()

        }

        return Single.just(true)

    }

}