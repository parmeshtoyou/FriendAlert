package com.friendalert.shivangshah.model.myplaces.request

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/15/17.
 */
class MyPlaceRequestModel(@SerializedName("base_camp_id") var base_camp_id: Int,
                          @SerializedName("fk_user_id") var fk_user_id: String,
                          @SerializedName("nickname") var nickname: String?,
                          @SerializedName("address") var address: String,
                          @SerializedName("city") var city: String,
                          @SerializedName("state") var state: String,
                          @SerializedName("latitude") var latitude: String,
                          @SerializedName("longitude") var longitude: String,
                          @SerializedName("active") var active: Int,
                          @SerializedName("radius") var radius: String)