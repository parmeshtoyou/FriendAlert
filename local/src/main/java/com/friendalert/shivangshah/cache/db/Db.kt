package com.friendalert.shivangshah.cache.db

/**
 * Created by shivangshah on 11/14/17.
 */
object Db {

    object NotificationTable {
        const val TABLE_NAME = "notifications"

        const val NOTIFICATION_ID = "notification_id"
        const val NAME = "name"
        const val TITLE = "title"
        const val AVATAR = "avatar"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        NAME + " TEXT NOT NULL," +
                        TITLE + " TEXT," +
                        AVATAR + " TEXT" +
                        "); "
    }

}