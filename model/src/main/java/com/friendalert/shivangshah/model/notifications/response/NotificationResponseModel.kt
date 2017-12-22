package com.friendalert.shivangshah.model.notifications.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/13/17.
 */
class NotificationResponseModel (@SerializedName("customCode") val customCode: Int,
                                 @SerializedName("data") val data: ArrayList<NotificationModel>)

class NotificationModel(@SerializedName("broadcast_id") var broadcast_id: String, @SerializedName("fk_user_id") var fk_user_id: String, @SerializedName("first_name") var first_name: String, @SerializedName("last_name") var last_name: String,
                        @SerializedName("phone_number") var phone_number: String, @SerializedName("latitude") var latitude: String, @SerializedName("longitude") var longitude: String, @SerializedName("message") var message: String, @SerializedName("friend_id") var friend_id: String,
                        @SerializedName("timestamp") var timestamp: String, var isRead: Boolean, @SerializedName("location") var location: String)