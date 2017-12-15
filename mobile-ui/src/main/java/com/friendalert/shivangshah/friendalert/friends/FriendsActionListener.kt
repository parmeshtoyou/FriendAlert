package com.friendalert.shivangshah.friendalert.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel

/**
 * Created by shivangshah on 12/14/17.
 */
interface FriendsActionListener {

    fun DeleteFriendClicked(position: Int, friendModel: FriendModel?)

    fun AcceptFriendRequestClicked(position: Int, friendModel: FriendModel?)

    fun DeclineFriendRequestClicked(position: Int, friendModel: FriendModel?)

    fun SendFriendRequestClicked(position: Int, friendModel: FriendModel?)

    fun InviteFriendClicked()

}