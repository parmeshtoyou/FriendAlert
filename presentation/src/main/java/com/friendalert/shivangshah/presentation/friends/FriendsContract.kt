package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView
import java.text.FieldPosition

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsContract {

    interface View : BaseView<FriendsContract.Presenter> {

        fun showFriends(friendsDictionary: HashMap<String, ArrayList<FriendModel>>)

    }

    interface Presenter : BasePresenter {

        fun getFriends()

        fun getFriendsByType()

        fun deleteFriend(position: Int, friendModel: FriendModel)

        fun sendFriendRequest(position: Int, friendModel: FriendModel)

        fun acceptFriendRequest(position: Int, friendModel: FriendModel)

        fun declineFriendRequest(position: Int, friendModel: FriendModel)
    }

}