package com.raghu.model;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class Person {
	 @Id private String id;
	 
	 @Indexed(name = "name_index", direction = IndexDirection.DESCENDING, unique = true)
	 private String name;
	 private Date dateOfBirth;
	 private Date dateOfDeath;
	 private String placeOfBirth;
	 private String email;
	 private Binary profileImage;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Binary getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Binary profileImage) {
		this.profileImage = profileImage;
	}
	 
}
