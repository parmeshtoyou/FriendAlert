package com.friendalert.shivangshah.model.friends.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsResponseModel (@SerializedName("customCode") var customCode: Int,
                            @SerializedName("friendsData") var friendsData: ArrayList<FriendModel>,
                            @SerializedName("requestsData") var requestsData: ArrayList<FriendModel>,
                            @SerializedName("suggestedData") var suggestedData: ArrayList<FriendModel>,
                            @SerializedName("inviteData") var inviteData: ArrayList<String>)

class FriendModel(@SerializedName("request_id") var request_id: Int, @SerializedName("sender_id") var sender_id: String, @SerializedName("receiver_id") var receiver_id: String,
                  @SerializedName("request_status") var request_status: String, @SerializedName("user_id") var user_id: String, @SerializedName("first_name") var first_name: String,
                  @SerializedName("last_name") var last_name: String, @SerializedName("phone_number") var phone_number: String, @SerializedName("active") var active: Int)