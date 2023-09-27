package com.example.animations.ui.screens.lazy_list

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.animations.data.models.Item
import com.example.animations.ui.theme.*
import kotlin.random.Random

class LazyListViewModel : ViewModel() {

    private val colors = listOf(
        Orange,
        Pink,
        Green,
        Lilac,
        Blue
    )

    private val listOfItems = List(10) { newItem(it) }
    private val _listItems: SnapshotStateList<Item> = listOfItems.toMutableStateList()
    val listItems: SnapshotStateList<Item> = _listItems

    private fun newItem(index: Int) = Item(
        id = index,
        label = "Item $index",
        color = colors[index % colors.size],
    )

    fun shuffleList() {
        _listItems.shuffle()
    }

    fun addItem() {
        val newIndex = if (_listItems.isEmpty()) 0 else _listItems.maxOf { it.id } + 1
        _listItems.add(
            index = Random.nextInt(_listItems.size),
            element = newItem(newIndex)
        )
    }

    fun removeItem() {
        val item = _listItems.first { item ->
            item.id == _listItems.maxOf { it.id }
        }
        _listItems.remove(item)
    }
}