package com.example.exxceliqsolutions.data.mapper

import com.example.exxceliqsolutions.data.model.ApiResponse
import com.example.exxceliqsolutions.data.model.Support
import com.example.exxceliqsolutions.data.model.Data
import com.example.exxceliqsolutions.data.util.safeMap
import com.example.exxceliqsolutions.domain.model.ApiResponseData
import com.example.exxceliqsolutions.domain.model.SupportData
import com.example.exxceliqsolutions.domain.model.UserData

fun ApiResponse.toApiResponseData(): ApiResponseData {
    return ApiResponseData(
        data = data?.toUserData() ?: emptyList(),
        page = page ?: 1,
        perPage = perPage ?: 0,
        support = support.toSupportData(),
        total = total ?: 0,
        totalPages = totalPages ?: 0
    )
}

fun List<Data>.toUserData(): List<UserData> {
    return safeMap {
        UserData(
            avatar = it.avatar.orEmpty(),
            email = it.email.orEmpty(),
            firstName = it.firstName.orEmpty(),
            id = it.id ?: 0,
            lastName = it.lastName.orEmpty()
        )
    }
}

fun Support?.toSupportData(): SupportData {
    return SupportData(text = this?.text.orEmpty(), url = this?.url.orEmpty())
}