package com.friendalert.shivangshah.model.friends.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsResponseModel (@SerializedName("customCode") val customCode: Int,
                            @SerializedName("friendsData") val friendsData: ArrayList<FriendModel>,
                            @SerializedName("requestsData") val requestsData: ArrayList<FriendModel>,
                            @SerializedName("suggestedData") val suggestedData: ArrayList<FriendModel>,
                            @SerializedName("inviteData") val inviteData: ArrayList<String>)

class FriendModel(@SerializedName("request_id") val request_id: Int, @SerializedName("sender_id") val sender_id: String, @SerializedName("receiver_id") val receiver_id: String,
                  @SerializedName("request_status") val request_status: String, @SerializedName("user_id") val user_id: String, @SerializedName("first_name") val first_name: String,
                  @SerializedName("last_name") val last_name: String, @SerializedName("phone_number") val phone_number: String, @SerializedName("active") val active: Int)