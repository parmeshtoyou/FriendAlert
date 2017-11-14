package com.friendalert.shivangshah.domain.interactor.notifications

import com.friendalert.shivangshah.domain.interactor.SingleUseCase
import com.friendalert.shivangshah.domain.model.Notification
import com.friendalert.shivangshah.domain.repository.NotificationRepository
import io.reactivex.Single
import javax.inject.Inject
import com.friendalert.shivangshah.domain.executor.PostExecutionThread;
import com.friendalert.shivangshah.domain.executor.ThreadExecutor;

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