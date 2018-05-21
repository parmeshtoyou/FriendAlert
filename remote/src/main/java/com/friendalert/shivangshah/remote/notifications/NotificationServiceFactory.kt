package com.friendalert.shivangshah.remote.notifications

import android.content.Context
import com.friendalert.shivangshah.remote.InternetConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by shivangshah on 11/11/17.
 */
object  NotificationServiceFactory {

    fun makeNotificationService(isDebug: Boolean, context: Context): NotificationService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug), makeNetworkInterceptor(context))
        return makeNotificationService(okHttpClient, makeGson())
    }

    private fun makeNotificationService(okHttpClient: OkHttpClient, gson: Gson): NotificationService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://x8w743ocle.execute-api.us-east-1.amazonaws.com/dev/api/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(NotificationService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, networkInterceptor: InternetConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(networkInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeNetworkInterceptor(context: Context) : InternetConnectionInterceptor {

        return InternetConnectionInterceptor(context)

    }


}