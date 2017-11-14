package com.friendalert.shivangshah.friendalert.injection.module

import android.app.Application
import android.content.Context
import com.friendalert.shivangshah.data.NotificationDataRepository
import com.friendalert.shivangshah.data.executor.JobExecutor
import com.friendalert.shivangshah.data.mapper.NotificationMapper
import com.friendalert.shivangshah.data.repository.NotificationRemote
import com.friendalert.shivangshah.data.source.NotificationDataStoreFactory
import com.friendalert.shivangshah.domain.executor.PostExecutionThread
import com.friendalert.shivangshah.domain.executor.ThreadExecutor
import com.friendalert.shivangshah.domain.repository.NotificationRepository
import com.friendalert.shivangshah.friendalert.BuildConfig
import com.friendalert.shivangshah.friendalert.UiThread
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.TestActivitySubComponent
import dagger.Module
import dagger.Provides
import com.friendalert.shivangshah.friendalert.injection.scopes.PerApplication
import com.friendalert.shivangshah.remote.NotificationRemoteImpl
import com.friendalert.shivangshah.remote.NotificationService
import com.friendalert.shivangshah.remote.NotificationServiceFactory


/**
 * We provide retrofit, okhttp, persistence db, shared pref etc
 * We have to add our subcomponents to AppModule so our dagger graph will understand that
 *
 */
@Module(subcomponents = arrayOf(HomeActivitySubComponent::class,
                                TestActivitySubComponent::class))
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

//    @Provides
//    @PerApplication
//    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
//        return PreferencesHelper(context)
//    }


    @Provides
    @PerApplication
    internal fun provideNotificationsRepository(factory: NotificationDataStoreFactory,
                                                mapper: NotificationMapper): NotificationRepository {
        return NotificationDataRepository(factory, mapper)
    }

//    @Provides
//    @PerApplication
//    internal fun provideNotificationsCache(factory: DbOpenHelper,
//                                      entityMapper: NotificationEntityMapper,
//                                      mapper: com.friendalert.shivangshah.cache.db.mapper.NotificationMapper,
//                                      helper: PreferencesHelper): NotificationsCache {
//        return NotificationCacheImpl(factory, entityMapper, mapper, helper)
//    }

    @Provides
    @PerApplication
    internal fun provideNotificationsRemote(service: NotificationService,
                                            factory: com.friendalert.shivangshah.remote.mapper.NotificationEntityMapper): NotificationRemote {
        return NotificationRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideNotificationsService(): NotificationService {
        return NotificationServiceFactory.makeNotificationService(BuildConfig.DEBUG)
    }
}
