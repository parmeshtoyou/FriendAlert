package com.friendalert.shivangshah.domain.myplaces

/**
 * Domain Layer Model
 */
class MyPlaces(var customCode: Int,
                             var myPlaces: List<MyPlacesData>)

class MyPlacesData(val base_camp_id: Int,
                                 val fk_user_id: String,
                                 val nickname: String,
                                 val address: String,
                                 val city: String,
                                 val state: String,
                                 val latitude: String,
                                 val longitude: String,
                                 val active: Int)