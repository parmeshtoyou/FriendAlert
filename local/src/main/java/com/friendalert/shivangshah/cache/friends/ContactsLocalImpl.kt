package com.friendalert.shivangshah.cache.friends

import com.friendalert.shivangshah.cache.ContactsHelper
import com.friendalert.shivangshah.data.friends.repository.ContactsLocal
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by shivangshah on 12/12/17.
 */
class ContactsLocalImpl @Inject constructor(private val contactsHelper: ContactsHelper) : ContactsLocal {

    override fun getContacts(): Single<ArrayList<String>> {

        return contactsHelper.readContacts()

    }
}