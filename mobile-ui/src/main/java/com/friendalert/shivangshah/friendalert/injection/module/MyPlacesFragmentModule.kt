package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.myplaces.CreateMyPlace
import com.friendalert.shivangshah.domain.myplaces.DeleteMyPlace
import com.friendalert.shivangshah.domain.myplaces.GetMyPlaces
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import com.friendalert.shivangshah.presentation.myplaces.*
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
                                          getMyPlaces: GetMyPlaces,
                                          createMyPlace: CreateMyPlace,
                                          deleteMyPlace: DeleteMyPlace,
                                          presentationModel: MyPlacesPresentationModel):
            MyPlacesContract.Presenter {
        return MyPlacesPresenter(mainView, getMyPlaces, createMyPlace, deleteMyPlace, presentationModel)
    }

}