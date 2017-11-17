package com.friendalert.shivangshah.friendalert.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import android.content.Intent
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.login.LoginContract
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject lateinit var loginPresenter : LoginContract.Presenter

    override fun setPresenter(presenter: LoginContract.Presenter) {
        loginPresenter = presenter;
    }

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callbackManager = CallbackManager.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions("email")

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                loginPresenter.loginUser()
            }

            override fun onCancel() {

            }

            override fun onError(exception: FacebookException) {

            }
        })
    }

    override fun loginUser() {

    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()

        loginPresenter.start()
    }
}
