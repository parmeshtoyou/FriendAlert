package com.friendalert.shivangshah.domain.myplaces

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlace (val base_camp_id: Int,
               val fk_user_id: String,
               val nickname: String,
               val address: String,
               val city: String,
               val state: String,
               val latitude: String,
               val longitude: String,
               val active: Int)