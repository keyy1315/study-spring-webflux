package com.kkk.studywebfluxkotlin.repository

import com.kkk.studywebfluxkotlin.entity.Card
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CardRepository : ReactiveCrudRepository<Card, Long> {
    fun findAllByAuthorId(authorId: Long): Flux<Card>
    fun findAllByAuthorIdIn(authorIds: List<Long>): Flux<Card>
}