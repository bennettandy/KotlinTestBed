package com.avsoftware.kotlinapp

import org.junit.Test

open class ABase(val name: String) {

    init { println("Initializing Base") }

    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class ADerived(
        name: String,
        val lastName: String
) : ABase(name.capitalize().also { println("Argument for Base: $it") }) {

    init { println("Initializing Derived") }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

open class AFoo {
    open fun f() { println("Foo.f()") }
    open val x: Int get() = 1
}

class Bar : AFoo() {
    override fun f() { /* ... */ }
    override val x: Int get() = 0

    inner class Baz {
        fun g() {
            super@Bar.f() // Calls Foo's implementation of f()
            println(super@Bar.x) // Uses Foo's implementation of x's getter
        }
    }
}


class TestingInitOrder {

    @Test
    fun testInitOrder(){
        val item = ADerived("fred", "smith")
    }
}