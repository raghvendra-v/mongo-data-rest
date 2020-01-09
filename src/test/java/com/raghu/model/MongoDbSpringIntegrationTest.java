package com.raghu.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/*
 * 
 * //TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder, username, password, httpClientOptions)
		//String responseFromTestRestTemplate = testRestTemplate.postForObject("/people", p, String.class);
		//assertNotNull(responseFromTestRestTemplate);

		ResultActions resultActions = mockMvc.perform(
				post("/people").content(objectMapper.writeValueAsString(p)).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		resultActions.andDo(mvcResult -> {
			String responseFromMockMvc = mvcResult.getResponse().getContentAsString();
		});
 */
@DataMongoTest
public class MongoDbSpringIntegrationTest {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PersonRepository personRepository;

	@Test
	@DisplayName("Save Marilyn Monroe")
	public void testInsert() throws Exception {
		Person p = new Person();
		p.setName("Marilyn Monroe");
		p.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("01/06/1926"));
		p.setDateOfDeath(new SimpleDateFormat("dd/MM/yyyy").parse("04/08/1962"));
		p.setEmail("marilyn.monroe@yandex.ru");
		p.setPlaceOfBirth("Los Angeles, California, U.S.");
		byte[] bytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("Monroecirca1953.jpg").toURI()));
		p.setProfileImage(new Binary(BsonBinarySubType.USER_DEFINED, bytes));
		mongoTemplate.save(p);

		List<Person> found = personRepository.findByName("Marilyn Monroe");

		// then
		assertThat(found.get(0).getName()).isEqualTo("Marilyn Monroe");

	}
}