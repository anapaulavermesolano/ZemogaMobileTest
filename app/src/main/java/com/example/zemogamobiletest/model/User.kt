package com.example.zemogamobiletest.model

import java.io.Serializable

data class User(
    val name: String,
    val email: String,
    val phone: String
): Serializable