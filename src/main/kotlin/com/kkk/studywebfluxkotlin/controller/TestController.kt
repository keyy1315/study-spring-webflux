package com.kkk.studywebfluxkotlin.controller

import com.kkk.studywebfluxkotlin.service.TestService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TestController(private val service: TestService) {

}