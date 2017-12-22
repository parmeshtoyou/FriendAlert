package com.friendalert.shivangshah.domain.broadcast

import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastRepository {

    fun createBroadcast(broadcast: BroadcastRequestModel) : Single<BroadcastResponseModel>

}