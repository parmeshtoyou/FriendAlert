package com.friendalert.shivangshah.friendalert.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.friendalert.shivangshah.cache.PreferencesHelper
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.login.LoginContract
import com.friendalert.shivangshah.presentation.login.LoginPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject lateinit var loginPresenter : LoginContract.Presenter
    @Inject lateinit var preferencesHelper: PreferencesHelper

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
                loginPresenter.loginUser(loginResult.accessToken.userId,
                        "Shivang",
                        "Shah",
                        "2013144410",
                        "newToken",
                        "oldToken",
                        1)
            }

            override fun onCancel() {

            }

            override fun onError(exception: FacebookException) {

            }
        })

    }

    override fun loginUser() {
        //Start Home Activity
        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorState() {
        Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_LONG).show();
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
