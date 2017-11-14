package com.friendalert.shivangshah.remote

import com.friendalert.shivangshah.remote.model.NotificationModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface NotificationService {

    @GET("team.json")
    fun getNotifications(): Single<NotificationResponse>

    class NotificationResponse {
        lateinit var team: List<NotificationModel>
    }

}