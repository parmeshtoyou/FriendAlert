package com.friendalert.shivangshah.data.broadcast

import com.friendalert.shivangshah.data.broadcast.source.BroadcastDataStoreFactory
import com.friendalert.shivangshah.domain.broadcast.Broadcast
import com.friendalert.shivangshah.domain.broadcast.BroadcastRepository
import com.friendalert.shivangshah.domain.broadcast.CreateBroadcastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastDataRepository @Inject constructor(private val factory: BroadcastDataStoreFactory,
                                                  private val broadcastMapper: BroadcastMapper,
                                                  private val broadcastResponseEntityMapper: CreateBroadcastResponseEntityMapper) :
        BroadcastRepository {

    override fun createBroadcast(broadcast: Broadcast): Single<CreateBroadcastResponse> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.createBroadcast(broadcastMapper.mapToEntity(broadcast)).map {
            response: CreateBroadcastResponseEntity ->  broadcastResponseEntityMapper.mapFromEntity(response)
        }
    }

}