package com.friendalert.shivangshah.data.user

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserResponse
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserResponseMapper @Inject constructor(): Mapper<UserResponseModelEntity, UserResponse> {

    override fun mapToEntity(type: UserResponse): UserResponseModelEntity {
        return UserResponseModelEntity(type.customCode)
    }

    override fun mapFromEntity(type: UserResponseModelEntity): UserResponse {
        return UserResponse(type.customCode)
    }


}