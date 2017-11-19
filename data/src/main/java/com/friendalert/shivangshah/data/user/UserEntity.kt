package com.friendalert.shivangshah.data.user

/**
 * Created by shivangshah on 11/17/17.
 */
class UserEntity (val userId: String,
                  val firstName: String,
                  val lastName: String,
                  val phoneNumber: String,
                  val newPushNotificationToken: String,
                  val oldPushNotificationToken: String,
                  val active: Int)