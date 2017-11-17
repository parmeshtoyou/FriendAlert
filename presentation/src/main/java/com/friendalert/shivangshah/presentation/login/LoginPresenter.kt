package com.friendalert.shivangshah.presentation.login

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/16/17.
 */
class LoginPresenter @Inject constructor(val loginView: LoginContract.View,
                                         val getLoginUserUseCase: SingleUseCase<List<MyPlace>, String>):
        LoginContract.Presenter {

    init {
        loginView.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {
        getLoginUserUseCase
    }

    override fun loginUser() {
        getLoginUserUseCase.execute(GetUserLoginSubscriber(), "hi")
    }

    inner class GetUserLoginSubscriber: DisposableSingleObserver<List<MyPlace>>() {

        override fun onSuccess(t: List<MyPlace>) {
            //handleGetMyPlacesSuccess(t)
        }

        override fun onError(exception: Throwable) {
//            myPlacesView.hideMyPlaces()
//            myPlacesView.hideEmptyState()
//            myPlacesView.showErrorState()
        }

    }
}