package com.kkk.studywebfluxkotlin.dto

import com.kkk.studywebfluxkotlin.entity.Author
import com.kkk.studywebfluxkotlin.entity.Card

data class CardWithAuthor(
    val card: Card,
    val author: Author,
)
