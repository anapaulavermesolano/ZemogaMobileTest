package com.example.zemogamobiletest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zemogamobiletest.model.CommentsRepository

class CommentsViewModelFactory(private val repository: CommentsRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentsViewModel(repository) as T
    }
}