package com.friendalert.shivangshah.data.repository

import com.friendalert.shivangshah.data.model.NotificationEntity
import io.reactivex.Completable
import io.reactivex.Single


/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface NotificationCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearNotifications(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun saveNotifications(notifications: List<NotificationEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getNotifications(): Single<List<NotificationEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}