package com.friendalert.shivangshah.data.broadcast.repository

import com.friendalert.shivangshah.data.broadcast.BroadcastEntity
import com.friendalert.shivangshah.data.broadcast.CreateBroadcastResponseEntity
import io.reactivex.Single

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastRemote {

    fun createBroadcast(broadcast : BroadcastEntity) : Single<CreateBroadcastResponseEntity>

}