package com.example.animations.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animations.domain.repository.Repository

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory (
    private val repository: Repository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}