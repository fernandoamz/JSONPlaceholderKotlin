package com.example.jsonplaceholder.data.mapper

import com.example.jsonplaceholder.data.remote.CommentDto
import com.example.jsonplaceholder.domain.model.Comment

fun CommentDto.toDomain(): Comment {
    return Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body
    )
}
