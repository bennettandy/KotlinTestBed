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


class TestingInitOrder {

    @Test
    fun testInitOrder(){
        val item = ADerived("fred", "smith")
    }
}