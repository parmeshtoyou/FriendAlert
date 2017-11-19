package com.friendalert.shivangshah.cache.notifications

import android.content.ContentValues
import android.database.Cursor
import com.friendalert.shivangshah.cache.db.Db
import com.friendalert.shivangshah.cache.db.ModelDbMapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/14/17.
 */
class NotificationMapper @Inject constructor(): ModelDbMapper<CachedNotification> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedBufferoo]
     */
    override fun toContentValues(model: CachedNotification): ContentValues {
        val values = ContentValues()
        values.put(Db.NotificationTable.NAME, model.name)
        values.put(Db.NotificationTable.TITLE, model.title)
        values.put(Db.NotificationTable.AVATAR, model.avatar)
        return values
    }

    /**
     * Parse the cursor creating a [CachedBufferoo] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedNotification {
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.NotificationTable.NAME))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.NotificationTable.TITLE))
        val avatar = cursor.getString(cursor.getColumnIndexOrThrow(Db.NotificationTable.AVATAR))
        return CachedNotification(name, title, avatar)
    }

}