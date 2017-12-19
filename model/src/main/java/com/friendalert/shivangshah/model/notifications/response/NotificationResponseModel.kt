package com.friendalert.shivangshah.model.notifications.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/13/17.
 */
class NotificationResponseModel (@SerializedName("customCode") val customCode: Int,
                                 @SerializedName("data") val data: ArrayList<NotificationModel>)

class NotificationModel(@SerializedName("broadcast_id") var broadcast_id: String, @SerializedName("fk_user_id") val fk_user_id: String, @SerializedName("latitude") val latitude: String,
                  @SerializedName("longitude") val longitude: String, @SerializedName("message") val message: String, @SerializedName("friend_id") val friend_id: String,
                  @SerializedName("timestamp") val timestamp: String, var isRead: Boolean)