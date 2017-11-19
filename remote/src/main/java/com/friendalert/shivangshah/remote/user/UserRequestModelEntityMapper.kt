package com.friendalert.shivangshah.remote.user

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.domain.user.User
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */

open class UserRequestModelEntityMapper @Inject constructor(): Mapper<UserEntity, UserRequestModel> {

    /**
     * Map a [UserEntity] instance to a [User] instance
     */
    override fun mapFromEntity(type: UserEntity): UserRequestModel {
        return UserRequestModel(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: UserRequestModel): UserEntity {
        return UserEntity(type.userId, type.firstName, type.lastName, type.newPushNotificationToken, type.oldPushNotificationToken, type.phoneNumber, type.active)
    }


}