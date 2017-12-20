package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.model.friends.response.FriendModel

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsPresentationModel {

    private var friendsDictionary :  HashMap<String, ArrayList<FriendModel>>? = null

    private var deleteFriendRequestModel : FriendModel? = null
    private var createFriendRequestModel : FriendModel? = null
    private var acceptFriendRequestModel : FriendModel? = null
    private var declineFriendRequestModel : FriendModel? = null

    private var friendActionType : FriendActionType? = null

    fun setFriendsDictionary(friends: ArrayList<FriendModel>,
                             requests: ArrayList<FriendModel>,
                             suggested: ArrayList<FriendModel>,
                             invites: ArrayList<String>){

        friendsDictionary = HashMap<String, ArrayList<FriendModel>>()

        friendsDictionary!!.put("Friends", friends)
        friendsDictionary!!.put("Requests", requests)
        friendsDictionary!!.put("Suggested", suggested)

    }

    fun getFriendsDictionary() : HashMap<String, ArrayList<FriendModel>>?{
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

    fun setFriendActionType(friendActionType: FriendActionType){
        this.friendActionType = friendActionType
    }

    fun getFriendActionType() : FriendActionType {
        return this.friendActionType!!
    }

    fun successDeleteFriend(){

        // remove from friends
        var friends = friendsDictionary!!["Friends"]
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
        var suggestedFriends = friendsDictionary!!["Suggested"]
        suggestedFriends!!.remove(createFriendRequestModel)

        // add to requests
        createFriendRequestModel!!.request_status = "0" // 0 = pending
        createFriendRequestModel!!.request_id = requestId
        createFriendRequestModel!!.receiver_id = createFriendRequestModel!!.user_id
        friendsDictionary!!["Requests"]!!.add(createFriendRequestModel!!)

        createFriendRequestModel = null

    }

    fun successAcceptFriendRequest(){

        // remove from requests
        var requestsFriends = friendsDictionary!!["Requests"]
        for(requestFriend in requestsFriends!!){
            if(requestFriend.request_id == acceptFriendRequestModel!!.request_id){
                requestsFriends.remove(requestFriend)
                break
            }
        }

        // add to my friends
        acceptFriendRequestModel!!.request_status = "accepted"
        friendsDictionary!!["Friends"]!!.add(acceptFriendRequestModel!!)

        acceptFriendRequestModel = null

    }

    fun successDeclineFriendRequest(){

        // remove from requests
        var requestsFriends = friendsDictionary!!["Requests"]
        for(requestFriend in requestsFriends!!){
            if(requestFriend.request_id == declineFriendRequestModel!!.request_id){
                requestsFriends.remove(requestFriend)
                break
            }
        }

        declineFriendRequestModel = null

    }

}