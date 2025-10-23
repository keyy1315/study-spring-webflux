package com.kkk.studywebfluxkotlin.dto

import com.kkk.studywebfluxkotlin.entity.Author
import com.kkk.studywebfluxkotlin.entity.Card

data class CardResponse(
    val authorName: String,
    val title: String,
    val description: String,
    val iconUrl: String,
    val color: String,
    val id: Long
) {
    companion object {
        fun from(card: Card, author: Author): CardResponse = CardResponse(
            authorName = author.name,
            title = card.title,
            description = card.description,
            iconUrl = card.iconUrl,
            color = card.color,
            id = card.id!!
        )
    }
}
