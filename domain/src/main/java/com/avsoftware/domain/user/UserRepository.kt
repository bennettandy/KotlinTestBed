package com.avsoftware.domain.user

import io.reactivex.Observable

interface  UserRepository {
    fun getUsers(): Observable<List<User>>
}

class User( userName: String, val date: Int = 0 ){

    var userName: String = ""
    set(value) { field = value }
}

object UserSingleton {
    var count: Int = 0;
}