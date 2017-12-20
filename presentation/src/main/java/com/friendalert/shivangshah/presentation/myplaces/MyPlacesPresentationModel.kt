package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlacesPresentationModel {

    private var myPlaces : ArrayList<MyPlaceModel>? = null
    private var toBeDeletedId = 0
    private var toBeEdittedMyPlace : MyPlaceModel? = null

    fun setMyPlaces(myPlaces: ArrayList<MyPlaceModel>) {
        this.myPlaces = myPlaces
    }

    fun getAllMyPlaces() : ArrayList<MyPlaceModel>?{
        return this.myPlaces
    }

    fun addMyPlaceToListPending(myPlaceRequestModel: MyPlaceRequestModel){
        var myPlace = MyPlaceModel(myPlaceRequestModel.base_camp_id, myPlaceRequestModel.fk_user_id, myPlaceRequestModel.nickname, myPlaceRequestModel.address, myPlaceRequestModel.city, myPlaceRequestModel.state, myPlaceRequestModel.latitude, myPlaceRequestModel.longitude, myPlaceRequestModel.active, myPlaceRequestModel.radius)
        this.myPlaces!!.add(myPlace)
    }

    fun setToBeDeletedId(myPlaceId : Int){
        toBeDeletedId = myPlaceId
    }

    fun deleteMyPlace() : MyPlaceModel{

        var toBeDeletedMyPlace = MyPlaceModel(0,"","","","","","","",0, "")

        for (myPlace in myPlaces!!){

            if(myPlace.base_camp_id == toBeDeletedId){

                toBeDeletedMyPlace = MyPlaceModel(myPlace.base_camp_id,myPlace.fk_user_id,myPlace.nickname,
                        myPlace.address, myPlace.city, myPlace.state, myPlace.latitude, myPlace.longitude, myPlace.active, myPlace.radius)

                myPlaces!!.remove(myPlace)
                break

            }

        }

        return toBeDeletedMyPlace

    }

    fun setToBeEditted(myPlaceRequestModel: MyPlaceRequestModel){
        toBeEdittedMyPlace = MyPlaceModel(myPlaceRequestModel.base_camp_id, myPlaceRequestModel.fk_user_id, myPlaceRequestModel.nickname, myPlaceRequestModel.address, myPlaceRequestModel.city, myPlaceRequestModel.state, myPlaceRequestModel.latitude, myPlaceRequestModel.longitude, myPlaceRequestModel.active, myPlaceRequestModel.radius)
    }

    fun editMyPlace() : MyPlaceModel? {

        for(myPlace in getAllMyPlaces()!!){
            if(toBeEdittedMyPlace!!.base_camp_id == myPlace.base_camp_id){
                myPlace.nickname = toBeEdittedMyPlace!!.nickname
                myPlace.radius = toBeEdittedMyPlace!!.radius

                return myPlace
            }
        }

        return null
    }

}