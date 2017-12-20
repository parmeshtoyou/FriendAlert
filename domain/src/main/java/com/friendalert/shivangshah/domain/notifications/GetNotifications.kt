package com.friendalert.shivangshah.domain.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject
import com.friendalert.shivangshah.domain.PostExecutionThread;
import com.friendalert.shivangshah.domain.ThreadExecutor;
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import java.util.concurrent.TimeUnit

/**
 * Use case used for retreiving a [List] of [Notification] instances from the [NotificationRepository]
 */
open class GetNotifications @Inject constructor(val notificationRepository: NotificationRepository,
                                                val userRepository: UserRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread):
        SingleUseCase<NotificationResponseModel, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<NotificationResponseModel> {

        return userRepository.getUser().flatMap{
            user: User -> notificationRepository.getReadNotifications().flatMap {
                readList: ArrayList<String> -> notificationRepository.getNotifications(user.userId).map {
                    t: NotificationResponseModel ->  markRemoteNotificationsAsRead(t, readList)
                }.map {
                    t: NotificationResponseModel ->  convertTimestampToTimeAgo(t)
                }
            }
        }

    }

    private fun markRemoteNotificationsAsRead(notificationResponseModel: NotificationResponseModel, readIdList : ArrayList<String>) : NotificationResponseModel{

        for (notification in notificationResponseModel.data){

            notification.isRead = readIdList.contains(notification.broadcast_id)

        }

        return notificationResponseModel

    }

    private fun convertTimestampToTimeAgo(notificationResponseModel: NotificationResponseModel) : NotificationResponseModel{

        for(notification in notificationResponseModel.data){

            var currentTime = System.currentTimeMillis()
            var notificationTime = notification.timestamp.toLong()

            var difference = currentTime - notificationTime

            var days = TimeUnit.MILLISECONDS.toDays(difference)
            var hours = TimeUnit.MILLISECONDS.toHours(difference)
            var minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
            var seconds = TimeUnit.MILLISECONDS.toSeconds(difference)

            var timestampText = ""

            if(days > 0){
                timestampText = days.toString() + if(days.toInt() == 1)  " day ago" else " days ago"
            }else{
                if(hours > 0){
                    timestampText = hours.toString() + if(hours.toInt() == 1)  " hour ago" else " hours ago"
                }else{
                    if(minutes > 0){
                        timestampText = minutes.toString() + if(minutes.toInt() == 1)  " minute ago" else " minutes ago"
                    }else{
                        timestampText = seconds.toString() + if(seconds.toInt() == 1)  " second ago" else " seconds ago"
                    }
                }
            }

            notification.timestamp = timestampText

        }

        return notificationResponseModel

    }

}