package com.friendalert.shivangshah.domain.notifications

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import com.sun.org.apache.xpath.internal.operations.Bool
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/18/17.
 */
class MarkAsRead @Inject constructor(val notificationRepository: NotificationRepository,
                                     threadExecutor: ThreadExecutor,
                                     postExecutionThread: PostExecutionThread):
        SingleUseCase<Boolean, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(notificationId: String?): Single<Boolean> {

        return notificationRepository.markAsRead(notificationId!!)

    }
}