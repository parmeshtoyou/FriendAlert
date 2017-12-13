package com.friendalert.shivangshah.data.friends.repository

import io.reactivex.Single

/**
 * Created by shivangshah on 12/12/17.
 */
interface ContactsLocal {

    fun getContacts() : Single<ArrayList<String>>

}