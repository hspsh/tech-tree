package sh.hsp.techtree

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class TechTreeApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun examples() {
        // KoTest
        "String" shouldBe "String"
        "String".shouldBe("String")

        // JUnit5 assertions or kotlin-test xD
        assertEquals("String", "String")
    }

}
