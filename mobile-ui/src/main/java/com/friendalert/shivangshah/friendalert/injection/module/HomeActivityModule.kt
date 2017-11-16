package com.friendalert.shivangshah.friendalert.injection.module

import android.support.v7.app.AppCompatActivity
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.MyPlacesFragmentSubComponent
import com.friendalert.shivangshah.friendalert.injection.component.NotificationsFragmentSubComponent
import dagger.Module
import dagger.Provides




/**
 * This module provides home activity related instances (eg. MainActivityPresenter)
 */
@Module(subcomponents = arrayOf(NotificationsFragmentSubComponent::class,
                                MyPlacesFragmentSubComponent::class))
class HomeActivityModule {



}