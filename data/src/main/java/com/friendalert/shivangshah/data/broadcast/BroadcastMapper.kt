package com.friendalert.shivangshah.data.broadcast

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.broadcast.Broadcast
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastMapper @Inject constructor(): Mapper<BroadcastEntity, Broadcast> {

    override fun mapToEntity(type: Broadcast): BroadcastEntity {
        return BroadcastEntity(type.userId, type.message, type.latitude, type.longitude)
    }

    override fun mapFromEntity(type: BroadcastEntity): Broadcast {
        return Broadcast(type.userId, type.message, type.latitude, type.longitude)
    }
}