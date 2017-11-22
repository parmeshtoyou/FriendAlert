package com.friendalert.shivangshah.presentation.myplaces

/**
 * Presentation Layer Model
 */
class MyPlaceView(var myPlaces: ArrayList<MyPlaceViewData>)

class MyPlaceViewData(var base_camp_id: Int,
                      var fk_user_id: String,
                      var nickname: String,
                      var address: String,
                      var city: String,
                      var state: String,
                      var latitude: String,
                      var longitude: String,
                      var active: Int)