package com.friendalert.shivangshah.friendalert.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.friendalert.shivangshah.friendalert.FriendAlertApplication
import com.friendalert.shivangshah.friendalert.injection.module.ActivityBuilderModule
import com.friendalert.shivangshah.friendalert.injection.module.ApplicationModule
import com.friendalert.shivangshah.friendalert.injection.scopes.PerApplication

// Bind Application instance to Dagger @Component - ApplicationComponent
// This component is responsible for providing application scope instances (eg. OkHttp, Database, SharedPrefs.)
// This Component is root of our dagger graph.
// Application component is providing 3 module in our app - ActivityBuilderModule, ApplicationModule, AndroidSupportInjectionModule
@PerApplication
@Component(modules = arrayOf(   ActivityBuilderModule::class,
                                ApplicationModule::class,
                                AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: FriendAlertApplication)

}
