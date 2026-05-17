package com.easybroker.project;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = EasybrokerApplication.class)
@ActiveProfiles("test")
class EasybrokerApplicationTests {

	@Test
	@Disabled("Context load test disabled for CI")
	void contextLoads() {
	}

}
