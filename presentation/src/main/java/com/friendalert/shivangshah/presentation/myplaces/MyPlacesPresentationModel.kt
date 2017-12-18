package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlacesPresentationModel {

    private var myPlaces = ArrayList<MyPlaceModel>()
    private var toBeDeletedId = 0

    fun setMyPlaces(myPlaces: ArrayList<MyPlaceModel>) {
        this.myPlaces = myPlaces
    }

    fun getAllMyPlaces() : ArrayList<MyPlaceModel>{
        return this.myPlaces
    }

    fun addMyPlaceToListPending(myPlaceRequestModel: MyPlaceRequestModel){
        var myPlace = MyPlaceModel(myPlaceRequestModel.base_camp_id, myPlaceRequestModel.fk_user_id, myPlaceRequestModel.nickname, myPlaceRequestModel.address, myPlaceRequestModel.city, myPlaceRequestModel.state, myPlaceRequestModel.latitude, myPlaceRequestModel.longitude, myPlaceRequestModel.active, myPlaceRequestModel.radius)
        this.myPlaces.add(myPlace)
    }

    fun setToBeDeletedId(myPlaceId : Int){
        toBeDeletedId = myPlaceId
    }

    fun deleteMyPlace() : MyPlaceModel{

        var toBeDeletedMyPlace = MyPlaceModel(0,"","","","","","","",0, "")

        for (myPlace in myPlaces){

            if(myPlace.base_camp_id == toBeDeletedId){

                toBeDeletedMyPlace = MyPlaceModel(myPlace.base_camp_id,myPlace.fk_user_id,myPlace.nickname,
                        myPlace.address, myPlace.city, myPlace.state, myPlace.latitude, myPlace.longitude, myPlace.active, myPlace.radius)

                myPlaces.remove(myPlace)
                break

            }

        }

        return toBeDeletedMyPlace

    }

}