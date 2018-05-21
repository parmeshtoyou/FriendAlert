package com.friendalert.shivangshah.friendalert.login

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.presentation.login.LoginContract
import dagger.android.AndroidInjection
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginContract.View, PhoneActionListener {

    @Inject lateinit var loginPresenter : LoginContract.Presenter

    lateinit var loginButton : LoginButton
    lateinit var fbButton : Button

    override fun setPresenter(presenter: LoginContract.Presenter) {
        loginPresenter = presenter;
    }

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fbButton = findViewById(R.id.customFBButton)
        fbButton.setOnClickListener {
            loginButton.performClick()
        }

        callbackManager = CallbackManager.Factory.create()
        loginButton = findViewById(R.id.login_button)
        loginButton.setReadPermissions("public_profile")

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

                val newPushNotificationToken = preferences.getString("newPushNotificationToken", "")
                val oldPushNotificationToken = preferences.getString("oldPushNotificationToken", "")
                var firstName = ""
                var lastName = ""

                val request = GraphRequest.newMeRequest(loginResult.accessToken) { user, graphResponse ->
                    var name = user.optString("name")
                    firstName = name.split(' ')[0]
                    lastName = name.split(' ')[1]

                    Log.d("Name", user.optString("name"))

                    loginPresenter.loginUser(loginResult.accessToken.userId,
                            firstName,
                            lastName,
                            "2013144410",
                            newPushNotificationToken,
                            oldPushNotificationToken,
                            1)
                }.executeAsync()

            }

            override fun onCancel() {

            }

            override fun onError(exception: FacebookException) {

            }
        })

    }

    override fun enterClicked(phoneNumber: String) {
    }

    override fun cancelClicked() {
    }

    override fun loginUser() {

        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorState() {

        Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_LONG).show();
        LoginManager.getInstance().logOut()

    }

    override fun hideErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFailure(firstTime: Boolean, errorMessage: String) {
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

    override fun onBackPressed() {

        // Do Not go back to login screen

    }
}
