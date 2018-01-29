package com.ms;

import com.fasterxml.jackson.databind.JsonNode;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EcomApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void productsTest() {
		JsonNode response = this.restTemplate.getForObject("/products", JsonNode.class);
		System.out.print(response);
		assertThat(response).isOfAnyClassIn(ArrayList.class);
	}

}
