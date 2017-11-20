package com.friendalert.shivangshah.friendalert.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.login.LoginContract
import com.friendalert.shivangshah.presentation.splash.SplashContract
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.content.Intent
import android.os.Handler
import com.friendalert.shivangshah.friendalert.HomeActivity


class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject lateinit var splashPresenter : SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun setPresenter(presenter: SplashContract.Presenter) {
        splashPresenter = presenter
    }

    override fun goToHomeScreen() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 3000)

    }

    override fun goToLoginScreen() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 3000)

    }

    override fun onStart() {
        super.onStart()

        splashPresenter.start()

    }
}
