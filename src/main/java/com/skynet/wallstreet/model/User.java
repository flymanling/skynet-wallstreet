package com.skynet.wallstreet.model;

import javax.persistence.Entity;

@Entity(name="user")
public class User extends BaseEntity {

	private static final long serialVersionUID = -1256467351650877240L;

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
