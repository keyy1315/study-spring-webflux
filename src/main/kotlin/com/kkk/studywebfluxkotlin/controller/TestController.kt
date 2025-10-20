package com.kkk.studywebfluxkotlin.controller

import com.kkk.studywebfluxkotlin.dto.AuthorWithCards
import com.kkk.studywebfluxkotlin.dto.CardWithAuthor
import com.kkk.studywebfluxkotlin.entity.Card
import com.kkk.studywebfluxkotlin.service.TestService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class TestController(private val service: TestService) {

    @Operation(summary = "Get all the cards")
    @GetMapping
    fun allCards(): Flux<Card> = service.getAll()

    @Operation(summary = "Get card By card pk")
    @GetMapping("/{id}")
    fun getCardById(@PathVariable id: Long): Mono<Card> = service.getById(id)

    @Operation(summary = "Create a new card")
    @PostMapping
    fun createNewCard(@RequestBody card: Mono<Card>): Mono<Card> {
        return service.createCard(card);
    }

    @Operation(summary = "Delete a card")
    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable id: Long): Mono<Card> {
        return service.deleteCardById(id)
    }

    @Operation(summary = "Get all Cards By Author ID")
    @GetMapping("/author/{id}")
    fun allCardsByAuthorId(@PathVariable id: Long): Mono<AuthorWithCards> {
        return service.getAuthorWithCards(id)
    }

    @Operation(summary = "Get Author With Card")
    @GetMapping("/card/{id}")
    fun getCardAuthorById(@PathVariable id: Long): Mono<CardWithAuthor> {
        return service.getCardWithAuthor(id)
    }

    @Operation(summary = "Get Cards By Author ID With Mono")
    @GetMapping("/author/mono")
    fun getCardMonoById(@RequestParam ids: List<Long>): Mono<List<AuthorWithCards>> {
        return service.getAuthorWithCardsMono(ids);
    }

    @Operation(summary = "Get Cards By Author ID With Flux")
    @GetMapping("/author/flux")
    fun getCardFluxById(@RequestParam ids: List<Long>): Flux<AuthorWithCards> {
        return service.getAuthorWithCardsFlux(ids);
    }
}