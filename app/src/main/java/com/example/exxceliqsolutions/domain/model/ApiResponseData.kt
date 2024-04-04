package com.example.exxceliqsolutions.domain.model

data class ApiResponseData(
    val data: List<UserData>,
    val page: Int,
    val perPage: Int,
    val support: SupportData,
    val total: Int,
    val totalPages: Int
)

data class SupportData(
    val text: String,
    val url: String
)