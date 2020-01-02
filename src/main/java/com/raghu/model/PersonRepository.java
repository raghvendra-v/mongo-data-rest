package com.raghu.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {
  List<Person> findByName(@Param("name") String name);
  List<Person> findByDateOfBirth(@Param("dateOfBirth") String dateOfBirth);
  List<Person> findByEmail(@Param("email") String email);
}