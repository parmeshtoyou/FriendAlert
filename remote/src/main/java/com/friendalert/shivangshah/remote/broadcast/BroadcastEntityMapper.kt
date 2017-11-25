package com.friendalert.shivangshah.remote.broadcast

import com.friendalert.shivangshah.data.broadcast.BroadcastEntity
import com.friendalert.shivangshah.remote.EntityMapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastEntityMapper @Inject constructor(): EntityMapper<BroadcastRequestModel,BroadcastEntity > {

    override fun mapFromRemote(type: BroadcastRequestModel): BroadcastEntity {
        return BroadcastEntity(type.userId, type.message, type.latitude, type.longitude)
    }

    fun mapToRemote(type: BroadcastEntity): BroadcastRequestModel {
        return BroadcastRequestModel(type.userId, type.message, type.latitude, type.longitude)
    }

//    override fun mapToEntity(type: Broadcast): BroadcastEntity {
//        return BroadcastEntity(type.userId, type.message, type.latitude, type.longitude)
//    }
//
//    override fun mapFromEntity(type: BroadcastEntity): Broadcast {
//        return Broadcast(type.userId, type.message, type.latitude, type.longitude)
//    }
}
