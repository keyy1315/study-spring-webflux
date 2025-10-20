package com.kkk.studywebfluxkotlin

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "AWS SDK", version = "1.0.0"))
class StudyWebfluxKotlinApplication

fun main(args: Array<String>) {
    runApplication<StudyWebfluxKotlinApplication>(*args)
}
