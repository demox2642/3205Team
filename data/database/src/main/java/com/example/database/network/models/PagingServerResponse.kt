package com.example.database.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingServerResponse<T : Any>(
    @SerialName("incomplete_results")
    val result: Boolean,
    val items: List<T>,
    @SerialName("total_count")
    val totalCount: Int,
)
