package com.avsoftware.data

import junit.framework.Assert.assertEquals
import org.junit.Test

class RecipeTest {

    @Test fun testRecipeId(){
        val url = "http://food2fork.com/view/33124"
        val id = url.substringAfterLast("/")
        assertEquals("33124", id)
    }
}