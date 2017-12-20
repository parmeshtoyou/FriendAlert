package com.friendalert.shivangshah.presentation

/**
 * Created by shivangshah on 11/11/17.
 */
interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

    fun showSuccess()

    fun showFailure(firstTime: Boolean, errorMessage: String)

    fun showProgress()

    fun hideProgress()

}