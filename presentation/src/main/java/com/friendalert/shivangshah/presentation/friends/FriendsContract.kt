package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsContract {

    interface View : BaseView<FriendsContract.Presenter> {

        fun showFriends(friendsDictionary: HashMap<String, ArrayList<FriendModel>>)

        fun showMyFriends(myFriends: java.util.ArrayList<FriendModel>)

        fun showRequests(requests: java.util.ArrayList<FriendModel>)

        fun showSuggested(suggested: java.util.ArrayList<FriendModel>)

        fun showInvites(invites: java.util.ArrayList<FriendModel>)

    }

    interface Presenter : BasePresenter {

        fun getFriends()

        fun getMyFriends()

        fun getRequests()

        fun getSuggested()

        fun getInvites()

    }

}