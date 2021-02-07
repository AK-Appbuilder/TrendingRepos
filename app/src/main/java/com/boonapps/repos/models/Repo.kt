package com.boonapps.repos.models

import com.squareup.moshi.Json
import java.security.acl.Owner

data class Repo(
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "description")
    val description: String?,
    @Json(name= "owner")
    val owner: Owner,
    @Json(name = "stargazers_count")
    val stars: Int,
    val language: String
) {

    data class Owner(
        @Json(name = "login")
        val login: String,
        @Json(name = "url")
        val url: String?
    )

    companion object {
        const val UNKNOWN_ID = -1
    }
}
