package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface NotificationService {

    @GET("broadcasts/{id}")
    fun getNotifications(@Path("id") userId: String): Single<NotificationResponseModel>

}