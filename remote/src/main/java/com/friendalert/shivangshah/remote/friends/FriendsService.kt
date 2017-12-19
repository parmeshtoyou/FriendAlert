package com.friendalert.shivangshah.remote.friends

import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsService {

    @POST("friends/{id}")
    fun getFriends(@Path("id") userId : String, @Body contactsRequestModel: ContactsRequestModel): Single<FriendsResponseModel>

    @POST("friends/create/{sender_id}/{receiver_id}")
    fun createFriendRequest(@Path("sender_id") senderId : String, @Path("receiver_id") recieverId: String): Single<CreateFriendRequestResponseModel>

    @PUT("friends/update/{id}/{status}")
    fun updateFriend(@Path("id") id: String, @Path("status") status: String) : Single<UpdateFriendResponseModel>

}