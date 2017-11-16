package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.myplaces.source.MyPlaceDataStoreFactory
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Contains the MyPlaceDataStore Factory (generates either remote or cache MyPlaceDataStore) using retrieveDataStore
 * remote data store will call api
 * cache data store will call local db
 */
class MyPlaceDataRepository @Inject constructor(private val factory: MyPlaceDataStoreFactory,
                                                private val myPlaceMapper: MyPlaceMapper) :
        MyPlaceRepository {
    override fun getMyPlaces(): Single<List<MyPlace>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getMyPlaces()
                .flatMap {
//                    if (dataStore is MyPlaceRemoteDataStore) {
//                        saveNotificationEntities(it).toSingle { it }
//                    } else {
//                        Single.just(it)
//                    }
                    Single.just(it)
                }
                .map { list ->
                    list.map { listItem ->
                        myPlaceMapper.mapFromEntity(listItem)
                    }
                }
    }
}