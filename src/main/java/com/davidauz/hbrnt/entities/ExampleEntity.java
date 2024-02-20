package com.davidauz.hbrnt.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Random;

@Entity
@Table
@Getter
public class ExampleEntity {
	static Random rand = new Random();

	@Id // mandatory
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column	public int int_field;
	@Column	public String string_field;
	@Column	public long long_field;

	public ExampleEntity(){
		int_field=rand.nextInt(100);
		string_field=rand.ints(48, 123)
		.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		.limit(20) // length
		.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		.toString();
		long_field=rand.nextLong(100);
	}

	public String toString(){
		return "int=`"+int_field+"`;"
		+" string=`"+string_field+"`;"
		+" long=`"+long_field+"`"
		;
	}
}
