package com.friendalert.shivangshah.presentation.broadcast

import com.friendalert.shivangshah.domain.broadcast.Broadcast
import com.friendalert.shivangshah.presentation.Mapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastMapper @Inject constructor(): Mapper<BroadcastView, Broadcast> {

    override fun mapToView(type: Broadcast): BroadcastView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun mapFromView(type: BroadcastView): Broadcast{
        return Broadcast(type.userId, type.message, type.latitude, type.longitude)
    }


}