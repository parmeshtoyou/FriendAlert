package com.friendalert.shivangshah.data.friends.repository

import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import io.reactivex.Single

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsRemote {

    fun getFriends(userId: String, contacts: ContactsRequestModel) : Single<FriendsResponseModel>

}