package com.friendalert.shivangshah.cache

import android.database.sqlite.SQLiteDatabase
import com.friendalert.shivangshah.cache.db.Db
import com.friendalert.shivangshah.cache.db.DbOpenHelper
import com.friendalert.shivangshah.cache.db.constants.NotificationConstants
import com.friendalert.shivangshah.cache.db.mapper.NotificationMapper
import com.friendalert.shivangshah.cache.mapper.NotificationEntityMapper
import com.friendalert.shivangshah.cache.model.CachedNotification
import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.data.repository.NotificationCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/14/17.
 */
class NotificationsCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                                 private val entityMapper: NotificationEntityMapper,
                                                 private val mapper: NotificationMapper,
                                                 private val preferencesHelper: PreferencesHelper):
        NotificationCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }


    override fun clearNotifications(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.NotificationTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }    }

    override fun saveNotifications(notifications: List<NotificationEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                notifications.forEach {
                    saveNotification(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Helper method for saving a [CachedBufferoo] instance to the database.
     */
    private fun saveNotification(cachedNotification: CachedNotification) {
        database.insert(Db.NotificationTable.TABLE_NAME, null, mapper.toContentValues(cachedNotification))
    }

    override fun getNotifications(): Single<List<NotificationEntity>> {
        return Single.defer<List<NotificationEntity>> {
            val updatesCursor = database.rawQuery(NotificationConstants.QUERY_GET_ALL_NOTIFICATIONS, null)
            val bufferoos = mutableListOf<NotificationEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedBufferoo = mapper.parseCursor(updatesCursor)
                bufferoos.add(entityMapper.mapFromCached(cachedBufferoo))
            }

            updatesCursor.close()
            Single.just<List<NotificationEntity>>(bufferoos)
        }
    }

    override fun isCached(): Boolean {
        return database.rawQuery(NotificationConstants.QUERY_GET_ALL_NOTIFICATIONS, null).count > 0
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}