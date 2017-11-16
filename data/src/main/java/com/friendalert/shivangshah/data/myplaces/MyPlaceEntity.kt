package com.friendalert.shivangshah.data.myplaces

/**
 * Data Layer Model
 */
class MyPlaceEntity (val baseCampId: Int,
                     val userId: String,
                     val nickname: String,
                     val address: String,
                     val city: String,
                     val state: String,
                     val latitude: String,
                     val longitude: String,
                     val active: Int)