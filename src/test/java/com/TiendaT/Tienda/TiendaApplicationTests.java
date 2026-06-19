package com.TiendaT.Tienda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TiendaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void main() {
		TiendaApplication.main(new String[]{
			"--spring.datasource.url=jdbc:h2:mem:mainAppTestDB",
			"--server.port=0"
		});
	}

}
