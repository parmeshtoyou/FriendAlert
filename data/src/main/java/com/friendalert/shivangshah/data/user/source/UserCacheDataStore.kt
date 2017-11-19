package com.friendalert.shivangshah.data.user.source

import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.data.user.repository.UserCache
import com.friendalert.shivangshah.data.user.repository.UserDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
open class UserCacheDataStore @Inject constructor(private val userCache: UserCache) : UserDataStore{

    override fun createUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userCache.createUser(user)
    }

    override fun updateUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userCache.updateUser(user)
    }

    override fun logoutUser(user: UserEntity): Single<UserResponseModelEntity> {
        return userCache.logoutUser(user)
    }

    override fun getUser(): Single<UserEntity> {
        return userCache.getUser()
    }
}