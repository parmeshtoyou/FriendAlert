package com.friendalert.shivangshah.presentation.login

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserResponse
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/16/17.
 */
class LoginPresenter @Inject constructor(val loginView: LoginContract.View,
                                         val getLoginUserUseCase: SingleUseCase<UserResponse, User>):
        LoginContract.Presenter {

    init {
        loginView.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {
        getLoginUserUseCase.dispose()
    }

    override fun loginUser(id: String, firstName: String, lastName: String, phoneNumber: String, newPushNotification: String, oldPushNotification: String, active: Int) {
        var user = User(id, firstName, lastName, phoneNumber,newPushNotification,oldPushNotification,active)
        getLoginUserUseCase.execute(GetUserLoginSubscriber(), user)
    }

    inner class GetUserLoginSubscriber: DisposableSingleObserver<UserResponse>() {

        override fun onSuccess(response: UserResponse) {
            if(response.customCode == CustomResponseCodes.createSuccess){
                loginView.loginUser()
            }else{
                loginView.showErrorState()
            }
        }

        override fun onError(exception: Throwable) {
            loginView.showErrorState()
        }

    }
}