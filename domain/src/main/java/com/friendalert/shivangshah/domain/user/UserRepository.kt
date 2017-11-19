package com.friendalert.shivangshah.domain.user

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by shivangshah on 11/17/17.
 */
interface UserRepository {

    fun createUser(user: User) : Single<UserResponse>

    fun updateUser(user: User) : Single<UserResponse>

    fun logoutUser(user: User) : Single<UserResponse>

    fun getUser() : Single<User>

}