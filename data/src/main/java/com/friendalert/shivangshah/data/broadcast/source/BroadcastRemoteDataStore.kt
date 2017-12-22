package com.friendalert.shivangshah.data.broadcast.source

import com.friendalert.shivangshah.data.broadcast.repository.BroadcastDataStore
import com.friendalert.shivangshah.data.broadcast.repository.BroadcastRemote
import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastRemoteDataStore @Inject constructor(private val broadcastRemote: BroadcastRemote) :
        BroadcastDataStore {


    override fun createBroadcast(broadcast: BroadcastRequestModel): Single<BroadcastResponseModel> {
        return broadcastRemote.createBroadcast(broadcast)
    }

}