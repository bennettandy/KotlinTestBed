package com.avsoftware.data

import com.avsoftware.data.recipe.Recipe
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

class RecipeTest {

    @Test fun testRecipeId(){
        val url = "http://food2fork.com/view/33124"
        val id = url.substringAfterLast("/")
        assertEquals("33124", id)
    }
}