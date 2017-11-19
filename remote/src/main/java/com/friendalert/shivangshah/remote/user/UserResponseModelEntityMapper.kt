package com.friendalert.shivangshah.remote.user

import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.remote.EntityMapper
import com.friendalert.shivangshah.remote.notifications.NotificationModel
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserResponseModelEntityMapper @Inject constructor(): EntityMapper<UserResponseModel, UserResponseModelEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [BufferooEntity] model
     */
    override fun mapFromRemote(type: UserResponseModel): UserResponseModelEntity {
        return UserResponseModelEntity(type.customCode)
    }

}