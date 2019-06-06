package com.tripleThreads.taxiyaz.Repository

import androidx.annotation.WorkerThread
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.data.user.UserDao

class UserRepository(private val dao: UserDao) {

    @WorkerThread
    fun getUser(): User {
        return dao.getUser()
    }

    @WorkerThread
    fun insert(user: User) {
        dao.insert(user)
    }

    @WorkerThread
    fun update(user: User) {
        dao.update(user)
    }

    @WorkerThread
    fun delete(user: User) {
        dao.delete(user)
    }

}