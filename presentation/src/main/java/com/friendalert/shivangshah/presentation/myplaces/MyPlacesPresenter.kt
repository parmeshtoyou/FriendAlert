package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesPresenter @Inject constructor(val myPlacesView: MyPlacesContract.View,
                                            val getMyPlacesUseCase: SingleUseCase<MyPlaces, Void>,
                                            val myPlaceMapper: MyPlaceMapper):
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

    internal fun handleGetMyPlacesSuccess(myPlaces: MyPlaces) {
        myPlacesView.hideErrorState()
        if (myPlaces.customCode == CustomResponseCodes.getSuccess) {
            myPlacesView.hideEmptyState()

            myPlacesView.showMyPlaces(myPlaces.myPlaces.map { myPlacesData -> myPlaceMapper.mapToView(myPlaces) })

        } else {
            myPlacesView.hideMyPlaces()
            myPlacesView.showEmptyState()
        }
    }

    inner class GetMyPlacesSubscriber: DisposableSingleObserver<MyPlaces>() {

        override fun onSuccess(t: MyPlaces) {
            handleGetMyPlacesSuccess(t)
        }

        override fun onError(exception: Throwable) {
            myPlacesView.hideMyPlaces()
            myPlacesView.hideEmptyState()
            myPlacesView.showErrorState()
        }

    }
}