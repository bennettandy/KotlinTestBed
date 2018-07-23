package com.avsoftware.kotlinapp

class CustA( val name: String = "fred" ) {

    var stringRepresentation: String = "acb"
        get() = this.toString()
        set(value) {
            //setDataFromString(value) // parses the string and assigns values to other properties
            field = processString(value)
        }


    companion object {
        fun processString( inString: String) = inString.capitalize()
    }
}