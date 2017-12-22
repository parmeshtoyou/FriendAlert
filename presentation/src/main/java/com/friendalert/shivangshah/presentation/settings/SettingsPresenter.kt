package com.friendalert.shivangshah.presentation.settings

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserResponse
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.splash.SplashContract
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 12/21/17.
 */
class SettingsPresenter @Inject constructor(val settingsView: SettingsContract.View,
                                            val logoutUser: SingleUseCase<UserResponse, User>):
        SettingsContract.Presenter {

    init {
        settingsView.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {

        logoutUser.dispose()

    }

    override fun logout() {

        logoutUser.execute(LogoutUserSubscriber())

    }

    inner class LogoutUserSubscriber: DisposableSingleObserver<UserResponse>() {

        override fun onSuccess(response: UserResponse) {
            if(response.customCode == CustomResponseCodes.deleteSuccess){

                settingsView.logout()

            }else{

                settingsView.showFailure(true, "Logout Failed")

            }
        }

        override fun onError(exception: Throwable) {

            settingsView.showFailure(true, exception.toString())

        }

    }

}