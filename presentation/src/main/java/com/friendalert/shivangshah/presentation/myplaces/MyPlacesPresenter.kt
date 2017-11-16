package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.notifications.Notification
import com.friendalert.shivangshah.presentation.notifications.NotificationMapper
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesPresenter @Inject constructor(val myPlacesView: MyPlacesContract.View,
                                            val getMyPlacesUseCase: SingleUseCase<List<MyPlace>, Void>,
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

    internal fun handleGetMyPlacesSuccess(myPlaces: List<MyPlace>) {
        myPlacesView.hideErrorState()
        if (myPlaces.isNotEmpty()) {
            myPlacesView.hideEmptyState()
            myPlacesView.showMyPlaces(myPlaces.map { myPlaceMapper.mapToView(it) })
        } else {
            myPlacesView.hideMyPlaces()
            myPlacesView.showEmptyState()
        }
    }

    inner class GetMyPlacesSubscriber: DisposableSingleObserver<List<MyPlace>>() {

        override fun onSuccess(t: List<MyPlace>) {
            handleGetMyPlacesSuccess(t)
        }

        override fun onError(exception: Throwable) {
            myPlacesView.hideMyPlaces()
            myPlacesView.hideEmptyState()
            myPlacesView.showErrorState()
        }

    }
}