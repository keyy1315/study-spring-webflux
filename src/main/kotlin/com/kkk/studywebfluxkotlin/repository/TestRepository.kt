package com.kkk.studywebfluxkotlin.repository

import com.kkk.studywebfluxkotlin.entity.Card
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : ReactiveCrudRepository<Card, String> {
}