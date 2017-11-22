package com.friendalert.shivangshah.friendalert.myplaces

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceViewModel (var myPlaces: ArrayList<MyPlaceViewModelData>)

class MyPlaceViewModelData(var base_camp_id: Int,
                      var fk_user_id: String,
                      var nickname: String,
                      var address: String,
                      var city: String,
                      var state: String,
                      var latitude: String,
                      var longitude: String,
                      var active: Int)