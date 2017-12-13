package com.friendalert.shivangshah.domain.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject
import com.friendalert.shivangshah.domain.PostExecutionThread;
import com.friendalert.shivangshah.domain.ThreadExecutor;
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel

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
            user: User -> notificationRepository.getNotifications(user.userId) }
    }

}