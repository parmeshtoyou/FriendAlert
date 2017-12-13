package com.friendalert.shivangshah.cache

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by shivangshah on 12/12/17.
 */
@Singleton
class ContactsHelper @Inject constructor(private val context: Context) {

    fun readContacts() : Single<ArrayList<String>> {

        var contacts = ArrayList<String> ()

        val cr = context.getContentResolver()
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        if (cur!!.getCount() > 0) {
            while (cur!!.moveToNext()) {
                val id = cur!!.getString(cur!!.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur!!.getString(cur!!.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (Integer.parseInt(cur!!.getString(cur!!.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    // get the phone number
                    val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf<String>(id), null)
                    while (pCur!!.moveToNext()) {
                        val phone = pCur!!.getString(
                                pCur!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        contacts.add(phone)
                    }
                    pCur!!.close()
                }
            }

            return Single.just(contacts)
        }

        return Single.just(contacts)
    }

}