package com.friendalert.shivangshah.remote.user

import com.friendalert.shivangshah.data.CustomResponseCodes
import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.data.user.repository.UserRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
class UserRemoteImpl @Inject constructor(private val userService: UserService,
                                        private val responseModelEntityMapper : UserResponseModelEntityMapper,
                                        private val requestModelEntityMapper: UserRequestModelEntityMapper) :
        UserRemote {

    override fun createUser(user: UserEntity): Single<UserResponseModelEntity> {
//        val requestModel : UserRequestModel = requestModelEntityMapper.mapFromEntity(user)
//        return userService.createUser(requestModel).map { response -> responseModelEntityMapper.mapFromRemote(response.userResponseModel) }

        var response = UserResponseModel(customCode = CustomResponseCodes.createSuccess)
        return Single.just(response).map { userResponse -> responseModelEntityMapper.mapFromRemote(response) }
    }

    override fun updateUser(user: UserEntity): Single<UserResponseModelEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logoutUser(user: UserEntity): Single<UserResponseModelEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}