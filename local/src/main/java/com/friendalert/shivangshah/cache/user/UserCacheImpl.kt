package com.friendalert.shivangshah.cache.user

import com.friendalert.shivangshah.cache.CustomResponseCodes
import com.friendalert.shivangshah.cache.PreferencesHelper
import com.friendalert.shivangshah.data.user.UserEntity
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.data.user.repository.UserCache
import com.friendalert.shivangshah.domain.user.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import com.google.gson.Gson


class UserCacheImpl @Inject constructor(private val preferencesHelper : PreferencesHelper,
                                        private val mapper: UserMapper,
                                        private val responseMapper: UserResponseModelEntityMapper) : UserCache {

    override fun createUser(user: UserEntity): Single<UserResponseModelEntity> {

        return Single.defer{
            val gson = Gson()
            val json = gson.toJson(mapper.mapFromEntity(user))
            preferencesHelper.getSharedPreferences().edit().putString("userObject", json).commit()

            var response = UserResponseModel(customCode = CustomResponseCodes.createSuccess)
            Single.just(response).map { userResponse -> responseMapper.mapFromCached(response) }
        }
    }

    override fun updateUser(user: UserEntity): Single<UserResponseModelEntity> {
        return Single.defer{
            val gson = Gson()
            val json = gson.toJson(mapper.mapFromEntity(user))
            preferencesHelper.getSharedPreferences().edit().putString("userObject", json).commit()

            var response = UserResponseModel(customCode = CustomResponseCodes.updateSuccess)

            Single.just(response).map { userResponse -> responseMapper.mapFromCached(response) }
        }
    }

    override fun logoutUser(user: UserEntity): Single<UserResponseModelEntity> {
        return Single.defer{
            preferencesHelper.getSharedPreferences().edit().putString("userObject", "").commit()

            var response = UserResponseModel(customCode = CustomResponseCodes.deleteSuccess)

            Single.just(response).map { userResponse -> responseMapper.mapFromCached(response) }
        }
    }

    override fun getUser(): Single<UserEntity> {

        return Single.defer<UserEntity> {

            val gson = Gson()
            val json = preferencesHelper.getSharedPreferences().getString("userObject", "")
            val cachedUser = gson.fromJson<CachedUser>(json, CachedUser::class.java)

            if(cachedUser == null){
                Single.just(mapper.mapToEntity(CachedUser("","","","","","",0)))
            }else{
                Single.just(mapper.mapToEntity(cachedUser))
            }
        }
    }

}