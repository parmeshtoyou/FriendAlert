package com.friendalert.shivangshah.model.friends.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/13/17.
 */
class CreateFriendRequestResponseModel(@SerializedName("customCode") val customCode: Int,
                                        @SerializedName("request_id") val requestId: Int)