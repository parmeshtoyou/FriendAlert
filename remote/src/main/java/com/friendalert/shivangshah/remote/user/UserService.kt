package com.friendalert.shivangshah.remote.user

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * Created by shivangshah on 11/17/17.
 */
interface UserService {

    @POST("login")
    fun createUser(@Body user : UserRequestModel): Single<UserResponseModel>

    @PUT("updateToken")
    fun updateUser(@Body user : UserRequestModel): Single<UserResponseModel>

    @PUT("logout")
    fun deleteUser(@Body user : UserRequestModel): Single<UserResponseModel>

}