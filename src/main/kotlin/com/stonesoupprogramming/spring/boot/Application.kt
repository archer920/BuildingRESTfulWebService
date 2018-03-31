package com.stonesoupprogramming.spring.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

/**
 * This class gets converted into JSON and serves as our data model.
 * We can use Kotlin's data class feature to make it only one line of code.
 */
data class Greeting (val id : Long, val content : String)

/**
 * The @RestController annotation tells the Spring Environment to
 * use this class to handle REST requests. That means that it will handle
 * HTTP requests but does not use a view technology to write the response.
 * Instead, an instance of Greeting is simply converted into JSON and written
 * to the HTTP response body.
 */
@RestController
class GreetingController {

    private val counter : AtomicLong = AtomicLong()

    /**
     * The @RequestMapping signals that this method will handle
     * HTTP requests to /greeting. We can narrow it down to GET, POST, PUT, etc
     * when we want different methods to handle different requests
     * at this endpoint.
     *
     * The name parameter is annotated with @RequestParam which has
     * two arguments. The name argument maps the request parameter name to
     * the name argument in this method. The defaultValue will populate
     * name with the value "World" if the request does not have a name argument.
     */
    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value="name", defaultValue="World") name : String) : Greeting {
        return Greeting(counter.incrementAndGet(), "Hello $name")
    }
}

/**
 * The @SpringBootApplication is a meta annotation that makes
 * this application executable.
 */
@SpringBootApplication
open class Application

/**
 * Now we just need an entry point to the program.
 */
fun main(args : Array<String>){
    SpringApplication.run(Application::class.java, *args)
}