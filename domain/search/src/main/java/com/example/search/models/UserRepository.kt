package com.example.search.models

data class UserRepository(
    val id: Long,
    val name: String,
    val avatar: String,
    val repository: List<Repository>,
)
