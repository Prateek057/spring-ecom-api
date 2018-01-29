package com.ms;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryAPITests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetCategoriesThenReturnCategoriesList() {
        JsonNode response = this.restTemplate.getForObject("/categories", JsonNode.class);
        assertThat(response.isArray()).isTrue();
    }

    @Test
    public void whenFindByCategoryIdThenReturnCategory(){
        String id = "CAT12345";
        JsonNode response = this.restTemplate.getForObject("/category/"+id, JsonNode.class);
        assertThat(response.get("id").textValue()).isEqualTo(id);
    }

    @Test
    public void whenFindCategoryBySlugLikeThenReturnCategoriesList(){
        String slug="electronics";
        JsonNode response = this.restTemplate.getForObject("/category/bySlug/"+slug, JsonNode.class);
        System.out.print(response);
        assertThat(response.get(0).get("slug").textValue()).contains(slug);
    }
}
