package com.friendalert.shivangshah.data.broadcast

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.broadcast.Broadcast
import com.friendalert.shivangshah.domain.broadcast.CreateBroadcastResponse
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class CreateBroadcastResponseEntityMapper @Inject constructor(): Mapper<CreateBroadcastResponseEntity, CreateBroadcastResponse> {

    override fun mapFromEntity(type: CreateBroadcastResponseEntity): CreateBroadcastResponse {
        return CreateBroadcastResponse(type.customCode)
    }

    override fun mapToEntity(type: CreateBroadcastResponse): CreateBroadcastResponseEntity {
        return CreateBroadcastResponseEntity(type.customCode)
    }

}