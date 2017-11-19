package com.friendalert.shivangshah.friendalert.firebase

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import android.preference.PreferenceManager
import android.content.SharedPreferences



class FriendAlertFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("Tag", "Refreshed token: " + refreshedToken!!)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var newPushNotificationToken = preferences.getString("newPushNotificationToken", "")
        var oldPushNotificationToken = preferences.getString("oldPushNotificationToken", "")

        // No token available in shared preferences, save token in shared preferences
        if (newPushNotificationToken.equals("", ignoreCase = true)) {
            val editor = preferences.edit()
            editor.putString("newPushNotificationToken", refreshedToken)
            editor.apply()
        }
        // Token is available in shared preferences, update it in sharedpreferences, and call api to update on server for this user
        else{
            oldPushNotificationToken = newPushNotificationToken;
            val editor = preferences.edit()
            editor.putString("newPushNotificationToken", refreshedToken)
            editor.putString("oldPushNotificationToken", oldPushNotificationToken)
            editor.apply()

            //sendTokenToServer(refreshedToken, oldToken, userId)
        }
    }

}