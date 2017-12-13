package com.friendalert.shivangshah.remote.friends

import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsService {

    @POST("friends/{id}")
    fun getFriends(@Path("id") userId : String, @Body contactsRequestModel: ContactsRequestModel): Single<FriendsResponseModel>

}