package com.friendalert.shivangshah.domain.broadcast

import io.reactivex.Single

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastRepository {

    fun createBroadcast(broadcast: Broadcast) : Single<CreateBroadcastResponse>

}