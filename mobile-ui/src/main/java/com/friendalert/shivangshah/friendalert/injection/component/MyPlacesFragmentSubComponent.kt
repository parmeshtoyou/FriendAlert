package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.injection.module.MyPlacesFragmentModule
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 11/15/17.
 */
@Subcomponent(modules = arrayOf(MyPlacesFragmentModule::class))
interface MyPlacesFragmentSubComponent : AndroidInjector<MyPlacesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MyPlacesFragment>()

}