package com.friendalert.shivangshah.friendalert.injection.module

import android.app.Application
import android.content.Context
import com.friendalert.shivangshah.cache.NotificationsCacheImpl
import com.friendalert.shivangshah.cache.PreferencesHelper
import com.friendalert.shivangshah.cache.db.DbOpenHelper
import com.friendalert.shivangshah.cache.mapper.NotificationEntityMapper
import com.friendalert.shivangshah.data.notifications.NotificationDataRepository
import com.friendalert.shivangshah.data.JobExecutor
import com.friendalert.shivangshah.data.myplaces.MyPlaceDataRepository
import com.friendalert.shivangshah.data.myplaces.MyPlaceMapper
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import com.friendalert.shivangshah.data.myplaces.source.MyPlaceDataStoreFactory
import com.friendalert.shivangshah.data.notifications.NotificationMapper
import com.friendalert.shivangshah.data.notifications.repository.NotificationCache
import com.friendalert.shivangshah.data.notifications.repository.NotificationRemote
import com.friendalert.shivangshah.data.notifications.source.NotificationDataStoreFactory
import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import com.friendalert.shivangshah.domain.notifications.NotificationRepository
import com.friendalert.shivangshah.friendalert.BuildConfig
import com.friendalert.shivangshah.friendalert.UiThread
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.TestActivitySubComponent
import dagger.Module
import dagger.Provides
import com.friendalert.shivangshah.friendalert.injection.scopes.PerApplication
import com.friendalert.shivangshah.remote.myplaces.MyPlaceEntityMapper
import com.friendalert.shivangshah.remote.myplaces.MyPlaceRemoteImpl
import com.friendalert.shivangshah.remote.myplaces.MyPlaceService
import com.friendalert.shivangshah.remote.myplaces.MyPlaceServiceFactory
import com.friendalert.shivangshah.remote.notifications.NotificationRemoteImpl
import com.friendalert.shivangshah.remote.notifications.NotificationService
import com.friendalert.shivangshah.remote.notifications.NotificationServiceFactory


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

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
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


    // Notifications Dependencies ------------------------------------------------------------------
    @Provides
    @PerApplication
    internal fun provideNotificationsRepository(factory: NotificationDataStoreFactory,
                                                mapper: NotificationMapper): NotificationRepository {
        return NotificationDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideNotificationsCache(factory: DbOpenHelper,
                                           entityMapper: NotificationEntityMapper,
                                           mapper: com.friendalert.shivangshah.cache.db.mapper.NotificationMapper,
                                           helper: PreferencesHelper): NotificationCache {
        return NotificationsCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideNotificationsRemote(service: NotificationService,
                                            factory: com.friendalert.shivangshah.remote.notifications.NotificationEntityMapper): NotificationRemote {
        return NotificationRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideNotificationsService(): NotificationService {
        return NotificationServiceFactory.makeNotificationService(BuildConfig.DEBUG)
    }

    // MyPlaces Dependencies -----------------------------------------------------------------------
    @Provides
    @PerApplication
    internal fun provideMyPlacesRepository(factory: MyPlaceDataStoreFactory,
                                           mapper: MyPlaceMapper): MyPlaceRepository {
        return MyPlaceDataRepository(factory, mapper)
    }

//    @Provides
//    @PerApplication
//    internal fun provideMyPlacesCache(factory: DbOpenHelper,
//                                      entityMapper: MyPlaceEntityMapper,
//                                      mapper: com.friendalert.shivangshah.cache.db.mapper.MyPlaceMapper,
//                                      helper: PreferencesHelper): MyPlaceCache {
//        return MyPlaceCacheImpl(factory, entityMapper, mapper, helper)
//    }

    @Provides
    @PerApplication
    internal fun provideMyPlacesRemote(service: MyPlaceService,
                                            factory: com.friendalert.shivangshah.remote.myplaces.MyPlaceEntityMapper): MyPlaceRemote {
        return MyPlaceRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideMyPlacesService(): MyPlaceService {
        return MyPlaceServiceFactory.makeMyPlaceService(BuildConfig.DEBUG)
    }

}
