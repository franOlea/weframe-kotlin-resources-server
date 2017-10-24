package org.weframe.kotlinresourcesserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinResourcesServerApplication {

    fun main(args: Array<String>) {
        SpringApplication.run(KotlinResourcesServerApplication::class.java, *args)
    }

}