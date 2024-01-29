package sh.hsp.techtree

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TechTreeApplication

fun main(args: Array<String>) {
	runApplication<TechTreeApplication>(*args)
}
