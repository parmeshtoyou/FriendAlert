package com.friendalert.shivangshah.data.user

import com.friendalert.shivangshah.data.CustomResponseCodes
import com.friendalert.shivangshah.data.user.source.UserDataStoreFactory
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.domain.user.UserResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserDataRepository @Inject constructor(private val factory: UserDataStoreFactory,
                                                private val mapper: UserMapper,
                                                private val responseMapper: UserResponseMapper) : UserRepository {

    override fun createUser(user: User): Single<UserResponse> {

        val remoteUserSingle: Single<UserResponse> = factory.retrieveRemoteDataStore().createUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }
        val cacheUserSingle: Single<UserResponse> = factory.retrieveCacheDataStore().createUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }

        // if create success, create user in cache
        return remoteUserSingle.flatMap{
            response: UserResponse -> if (response.customCode == CustomResponseCodes.createSuccess){
                cacheUserSingle
            }else{ // return create failure
                var response = UserResponse(customCode = CustomResponseCodes.createFailure)
                Single.just(response)
            }
        }
    }

    override fun updateUser(user: User): Single<UserResponse> {
        val remoteUserSingle: Single<UserResponse> = factory.retrieveRemoteDataStore().updateUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }
        val cacheUserSingle: Single<UserResponse> = factory.retrieveCacheDataStore().updateUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }

            return remoteUserSingle.flatMap{
                response: UserResponse -> if (response.customCode == 1){
                cacheUserSingle
            }else{
                var response = UserResponse(customCode = 0)
                Single.just(response)
            }
        }
    }

    override fun logoutUser(user: User): Single<UserResponse> {
        val remoteUserSingle: Single<UserResponse> = factory.retrieveRemoteDataStore().logoutUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }
        val cacheUserSingle: Single<UserResponse> = factory.retrieveCacheDataStore().logoutUser(mapper.mapToEntity(user)).map { response -> responseMapper.mapFromEntity(response) }

            return remoteUserSingle.flatMap{
                response: UserResponse -> if (response.customCode == 1){
                cacheUserSingle
            }else{
                var response = UserResponse(customCode = 0)
                Single.just(response)
            }
        }
    }

    override fun getUser(): Single<User> {
        return factory.retrieveRemoteDataStore().getUser().map { user -> mapper.mapFromEntity(user) }
    }

}