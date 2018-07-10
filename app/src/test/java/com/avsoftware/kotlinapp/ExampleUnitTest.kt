package com.avsoftware.kotlinapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_quacks(){
        val mallard = Duck(DoubleQuackDelegate())
        val moorHen = Duck(SqueekQuackDelegate())

        mallard.quack()
        moorHen.quack()
    }
}

interface Quack {
    fun quack(): Unit
}

class DoubleQuackDelegate() : Quack {
    override fun quack(){
        println("Quack Quack")
    }
}

class SqueekQuackDelegate() : Quack {
    override fun quack() {
        println("Squeek")
    }
}

class Duck( val quackDelegate: Quack){
    fun quack(){
        quackDelegate.quack()
    }
}