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

    @PUT("")
    fun updateUser(user : UserRequestModel): Single<UserResponseModel>

    @DELETE("")
    fun deleteUser(user : UserRequestModel): Single<UserResponseModel>

    class BufferooResponse {
        var customCode: Int = 1
    }

}