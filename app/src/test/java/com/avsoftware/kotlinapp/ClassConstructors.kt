package com.avsoftware.kotlinapp

import org.junit.Test

// Person class with primary constructor
class Person(val name: String) {
    // secondary constructor
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }

    private val children = mutableListOf<Person>()
}


class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

class ConstructorTestClass {
    @Test
    fun constructorTest() {
        val constructors = Constructors(2)
    }
}

// Private Constructor
class DontCreateMe private constructor() {

    constructor( a: Int) : this()
}

// Extending Base Class

open class Base {
    open fun v() {}
    fun nv() {}
}
class Derived() : Base() {
    override fun v() {}
}

// property override
open class Foo {
    open val x = 1
        get() { return field }
}

class Bar1 : Foo() {
    override val x: Int = 5
}


fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)

    val comp: Comparator<Int> = object : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
