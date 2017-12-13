package com.friendalert.shivangshah.cache

/**
 * Created by shivangshah on 12/13/17.
 */
interface EntityMapper<T, V> {

    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T

}