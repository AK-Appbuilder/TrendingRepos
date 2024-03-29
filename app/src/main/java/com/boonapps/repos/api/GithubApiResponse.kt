package com.boonapps.repos.api

import com.boonapps.repos.models.Repo
import com.squareup.moshi.Json

data class GithubApiResponse (
   @Json(name ="total_count")
   val  totalCount: Long,
   val  items: List<Repo>
)