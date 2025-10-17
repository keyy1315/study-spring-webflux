package com.kkk.studywebfluxkotlin.service

import com.kkk.studywebfluxkotlin.entity.Card
import com.kkk.studywebfluxkotlin.repository.TestRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TestService(private val repo: TestRepository) {
    fun getAll(): Flux<Card> = repo.findAll()
}