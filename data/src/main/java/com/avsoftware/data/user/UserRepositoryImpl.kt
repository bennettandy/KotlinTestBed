package com.avsoftware.data.user

import com.avsoftware.domain.user.User
import com.avsoftware.domain.user.UserRepository
import io.reactivex.Observable

class UserRepositoryImpl: UserRepository {

    override fun getUsers(): Observable<List<User>> {

        val list = listOf(User("user a"), User("user b"))

        return Observable.just(list)
    }

}