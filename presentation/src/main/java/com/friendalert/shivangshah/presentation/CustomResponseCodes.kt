package com.friendalert.shivangshah.presentation

class CustomResponseCodes {

    companion object {
        val getSuccess : Int = 101
        val createSuccess : Int = 102
        val alreadyExists : Int = 103
        val updateSuccess : Int = 104
        val deleteSuccess : Int = 105

        val updateFailure : Int = 306
        val createFailure : Int = 308
        val invalidParamus : Int = 307
    }

}