package com.friendalert.shivangshah.domain.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject
import com.friendalert.shivangshah.domain.PostExecutionThread;
import com.friendalert.shivangshah.domain.ThreadExecutor;

/**
 * Use case used for retreiving a [List] of [Notification] instances from the [NotificationRepository]
 */
open class GetNotifications @Inject constructor(val notificationRepository: NotificationRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Notification>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<Notification>> {
        return notificationRepository.getNotifications()
    }

}