package com.friendalert.shivangshah.remote.broadcast

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastRequestModel (@SerializedName("userId") val userId: String,
                             @SerializedName("message") val message: String,
                             @SerializedName("latitude") val latitude: String,
                             @SerializedName("longitude") val longitude: String)