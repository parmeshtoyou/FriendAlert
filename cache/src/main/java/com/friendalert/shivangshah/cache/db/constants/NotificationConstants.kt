package com.friendalert.shivangshah.cache.db.constants

import com.friendalert.shivangshah.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object NotificationConstants {

    internal val QUERY_GET_ALL_NOTIFICATIONS = "SELECT * FROM " + Db.NotificationTable.TABLE_NAME

}