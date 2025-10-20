### Flux

- 0개 이상, 무한개까지 데이터 스트림
- Flux<String> : 문자열 여러개
- 리스트 조회, 이벤트 스트림, 대용량 DB 결과

---

- 0 ~ N개의 연속 데이터 처리 가능

```kotlin
Flux.just(1, 2, 3)
Flux.fromIterable(listOf(1, 2, 3))
Flux.empty<Int>() // 값 없음
```

### Mono

- 0개 또는 1개의 데이터
- Mono<String> : 단일 문자열
- 단일 DB 조회, 단일 HTTP 호출, 계산 결과

---

- 단일 결과 또는 empty 반환

```kotlin
Mono.just("hello") // 단일 값
Mono.empty<String>() // 값 없음
Mono.error(RuntimeException()) // 에러 전달
```

- 한 번 발행되면 끝나는 구조

### Mono -> Flux

```kotlin
val mono: Mono<Card> = repository.findById(1)
val flux: Flux<Card> = mono.flux()
```

### Flux -> Mono

```kotlin
val flux: Flux<Card> = repository.findAll()
val mono: Mono<List<Card>> = flux.collectList()
```


### 연산 체인
**Mono**
```kotlin
repository.findById(1)
    .map { it.copy(name = it.name.uppercase()) }
    .flatMap { cardRepo.save(it) }   // save 후 Mono<Card> 반환
    .subscribe { println("Saved card: $it") }
```

**Flux**
```kotlin
repository.findAll()
    .filter { it.active }
    .map { it.copy(name = it.name.uppercase()) }
    .flatMap { cardRepo.save(it) }  // 각 요소마다 저장
    .subscribe { println("Saved card: $it") }
```