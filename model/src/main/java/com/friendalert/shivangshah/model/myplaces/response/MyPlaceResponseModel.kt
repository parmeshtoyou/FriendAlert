package com.friendalert.shivangshah.model.myplaces.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/15/17.
 */
class MyPlaceResponseModel(@SerializedName("customCode") val customCode: Int,
                           @SerializedName("data") val data: MyPlaceResponseModelData = MyPlaceResponseModelData(0))

class MyPlaceResponseModelData(@SerializedName("insertId") val insertId: Int)