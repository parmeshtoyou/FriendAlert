package com.friendalert.shivangshah.friendalert.injection.module

import android.support.v7.app.AppCompatActivity
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.injection.component.*
import dagger.Module
import dagger.Provides




/**
 * This module provides home activity related instances (eg. MainActivityPresenter)
 */
@Module(subcomponents = arrayOf(NotificationsFragmentSubComponent::class,
                                MyPlacesFragmentSubComponent::class,
                                BroadcastFragmentSubComponent::class,
                                FriendsFragmentSubComponent::class))
class HomeActivityModule {



}