package com.friendalert.shivangshah.data.broadcast.repository

import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastDataStore {

    fun createBroadcast(broadcast: BroadcastRequestModel) : Single<BroadcastResponseModel>

}