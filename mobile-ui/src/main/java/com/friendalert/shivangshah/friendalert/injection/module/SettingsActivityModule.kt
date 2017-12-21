package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.LogoutUser
import com.friendalert.shivangshah.domain.user.GetUser
import com.friendalert.shivangshah.friendalert.login.SplashActivity
import com.friendalert.shivangshah.friendalert.settings.SettingsActivity
import com.friendalert.shivangshah.presentation.settings.SettingsContract
import com.friendalert.shivangshah.presentation.settings.SettingsPresenter
import com.friendalert.shivangshah.presentation.splash.SplashContract
import com.friendalert.shivangshah.presentation.splash.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 12/21/17.
 */
@Module
class SettingsActivityModule {

    @Provides
    internal fun provideSettingsView(settingsActivity: SettingsActivity): SettingsContract.View {
        return settingsActivity
    }

    @Provides
    internal fun provideSettingsPresenter(mainView: SettingsContract.View,
                                        logoutUser: LogoutUser):
            SettingsContract.Presenter {
        return SettingsPresenter(mainView, logoutUser)
    }

}