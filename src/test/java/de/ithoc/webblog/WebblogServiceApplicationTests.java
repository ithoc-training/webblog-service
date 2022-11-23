package de.ithoc.webblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = { "DB_USERNAME=username", "DB_PASSWORD=password" }
)

class WebblogServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
