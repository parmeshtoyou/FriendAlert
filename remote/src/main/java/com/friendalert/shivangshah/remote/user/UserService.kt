package com.friendalert.shivangshah.remote.user

import com.friendalert.shivangshah.remote.notifications.NotificationModel
import com.friendalert.shivangshah.remote.notifications.NotificationService
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * Created by shivangshah on 11/17/17.
 */
interface UserService {

    @POST("team.json")
    fun createUser(user : UserRequestModel): Single<UserResponse>

    @PUT("")
    fun updateUser(user : UserRequestModel): Single<UserResponse>

    @DELETE("")
    fun deleteUser(user : UserRequestModel): Single<UserResponse>

    class UserResponse {
        lateinit var userResponseModel: UserResponseModel
    }

}