package com.boonapps.repos.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity
data class Repo(
    @PrimaryKey
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String?,
    @Json(name = "description")
    val description: String?,
    @Embedded
    @Json(name = "owner")
    val owner: Owner,
    @field:Json(name = "stargazers_count")
    val stars: String?,
    val language: String?
) {

    data class Owner(
        @Json(name = "login")
        val login: String,
        @field:Json(name = "avatar_url")
        val avatarUrl: String?,
        @Embedded
        val link: SelfLink?
    )

    data class SelfLink(
            val href: String
    ) : Serializable
}
