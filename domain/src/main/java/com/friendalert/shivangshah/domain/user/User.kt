package com.friendalert.shivangshah.domain.user

/**
 * Created by shivangshah on 11/17/17.
 */
class User (val userId: String,
            val firstName: String,
            val lastName: String,
            val phoneNumber: String,
            val newPushNotificationToken: String,
            val oldPushNotificationToken: String,
            val active: Int)