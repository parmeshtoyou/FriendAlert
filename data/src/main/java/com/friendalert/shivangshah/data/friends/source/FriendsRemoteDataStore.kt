package com.friendalert.shivangshah.data.friends.source

import com.friendalert.shivangshah.data.friends.repository.FriendsDataStore
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
class FriendsRemoteDataStore @Inject constructor(private val friendsRemote: FriendsRemote) :
        FriendsDataStore {

    override fun getContacts(): Single<ArrayList<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFriends(userId: String, contacts: ContactsRequestModel): Single<FriendsResponseModel> {

        return friendsRemote.getFriends(userId, contacts)

    }

    override fun createFriendRequest(senderId: String, receiverId: String): Single<CreateFriendRequestResponseModel> {

        return friendsRemote.createFriendRequest(senderId, receiverId)

    }

    override fun updateFriend(id: String, status: String): Single<UpdateFriendResponseModel> {

        return friendsRemote.updateFriend(id, status)

    }

}