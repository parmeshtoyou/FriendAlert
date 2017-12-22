package com.friendalert.shivangshah.data.broadcast

import com.friendalert.shivangshah.data.broadcast.source.BroadcastDataStoreFactory
import com.friendalert.shivangshah.domain.broadcast.BroadcastRepository
import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastDataRepository @Inject constructor(private val factory: BroadcastDataStoreFactory) :
        BroadcastRepository {

    override fun createBroadcast(broadcast: BroadcastRequestModel): Single<BroadcastResponseModel> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.createBroadcast(broadcast)
    }

}