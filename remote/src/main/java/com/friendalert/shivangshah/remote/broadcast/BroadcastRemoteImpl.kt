package com.friendalert.shivangshah.remote.broadcast

import com.friendalert.shivangshah.data.broadcast.repository.BroadcastRemote
import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastRemoteImpl @Inject constructor(private val broadcastService: BroadcastService) :
        BroadcastRemote {

    override fun createBroadcast(broadcast: BroadcastRequestModel): Single<BroadcastResponseModel> {

        return broadcastService.createBroadcast(broadcast)

    }

}