package com.boonapps.repos.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.security.acl.Owner

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
    val stargazers_count: Long,
    val language: String
) {


    data class Owner(
        @Json(name = "login")
        val login: String,
        val avatar_url: String?
    )

    fun getRatings() = stargazers_count.toString()

}
