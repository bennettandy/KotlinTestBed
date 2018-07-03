package com.avsoftware.domain.user

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

class UserRepositoryTest {

    @Test
    fun testUser() {
        val user = User("name")

        user.userName = "TEST"

        assertEquals( 0, user.date)

        assertNotNull(user)

        assertEquals( 0, UserSingleton.count)

        UserSingleton.count = 23

        assertEquals(23, UserSingleton.count)
    }
}
