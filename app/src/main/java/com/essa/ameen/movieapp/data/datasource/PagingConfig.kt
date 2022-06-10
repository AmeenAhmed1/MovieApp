package com.essa.ameen.movieapp.data.datasource

import androidx.paging.PagingConfig
import com.essa.ameen.movieapp.core.util.PAGE_SIZE

val PAGE_CONFIG =
    PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )
