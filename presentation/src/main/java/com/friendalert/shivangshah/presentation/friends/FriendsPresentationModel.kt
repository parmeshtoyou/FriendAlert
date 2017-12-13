package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsPresentationModel {

    private var friendsDictionary =  HashMap<String, ArrayList<FriendModel>>()

    fun setFriendsDictionary(friends: ArrayList<FriendModel>,
                             requests: ArrayList<FriendModel>,
                             suggested: ArrayList<FriendModel>,
                             invites: ArrayList<String>){

        friendsDictionary.put("Friends", friends)
        friendsDictionary.put("Requests", requests)
        friendsDictionary.put("Suggested", suggested)

    }

    fun getFriendsDictionary() : HashMap<String, ArrayList<FriendModel>>{
        return friendsDictionary
    }

}