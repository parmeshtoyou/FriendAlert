package com.friendalert.shivangshah.remote.myplaces

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 11/20/17.
 */
class MyPlacesResponseModel(@SerializedName("customCode") var customCode: Int,
                            @SerializedName("data") var myPlaces: List<MyPlacesResponseModelData>)

class MyPlacesResponseModelData(val base_camp_id: Int,
                                val fk_user_id: String,
                                val nickname: String,
                                val address: String,
                                val city: String,
                                val state: String,
                                val latitude: String,
                                val longitude: String,
                                val active: Int)