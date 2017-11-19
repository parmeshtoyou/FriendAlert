package com.friendalert.shivangshah.cache.user

import com.friendalert.shivangshah.cache.db.EntityMapper
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserResponseModelEntityMapper @Inject constructor(): EntityMapper<UserResponseModel, UserResponseModelEntity> {

    override fun mapFromCached(type: UserResponseModel): UserResponseModelEntity {
        return UserResponseModelEntity(type.customCode)
    }

    override fun mapToCached(type: UserResponseModelEntity): UserResponseModel {
        return UserResponseModel(type.customCode)
    }

}