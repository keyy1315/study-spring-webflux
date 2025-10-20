package com.kkk.studywebfluxkotlin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "authors")
data class Author(
    @Id val id: Long? = null,
    val name: String,
)