package com.friendalert.shivangshah.cache.user

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.domain.user.User
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
open class UserMapper @Inject constructor(): Mapper<UserEntity, CachedUser> {

    /**
     * Map a [UserEntity] instance to a [User] instance
     */
    override fun mapFromEntity(type: UserEntity): CachedUser {
        return CachedUser(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: CachedUser): UserEntity {
        return UserEntity(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }


}