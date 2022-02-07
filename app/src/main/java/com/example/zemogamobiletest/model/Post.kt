package com.example.zemogamobiletest.model

import androidx.room.Entity
import java.io.Serializable

@Entity
data class Post(
    val id: Int,
    val title: String,
    val isFavorite: Boolean,
    val isOpen: Boolean,
    val description: String,
    val user: User
) : Serializable