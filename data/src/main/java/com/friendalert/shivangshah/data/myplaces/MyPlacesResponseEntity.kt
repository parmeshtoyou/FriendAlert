package com.friendalert.shivangshah.data.myplaces

/**
 * Data Layer Model
 */
class MyPlacesResponseEntity(var customCode: Int,
                            var myPlaces: List<MyPlacesResponseEntityData>)

class MyPlacesResponseEntityData(val base_camp_id: Int,
                                val fk_user_id: String,
                                val nickname: String,
                                val address: String,
                                val city: String,
                                val state: String,
                                val latitude: String,
                                val longitude: String,
                                val active: Int)