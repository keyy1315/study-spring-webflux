package com.kkk.studywebfluxkotlin.dto

import com.kkk.studywebfluxkotlin.entity.Author
import com.kkk.studywebfluxkotlin.entity.Card

data class AuthorWithCards(
    val author: Author,
    val cards: List<Card>,
)