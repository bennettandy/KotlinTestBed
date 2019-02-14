package com.avsoftware.kotlinapp

import android.support.v7.util.DiffUtil
import org.junit.Test


class DiffUtilTest {

    @Test
    fun diffUtilTest() : Unit {

        val listA = listOf<String>("aaa", "bbb", "ccc", "ddd")
        val listB = listOf<String>("ddd", "bbb")

        val diffResult = DiffUtil.calculateDiff(DiffCallback(listA, listB))

        println("${diffResult.toString()}")
    }
}

class DiffCallback(val newItems : List<String>, val oldItems : List<String>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems.get(oldItemPosition) == newItems.get(newItemPosition);
    }

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
    }

}