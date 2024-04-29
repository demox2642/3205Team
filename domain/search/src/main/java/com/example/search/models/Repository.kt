package com.example.search.models

data class Repository(
    val id: Long,
    val name: String,
    val defaultBranch: String,
    val htmlUrl: String,
)
