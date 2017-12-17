package com.friendalert.shivangshah.remote.broadcast

import com.friendalert.shivangshah.data.broadcast.BroadcastEntity
import com.friendalert.shivangshah.data.broadcast.CreateBroadcastResponseEntity
import com.friendalert.shivangshah.data.broadcast.repository.BroadcastRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastRemoteImpl @Inject constructor(private val broadcastService: BroadcastService,
                                              private val entityMapper: BroadcastEntityMapper,
                                              private val responseModelEntityMapper: BroadcastResponseModelEntityMapper) :
        BroadcastRemote {

    override fun createBroadcast(broadcast: BroadcastEntity): Single<CreateBroadcastResponseEntity> {

        val broadcastRequestModel = entityMapper.mapToRemote(broadcast)

        return broadcastService.createBroadcast(broadcastRequestModel).map {
            responseModel: BroadcastResponseModel -> responseModelEntityMapper.mapFromRemote(responseModel)
        }

    }

}