package org.weframe.kotlinresourcesserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class KotlinResourcesServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinResourcesServerApplication::class.java, *args)
}
