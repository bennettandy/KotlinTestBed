package com.avsoftware.kotlinapp

const val ANOTHER_CONSTANT = "12345"

object SomeObject {

    const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

    @Deprecated(SUBSYSTEM_DEPRECATED, ReplaceWith("bar()"))
    fun foo() {  }

    fun bar() {}

    fun something(){
        foo()
    }
}