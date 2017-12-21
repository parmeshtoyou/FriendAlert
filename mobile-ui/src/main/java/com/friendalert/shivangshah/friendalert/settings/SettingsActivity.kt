package com.friendalert.shivangshah.friendalert.settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.settings.SettingsContract
import com.friendalert.shivangshah.presentation.splash.SplashContract
import dagger.android.AndroidInjection
import javax.inject.Inject

class SettingsActivity : AppCompatActivity(), SettingsContract.View {

    @Inject lateinit var settingsPresenter : SettingsContract.Presenter

    private var logoutButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        logoutButton = findViewById<Button>(R.id.logoutButton)

        logoutButton!!.setOnClickListener {
            v: View? -> settingsPresenter.logout()
        }
    }

    override fun setPresenter(presenter: SettingsContract.Presenter) {

        settingsPresenter = presenter

    }

    override fun showSuccess() {

        // show logout succesfull

    }

    override fun showFailure(firstTime: Boolean, errorMessage: String) {

        // show logout failed

    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun logout() {

        // facebook logout

    }

    override fun goToLoginScreen() {

        // navigate to login screen

    }

    override fun onStart() {
        super.onStart()

        settingsPresenter.start()
    }

    override fun onDestroy() {
        settingsPresenter.stop()

        super.onDestroy()
    }
}
