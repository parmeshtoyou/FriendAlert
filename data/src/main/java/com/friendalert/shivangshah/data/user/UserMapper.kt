package com.friendalert.shivangshah.data.user

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.friendalert.shivangshah.domain.notifications.Notification
import com.friendalert.shivangshah.domain.user.User
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
open class UserMapper @Inject constructor(): Mapper<UserEntity, User> {

    /**
     * Map a [UserEntity] instance to a [User] instance
     */
    override fun mapFromEntity(type: UserEntity): User {
        return User(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }


}