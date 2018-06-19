package avsoftware.com.data.user

import avsoftware.com.domain.user.User
import avsoftware.com.domain.user.UserRepository
import io.reactivex.Observable

class UserRepositoryImpl: UserRepository {

    override fun getUsers(): Observable<List<User>> {

        val list = listOf(User("user a"), User("user b"))

        return Observable.just(list)
    }

}