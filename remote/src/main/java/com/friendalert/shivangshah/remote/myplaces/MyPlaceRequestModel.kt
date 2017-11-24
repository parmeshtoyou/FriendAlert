package com.friendalert.shivangshah.remote.myplaces

import com.google.gson.annotations.SerializedName

/**
 * MyPlace model returned from service
 */
class MyPlaceRequestModel(@SerializedName("base_camp_id") val base_camp_id: Int,
                          @SerializedName("fk_user_id") val fk_user_id: String,
                          @SerializedName("nickname") val nickname: String,
                          @SerializedName("address") val address: String,
                          @SerializedName("city") val city: String,
                          @SerializedName("state") val state: String,
                          @SerializedName("latitude") val latitude: String,
                          @SerializedName("longitude") val longitude: String,
                          @SerializedName("active") val active: Int)