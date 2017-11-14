package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.TestActivity
import com.friendalert.shivangshah.friendalert.injection.module.FragmentBuilderModule
import com.friendalert.shivangshah.friendalert.injection.module.HomeActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 11/13/17.
 */
@Subcomponent(modules = arrayOf(HomeActivityModule::class))
interface TestActivitySubComponent : AndroidInjector<TestActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TestActivity>()

}