package com.ms;

import com.fasterxml.jackson.databind.JsonNode;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EcomApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void whenGetProductsThenReturnProductsList() {
		JsonNode response = this.restTemplate.getForObject("/products", JsonNode.class);
		assertThat(response.isArray()).isTrue();
	}

	@Test
    public void whenFindByProductSKUThenReturnProduct(){
	    String sku = "PID12345";
        JsonNode response = this.restTemplate.getForObject("/product/"+sku, JsonNode.class);
        assertThat(response.get("sku").textValue()).isEqualTo(sku);
    }

    @Test
    public void whenFindProductByCategoryLikeThenReturnProducts(){
        String category="electronics";
        JsonNode response = this.restTemplate.getForObject("/products?category="+category, JsonNode.class);
        assertThat(response.get(0).get("category").textValue()).contains(category);
    }

}
