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
        return UserRequestModel(Body(type.userId, type.firstName, type.lastName, type.phoneNumber, type.newPushNotificationToken, type.oldPushNotificationToken, type.active)
        )
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: UserRequestModel): UserEntity {
        return UserEntity(type.body.userId.toString(), type.body.firstName, type.body.lastName, type.body.phoneNumber, type.body.pushNotificationToken, "" , type.body.active)
    }


}