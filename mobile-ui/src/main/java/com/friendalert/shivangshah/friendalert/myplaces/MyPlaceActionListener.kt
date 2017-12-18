package com.friendalert.shivangshah.friendalert.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel

/**
 * Created by shivangshah on 12/18/17.
 */
interface MyPlaceActionListener {

    fun createMyPlacePressed(myPlace: MyPlaceRequestModel)

    fun editMyPlacePressed(myPlace: MyPlaceRequestModel)

    fun deleteMyPlacePressed(myPlace: MyPlaceRequestModel)

}