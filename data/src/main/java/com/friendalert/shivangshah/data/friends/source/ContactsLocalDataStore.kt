package com.friendalert.shivangshah.data.friends.source

import com.friendalert.shivangshah.data.friends.repository.ContactsLocal
import com.friendalert.shivangshah.data.friends.repository.FriendsDataStore
import com.friendalert.shivangshah.data.friends.repository.FriendsRemote
import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class ContactsLocalDataStore @Inject constructor(private val contactsLocal: ContactsLocal) :
        FriendsDataStore {

    override fun getContacts(): Single<ArrayList<String>> {

        return contactsLocal.getContacts()

    }

    override fun getFriends(userId: String, contacts: ContactsRequestModel): Single<FriendsResponseModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}