package com.avsoftware.domain.user

import io.reactivex.Observable

interface  UserRepository {
    fun getUsers(): Observable<List<User>>
}

data class User( val userName: String )