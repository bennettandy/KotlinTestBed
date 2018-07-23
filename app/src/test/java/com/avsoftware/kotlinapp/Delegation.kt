package com.avsoftware.kotlinapp

import org.junit.Test
import kotlin.properties.Delegates

interface XBase {
    fun printMessage()
    fun printMessageLine()
}

class XBaseImpl(val x: Int) : XBase {
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}

class XDerived(b: XBase) : XBase by b {
    override fun printMessage() { print("abc") } // override function implemented by delegate b
}

class Fred {
    @Test
    fun test() {
        val b = XBaseImpl(10)
        XDerived(b).printMessage()
        XDerived(b).printMessageLine()
    }

}


class UserX {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }

    @Test
    fun test(){
        val user = UserX()
        user.name = "first"
        user.name = "second"
    }

}


// properties by map
class PropsByMap( val map: Map<String, Any?>){
    val name: String by map
    val city: String by map
}

fun mapTest(){
    val map = mapOf("name" to "John Smith", "city" to "London")
    val obj = PropsByMap(map)
}