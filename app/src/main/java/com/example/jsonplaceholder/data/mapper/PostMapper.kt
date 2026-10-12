package com.example.jsonplaceholder.data.mapper

import com.example.jsonplaceholder.data.remote.PostDto
import com.example.jsonplaceholder.domain.model.Post

fun PostDto.toDomain(): Post  {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
