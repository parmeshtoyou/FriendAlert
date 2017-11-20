package com.friendalert.shivangshah.presentation.splash

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserResponse
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.login.LoginContract
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/20/17.
 */
class SplashPresenter @Inject constructor(val splashView: SplashContract.View,
                                          val getGetUserUseCase: SingleUseCase<User, Void>):
        SplashContract.Presenter {

    init {
        splashView.setPresenter(this)
    }

    override fun start() {
        getUser()
    }

    override fun stop() {
        getGetUserUseCase.dispose()
    }

    override fun getUser() {
        getGetUserUseCase.execute(GetUserSubscriber())
    }

    inner class GetUserSubscriber: DisposableSingleObserver<User>() {

        override fun onSuccess(user: User) {
            if(user.userId != ""){
                splashView.goToHomeScreen()
            }else{
                splashView.goToLoginScreen()
            }
        }

        override fun onError(exception: Throwable) {

        }
    }
}