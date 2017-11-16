package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.myplaces.GetMyPlaces
import com.friendalert.shivangshah.domain.notifications.GetNotifications
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceMapper
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesPresenter
import com.friendalert.shivangshah.presentation.notifications.NotificationMapper
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import com.friendalert.shivangshah.presentation.notifications.NotificationsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 11/15/17.
 */
@Module
class MyPlacesFragmentModule {

    @PerFragment
    @Provides
    internal fun provideMyPlacesView(myPlacesFragment: MyPlacesFragment): MyPlacesContract.View {
        return myPlacesFragment
    }

    @PerFragment
    @Provides
    internal fun provideMyPlacesPresenter(mainView: MyPlacesContract.View,
                                               getMyPlaces: GetMyPlaces, mapper: MyPlaceMapper):
            MyPlacesContract.Presenter {
        return MyPlacesPresenter(mainView, getMyPlaces, mapper)
    }

}