package com.boonapps.repos.models

import com.squareup.moshi.Json

data class Repo(
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "owner")
    val owner: Owner,
    @field:Json(name = "stargazers_count")
    val stars: String,
    val language: String
) {

    data class Owner(
        @Json(name = "login")
        val login: String,
        @field:Json(name = "avatar_url")
        val avatarUrl: String?
    )
}
