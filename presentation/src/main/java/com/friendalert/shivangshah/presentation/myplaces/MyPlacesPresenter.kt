package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.myplaces.MyPlaceResponse
import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesPresenter @Inject constructor(val myPlacesView: MyPlacesContract.View,
                                            val getMyPlacesUseCase: SingleUseCase<MyPlaces, Void>,
                                            val createMyPlaceUseCase: SingleUseCase<MyPlaceResponse, MyPlace>,
                                            val deleteMyPlaceUseCase: SingleUseCase<MyPlaceResponse, Int>,
                                            val myPlacesMapper: MyPlacesMapper,
                                            val myPlaceMapper: MyPlaceMapper,
                                            val presentationModel: MyPlacesPresentationModel):
        MyPlacesContract.Presenter {

    init {
        myPlacesView.setPresenter(this)
    }

    override fun start() {
        retrieveMyPlaces()
    }

    override fun stop() {
        getMyPlacesUseCase.dispose()
    }

    override fun retrieveMyPlaces() {
        getMyPlacesUseCase.execute(GetMyPlacesSubscriber())
    }


    inner class GetMyPlacesSubscriber: DisposableSingleObserver<MyPlaces>() {

        override fun onSuccess(myPlaces: MyPlaces) {
            if (myPlaces.customCode == CustomResponseCodes.getSuccess) {

                // save retrieved data to presentation model (knows which data to show to user)
                presentationModel.setMyPlaces(myPlacesMapper.mapToView(myPlaces))

                // show from presentation model
                myPlacesView.showMyPlaces(presentationModel.getAllMyPlaces())

            } else {

            }
        }

        override fun onError(exception: Throwable) {


        }

    }

    override fun createMyPlace(myPlace: MyPlaceViewData) {

        presentationModel.addMyPlaceToListPending(myPlace)
        createMyPlaceUseCase.execute(CreateMyPlaceSubscriber(), myPlaceMapper.mapFromView(myPlace))

    }

    inner class CreateMyPlaceSubscriber: DisposableSingleObserver<MyPlaceResponse>(){

        override fun onSuccess(t: MyPlaceResponse) {
            if(t.customCode == CustomResponseCodes.createSuccess){

                // success in backend - add returned id to the last object added to the list (newly created Myplace)
                presentationModel.getAllMyPlaces().last().base_camp_id = t.data.insertId
                myPlacesView.addMyPlace(presentationModel.getAllMyPlaces().last())

            }else{

                // failure in backend - remove last object added from list
                presentationModel.getAllMyPlaces().removeAt(presentationModel.getAllMyPlaces().size - 1)

            }
        }

        override fun onError(e: Throwable) {

            // error - remove last object added from list
            presentationModel.getAllMyPlaces().removeAt(presentationModel.getAllMyPlaces().size - 1)

        }

    }

    inner class DeleteMyPlaceSubscriber: DisposableSingleObserver<MyPlaceResponse>(){

        override fun onSuccess(t: MyPlaceResponse) {
            if(t.customCode == CustomResponseCodes.deleteSuccess){

                var myPlaceToBeDeleted = presentationModel.deleteMyPlace()
                myPlacesView.deleteMyPlace(myPlaceToBeDeleted)

            }else{


            }
        }

        override fun onError(e: Throwable) {



        }

    }
}