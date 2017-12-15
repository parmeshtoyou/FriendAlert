package com.friendalert.shivangshah.presentation.myplaces

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlacesPresentationModel {

    private var myPlaceView : MyPlaceView = MyPlaceView(ArrayList<MyPlaceViewData>())
    private var toBeDeletedId = 0

    fun setMyPlaces(myPlacesView: MyPlaceView) {
        this.myPlaceView = myPlacesView
    }

    fun getAllMyPlaces() : ArrayList<MyPlaceViewData>{
        return this.myPlaceView.myPlaces
    }

    fun addMyPlaceToListPending(myPlace: MyPlaceViewData){
        this.myPlaceView.myPlaces.add(myPlace)
    }

    fun setToBeDeletedId(myPlaceId : Int){
        toBeDeletedId = myPlaceId
    }

    fun deleteMyPlace() : MyPlaceViewData{

        var toBeDeletedMyPlace = MyPlaceViewData(0,"","","","","","","",0)

        for (myPlace in myPlaceView.myPlaces){

            if(myPlace.base_camp_id == toBeDeletedId){

                toBeDeletedMyPlace = MyPlaceViewData(myPlace.base_camp_id,myPlace.fk_user_id,myPlace.nickname,
                        myPlace.address, myPlace.city, myPlace.state, myPlace.latitude, myPlace.longitude, myPlace.active)

                myPlaceView.myPlaces.remove(myPlace)
                break

            }

        }

        return toBeDeletedMyPlace

    }

}