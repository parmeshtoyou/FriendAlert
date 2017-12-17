package com.friendalert.shivangshah.model.myplaces.request

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/15/17.
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