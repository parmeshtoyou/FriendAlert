package com.friendalert.shivangshah.remote

import android.content.Context
import android.net.ConnectivityManager
import com.friendalert.shivangshah.model.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by shivangshah on 12/20/17.
 */
@Singleton
class InternetConnectionInterceptor @Inject constructor(context: Context) : Interceptor {

    var context: Context? = null

    init {
        this.context = context
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        if (netInfo == null || !netInfo.isConnected) {
            throw NoNetworkException()
        }

        return chain.proceed(chain.request())
    }

}
