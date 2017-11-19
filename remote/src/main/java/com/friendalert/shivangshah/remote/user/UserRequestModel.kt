package com.friendalert.shivangshah.remote.user

/**
 * Created by shivangshah on 11/17/17.
 */
class UserRequestModel (val userId: String,
                        val firstName: String,
                        val lastName: String,
                        val phoneNumber: String,
                        val newPushNotificationToken: String,
                        val oldPushNotificationToken: String,
                        val active: Int)