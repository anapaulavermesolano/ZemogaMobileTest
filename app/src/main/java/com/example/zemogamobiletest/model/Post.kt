package com.example.zemogamobiletest.model

import java.io.Serializable

data class Post(
    val id: Int,
    val title: String,
    val isFavorite: Boolean,
    val isOpen: Boolean,
    val description: String,
    val user: User
) : Serializable