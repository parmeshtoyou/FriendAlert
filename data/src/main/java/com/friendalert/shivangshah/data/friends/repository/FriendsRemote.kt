package com.friendalert.shivangshah.data.friends.repository

import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import io.reactivex.Single

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsRemote {

    fun getFriends(userId: String, contacts: ContactsRequestModel) : Single<FriendsResponseModel>

    fun createFriendRequest(senderId: String, receiverId: String) : Single<CreateFriendRequestResponseModel>

    fun updateFriend(id: String, status: Int) : Single<UpdateFriendResponseModel>

}