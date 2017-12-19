package com.friendalert.shivangshah.cache.notifications

import com.friendalert.shivangshah.cache.PreferencesHelper
import com.friendalert.shivangshah.data.notifications.repository.NotificationLocal
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/18/17.
 */
class NotificationsLocalImpl @Inject constructor(private val preferencesHelper : PreferencesHelper) : NotificationLocal{


    override fun getReadNotifications(): Single<ArrayList<String>> {

        val gson = Gson()

        var readJsonString : String = preferencesHelper.getSharedPreferences().getString("readNotificationIds", "")

        if(readJsonString == ""){

            var readList = ArrayList<String>()
            return Single.just(readList)

        }else{

            return Single.just(gson.fromJson<ArrayList<String>>(readJsonString, String::class.java))

        }

    }

    override fun markAsRead(notificationId: String): Single<Boolean> {

        val gson = Gson()
        var readJsonString : String = preferencesHelper.getSharedPreferences().getString("readNotificationIds", "")

        var readList = ArrayList<String>()

        if(readJsonString != "") {

            readList = gson.fromJson<ArrayList<String>>(readJsonString, String::class.java)

        }

        if(!readList.contains(notificationId)){
            readList.add(notificationId)
        }

        readJsonString = gson.toJson(readList)
        preferencesHelper.getSharedPreferences().edit().putString("readNotificationIds", readJsonString).commit()

        return Single.just(true)

    }

}