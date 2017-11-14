package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.injection.module.FragmentBuilderModule
import com.friendalert.shivangshah.friendalert.injection.module.HomeActivityModule
import com.friendalert.shivangshah.friendalert.injection.module.NotificationsFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * This component is just a bridge to MainActivityModule
 *
 * Since all fragments are under Home Activity, we add FragmentBindingModule to this component
 */

@Subcomponent(modules = arrayOf(HomeActivityModule::class,
                                FragmentBuilderModule::class))
interface HomeActivitySubComponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()

}