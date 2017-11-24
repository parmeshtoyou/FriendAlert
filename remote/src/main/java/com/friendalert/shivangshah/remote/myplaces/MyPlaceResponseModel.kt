package com.friendalert.shivangshah.remote.myplaces

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceResponseModel(@SerializedName("customCode") val customCode: Int,
                           @SerializedName("data") val data: MyPlaceResponseModelData)

class MyPlaceResponseModelData(@SerializedName("insertId") val insertId: Int)