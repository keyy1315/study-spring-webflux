package com.kkk.studywebfluxkotlin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cards")
data class Card(@Id val id: Long, val title: String, val description: String)