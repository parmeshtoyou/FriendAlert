package com.friendalert.shivangshah.model.broadcast.request

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/22/17.
 */
class BroadcastRequestModel (@SerializedName("userId") val userId: String,
                             @SerializedName("message") val message: String,
                             @SerializedName("latitude") val latitude: String,
                             @SerializedName("longitude") val longitude: String,
                             @SerializedName("location") val location: String)