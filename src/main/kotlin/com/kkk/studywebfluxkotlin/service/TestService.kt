package com.kkk.studywebfluxkotlin.service

import com.kkk.studywebfluxkotlin.dto.AuthorWithCards
import com.kkk.studywebfluxkotlin.dto.CardResponse
import com.kkk.studywebfluxkotlin.dto.CardWithAuthor
import com.kkk.studywebfluxkotlin.entity.Author
import com.kkk.studywebfluxkotlin.entity.Card
import com.kkk.studywebfluxkotlin.repository.AuthorRepository
import com.kkk.studywebfluxkotlin.repository.CardRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TestService(private val cardRepo: CardRepository, private val authRepo: AuthorRepository) {
    fun getAll(): Flux<CardResponse> {
//        아래 코드는 N+1문제 발생.. 매 카드마다 author 찾는 쿼리 날림
//        return cardRepo.findAll().flatMap { card ->
//            authRepo.findById(card.authorId).map { author -> CardResponse.from(card, author) }
//        }
        return authRepo.findAll()
            .collectMap { it.id!! }
            .flatMapMany { authorMap ->
                cardRepo.findAll().map { card ->
                    val author = authorMap[card.authorId]
                    CardResponse.from(
                        card, author ?: Author(
                            null,
                            name = "Unknown"
                        )
                    )
                }
            }
    }

    fun getById(id: Long): Mono<Card> = cardRepo.findById(id)

    fun createCard(card: Mono<Card>): Mono<Card> {
        return card.flatMap { cardRepo.save(it) }
    }

    fun deleteCardById(id: Long): Mono<Card> {
        return cardRepo.findById(id).flatMap { card -> cardRepo.delete(card).thenReturn(card) }
    }

    /**
     * 1:N 관계 (Author -> Card)
     */
    fun getAuthorWithCards(authorId: Long): Mono<AuthorWithCards> {
        return authRepo.findById(authorId).flatMap { author ->
            cardRepo.findAllByAuthorId(authorId)
                .collectList().map { card -> AuthorWithCards(author, card) }
        }
    }

    /// Mono로 한 번에 묶어서 보냄... 권장되지는 않음
    fun getAuthorWithCardsMono(authorIds: List<Long>): Mono<List<AuthorWithCards>> {
        val authors = authRepo.findAllById(authorIds).collectList()
        val cards = cardRepo.findAllByAuthorIdIn(authorIds).collectList()
        return Mono.zip(authors, cards) { aList, bList ->
            aList.map { author ->
                AuthorWithCards(author, bList.filter { it.authorId == author.id })
            }
        }
    }

    /// Flux로 스트리밍 전송
    fun getAuthorWithCardsFlux(authorIds: List<Long>): Flux<AuthorWithCards> {
        return authRepo.findAllById(authorIds)
            .flatMap { author ->
                cardRepo.findAllByAuthorId(author.id!!).collectList()
                    .map { cards -> AuthorWithCards(author, cards) }
            }
    }

    /**
     * N:1 관계 (Card -> Author)
     */
    fun getCardWithAuthor(cardId: Long): Mono<CardWithAuthor> {
        return cardRepo.findById(cardId)
            .flatMap { card ->
                authRepo.findById(card.authorId)
                    .map { author -> CardWithAuthor(card, author) }
            }
    }
}
