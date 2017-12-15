package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsPresentationModel {

    private var friendsDictionary =  HashMap<String, ArrayList<FriendModel>>()

    private var deleteFriendRequestModel : FriendModel? = null
    private var createFriendRequestModel : FriendModel? = null
    private var acceptFriendRequestModel : FriendModel? = null
    private var declineFriendRequestModel : FriendModel? = null

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

    fun setDeleteFriendRequestModel(friendModel: FriendModel){
        deleteFriendRequestModel = friendModel
    }

    fun setCreateFriendRequestModel(friendModel: FriendModel){
        createFriendRequestModel = friendModel
    }

    fun setAcceptFriendRequestModel(friendModel: FriendModel){
        acceptFriendRequestModel = friendModel
    }

    fun setDeclineFriendRequestModel(friendModel: FriendModel){
        declineFriendRequestModel = friendModel
    }

    fun successDeleteFriend(){

        // remove from friends
        var friends = friendsDictionary["Friends"]
        for(friend in friends!!){
            if(friend.request_id == deleteFriendRequestModel!!.request_id){
                friends.remove(friend)
                break
            }
        }

        deleteFriendRequestModel = null

    }

    fun successCreateFriendRequest(requestId: Int){

        // remove from suggested
        var suggestedFriends = friendsDictionary["Suggested"]
        for(suggestedFriend in suggestedFriends!!){
            if(suggestedFriend.request_id == createFriendRequestModel!!.request_id){
                suggestedFriends.remove(suggestedFriend)
                break
            }
        }

        // add to requests
        createFriendRequestModel!!.request_status = "pending"
        createFriendRequestModel!!.request_id = requestId
        friendsDictionary["Requests"]!!.add(createFriendRequestModel!!)

        createFriendRequestModel = null

    }

    fun successAcceptFriendRequest(){

        // remove from requests
        var requestsFriends = friendsDictionary["Requests"]
        for(requestFriend in requestsFriends!!){
            if(requestFriend.request_id == acceptFriendRequestModel!!.request_id){
                requestsFriends.remove(requestFriend)
                break
            }
        }

        // add to my friends
        acceptFriendRequestModel!!.request_status = "accepted"
        friendsDictionary["Friends"]!!.add(acceptFriendRequestModel!!)

        acceptFriendRequestModel = null

    }

    fun successDeclineFriendRequest(){

        // remove from requests
        var requestsFriends = friendsDictionary["Requests"]
        for(requestFriend in requestsFriends!!){
            if(requestFriend.request_id == declineFriendRequestModel!!.request_id){
                requestsFriends.remove(requestFriend)
                break
            }
        }

        declineFriendRequestModel = null

    }

}