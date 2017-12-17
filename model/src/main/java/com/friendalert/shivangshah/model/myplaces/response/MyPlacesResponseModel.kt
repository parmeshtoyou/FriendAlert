package com.friendalert.shivangshah.model.myplaces.response

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/15/17.
 */
class MyPlacesResponseModel(@SerializedName("customCode") var customCode: Int,
                            @SerializedName("data") var myPlaces: ArrayList<MyPlaceModel>)