package com.friendalert.shivangshah.remote.myplaces

/**
 * MyPlace model returned from service
 */
class MyPlaceRequestModel(val base_camp_id: Int,
                          val fk_user_id: String,
                          val nickname: String,
                          val address: String,
                          val city: String,
                          val state: String,
                          val latitude: String,
                          val longitude: String,
                          val active: Int)