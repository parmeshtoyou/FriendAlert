package com.friendalert.shivangshah.data.user.source

import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.data.user.repository.UserDataStore
import com.friendalert.shivangshah.data.user.repository.UserRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemote) :
        UserDataStore {

    override fun createUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userRemote.createUser(user)
    }

    override fun updateUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userRemote.updateUser(user)
    }

    override fun logoutUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userRemote.logoutUser(user)
    }

    override fun getUser(): Single<UserEntity> {
        throw UnsupportedOperationException()
    }
}