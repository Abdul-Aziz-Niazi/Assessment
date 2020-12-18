package com.azeez.assessment.daos.responses

class APIResponse<T> {
    var isSuccessful: Boolean = false
    var data: T? = null
    var error: String? = null
    fun success(data: T?): APIResponse<T> {
        isSuccessful = true
        this.data = data
        return this
    }

    fun failure(error: T? = null, message: String?): APIResponse<T> {
        this.error = message
        return this
    }

}