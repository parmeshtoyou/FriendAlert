package com.friendalert.shivangshah.remote.broadcast

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastService {

    @POST("broadcasts")
    fun createBroadcast(@Body broadcastRequestModel: BroadcastRequestModel) : Single<BroadcastResponseModel>

}