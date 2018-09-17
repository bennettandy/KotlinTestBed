package com.avsoftware.kotlinapp

import android.test.MoreAsserts.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runners.JUnit4
import java.util.concurrent.atomic.AtomicInteger

class AlgorithmExamples {

    @Test
    fun binarySearch() {

        val values : IntArray = IntArray(10000){ i: Int ->  i }

        values.forEach { print("$it,") }
        println()

        val result = binarySearchRecursive( values, 17, 0, values.size - 1)

        println("Result $result")
    }

    private fun binarySearchRecursive( values : IntArray, x : Int, left : Int, right : Int) : Boolean {

        println("x=$x, left $left right $right")

        if (left > right ) return false

        val mid = (left + right) / 2

        if ( values[mid] == x ) return true

        if ( x < values[mid]) return binarySearchRecursive(values, x, left, mid - 1)

        if ( x > values[mid]) return binarySearchRecursive(values, x, mid + 1, right)

        return false
    }

    @Test
    fun labelsTest(){
        labelA@for (a in 1..100){
            labelB@for (b in 1..100){
                println("a$a b$b")
                if (a == 20) return@labelA
            }
        }
    }


    @Test
    fun givenLazyValue_whenGetIt_thenShouldInitializeItOnlyOnce() {
        // given
        val numberOfInitializations: AtomicInteger = AtomicInteger()
        val lazyValue: String by lazy {
            numberOfInitializations.incrementAndGet()
            "Hello"
            }
        // when
        println(lazyValue)
        println(lazyValue)

        // then
        Assert.assertEquals(numberOfInitializations.get(), 1)
    }

}