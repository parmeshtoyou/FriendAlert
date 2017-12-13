package com.friendalert.shivangshah.data.friends

import com.friendalert.shivangshah.data.friends.source.FriendsDataStoreFactory
import com.friendalert.shivangshah.domain.friends.FriendsRepository
import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsDataRepository @Inject constructor(private val factory: FriendsDataStoreFactory)
    : FriendsRepository {

    override fun getContacts(): Single<ArrayList<String>> {
        val dataStore = factory.retrieveDataStore(local = true)
        return dataStore.getContacts()
    }

    override fun getFriends(userId: String, contacts: ContactsRequestModel): Single<FriendsResponseModel> {

        val dataStore = factory.retrieveDataStore(local = false)
        return dataStore.getFriends(userId, contacts)

    }

}