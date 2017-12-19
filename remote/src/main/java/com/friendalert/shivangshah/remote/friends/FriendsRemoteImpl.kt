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

        return friendsService.getFriends(userId, contacts)

    }

    override fun createFriendRequest(senderId: String, receiverId: String): Single<CreateFriendRequestResponseModel> {

        return friendsService.createFriendRequest(senderId, receiverId)

    }

    override fun updateFriend(id: String, status: String): Single<UpdateFriendResponseModel> {

        return friendsService.updateFriend(id, status)

    }

}