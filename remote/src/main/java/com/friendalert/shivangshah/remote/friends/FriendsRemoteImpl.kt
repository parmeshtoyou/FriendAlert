package com.friendalert.shivangshah.remote.friends

import com.friendalert.shivangshah.data.friends.repository.FriendsRemote
import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsRemoteImpl  @Inject constructor(private val friendsService: FriendsService) : FriendsRemote {

    override fun getFriends(userId: String, contacts: ContactsRequestModel): Single<FriendsResponseModel> {

        val contacts2 = ArrayList<String>()
        contacts2.add("123")
        contacts2.add("2342423")
        contacts2.add("111111")
        contacts2.add("928340293")

        return friendsService.getFriends("10155348650147990", ContactsRequestModel(contacts2))

        //return friendsService.getFriends(userId, contacts)
    }

    override fun createFriendRequest(senderId: String, receiverId: String): Single<CreateFriendRequestResponseModel> {
        return friendsService.createFriendRequest(senderId, receiverId)
    }

    override fun updateFriend(id: String, status: Int): Single<UpdateFriendResponseModel> {
        return friendsService.updateFriend(id, status)
    }

}