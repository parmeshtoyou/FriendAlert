package com.friendalert.shivangshah.remote.friends

import com.friendalert.shivangshah.data.friends.repository.FriendsRemote
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.remote.myplaces.MyPlaceService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsRemoteImpl  @Inject constructor(private val friendsService: FriendsService) : FriendsRemote {

    override fun getFriends(userId: String, contacts: ContactsRequestModel): Single<FriendsResponseModel> {

        return friendsService.getFriends("10155348650147990", contacts)

    }

}