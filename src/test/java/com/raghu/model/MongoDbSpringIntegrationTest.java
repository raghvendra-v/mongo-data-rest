package com.raghu.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class MongoDbSpringIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("given object to save" + " when save object using MongoDB template" + " then object is saved")
	@Test
	@Disabled
	public void test(@Autowired MongoTemplate mongoTemplate) {
		// given
		DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

		// when
		mongoTemplate.save(objectToSave, "collection");

		// then
		assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key").containsOnly("value");
	}

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
		//TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder, username, password, httpClientOptions)
		//String responseFromTestRestTemplate = testRestTemplate.postForObject("/people", p, String.class);
		//assertNotNull(responseFromTestRestTemplate);

		ResultActions resultActions = mockMvc.perform(
				post("/people").content(objectMapper.writeValueAsString(p)).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		resultActions.andDo(mvcResult -> {
			String responseFromMockMvc = mvcResult.getResponse().getContentAsString();
		});

	}
}