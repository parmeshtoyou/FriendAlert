package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.myplaces.EditMyPlace
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesPresenter @Inject constructor(val myPlacesView: MyPlacesContract.View,
                                            val getMyPlacesUseCase: SingleUseCase<MyPlacesResponseModel, Void>,
                                            val createMyPlaceUseCase: SingleUseCase<MyPlaceResponseModel, MyPlaceRequestModel>,
                                            val deleteMyPlaceUseCase: SingleUseCase<MyPlaceResponseModel, Int>,
                                            val editMyPlaceUseCase: SingleUseCase<MyPlaceResponseModel, MyPlaceRequestModel>,
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
        createMyPlaceUseCase.dispose()
        deleteMyPlaceUseCase.dispose()
    }

    override fun retrieveMyPlaces() {
        getMyPlacesUseCase.execute(GetMyPlacesSubscriber())
    }


    inner class GetMyPlacesSubscriber: DisposableSingleObserver<MyPlacesResponseModel>() {

        override fun onSuccess(myPlacesResponseModel: MyPlacesResponseModel) {
            if (myPlacesResponseModel.customCode == CustomResponseCodes.getSuccess) {

                // save retrieved data to presentation model (knows which data to show to user)
                presentationModel.setMyPlaces(myPlacesResponseModel.myPlaces)

                // show from presentation model
                myPlacesView.showMyPlaces(presentationModel.getAllMyPlaces())

            } else {

            }
        }

        override fun onError(exception: Throwable) {


        }

    }

    override fun createMyPlace(myPlaceRequestModel: MyPlaceRequestModel) {

        presentationModel.addMyPlaceToListPending(myPlaceRequestModel)
        createMyPlaceUseCase.execute(CreateMyPlaceSubscriber(), myPlaceRequestModel)

    }

    inner class CreateMyPlaceSubscriber: DisposableSingleObserver<MyPlaceResponseModel>(){

        override fun onSuccess(t: MyPlaceResponseModel) {
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

    override fun editMyPlace(myPlaceRequestModel: MyPlaceRequestModel) {

        // presentation model - set to be editted
        presentationModel.setToBeEditted(myPlaceRequestModel)

        editMyPlaceUseCase.execute(EditMyPlaceSubscriber(), myPlaceRequestModel)

    }

    inner class EditMyPlaceSubscriber: DisposableSingleObserver<MyPlaceResponseModel>(){

        override fun onSuccess(t: MyPlaceResponseModel) {
            if(t.customCode == CustomResponseCodes.updateSuccess){

                // presentation model - update to be editted
                var myPlace = presentationModel.editMyPlace()
                myPlacesView.editMyPlace(myPlace!!)

            }else{


            }
        }

        override fun onError(e: Throwable) {


        }

    }

    override fun deleteMyPlace(myPlaceId: Int) {
        presentationModel.setToBeDeletedId(myPlaceId)
        deleteMyPlaceUseCase.execute(DeleteMyPlaceSubscriber(), myPlaceId)
    }

    inner class DeleteMyPlaceSubscriber: DisposableSingleObserver<MyPlaceResponseModel>(){

        override fun onSuccess(t: MyPlaceResponseModel) {
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