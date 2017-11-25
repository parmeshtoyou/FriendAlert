package com.friendalert.shivangshah.remote.broadcast

import com.friendalert.shivangshah.data.broadcast.CreateBroadcastResponseEntity
import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.friendalert.shivangshah.remote.EntityMapper
import com.friendalert.shivangshah.remote.notifications.NotificationModel
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastResponseModelEntityMapper @Inject constructor(): EntityMapper<BroadcastResponseModel, CreateBroadcastResponseEntity> {

    override fun mapFromRemote(type: BroadcastResponseModel): CreateBroadcastResponseEntity {
        return CreateBroadcastResponseEntity(type.customCode)
    }

}