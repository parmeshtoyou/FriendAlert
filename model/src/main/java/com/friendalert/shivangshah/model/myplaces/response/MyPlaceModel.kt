package com.friendalert.shivangshah.model.myplaces.response

/**
 * Created by shivangshah on 12/15/17.
 */
class MyPlaceModel(var base_camp_id: Int,
                   var fk_user_id: String,
                   var nickname: String?,
                   var address: String,
                   var city: String,
                   var state: String,
                   var latitude: String,
                   var longitude: String,
                   var active: Int,
                   var radius: String)