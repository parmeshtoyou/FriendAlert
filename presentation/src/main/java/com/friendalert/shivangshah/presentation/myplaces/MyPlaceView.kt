package com.friendalert.shivangshah.presentation.myplaces

/**
 * Presentation Layer Model
 */
class MyPlaceView(var myPlaces: List<MyPlaceViewData>)

class MyPlaceViewData(val base_camp_id: Int,
                                val fk_user_id: String,
                                val nickname: String,
                                val address: String,
                                val city: String,
                                val state: String,
                                val latitude: String,
                                val longitude: String,
                                val active: Int)