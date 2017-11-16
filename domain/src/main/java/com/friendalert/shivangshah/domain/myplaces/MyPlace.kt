package com.friendalert.shivangshah.domain.myplaces

/**
 * Domain Layer Model
 */
class MyPlace (val baseCampId: Int,
               val userId: String,
               val nickname: String,
               val address: String,
               val city: String,
               val state: String,
               val latitude: String,
               val longitude: String,
               val active: Int)