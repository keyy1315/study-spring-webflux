package com.kkk.studywebfluxkotlin.repository

import com.kkk.studywebfluxkotlin.entity.Author
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : ReactiveCrudRepository<Author, Long> {
}