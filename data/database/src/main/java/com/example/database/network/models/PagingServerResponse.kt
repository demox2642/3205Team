package com.example.database.network.models

data class PagingServerResponse<T : Any>(
    val incomplete_results: Boolean,
    val items: List<T>,
    val total_count: Int,
)
