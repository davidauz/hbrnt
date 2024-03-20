package com.davidauz.hibrntTests.entities.ImplicitPolymorphism;

import jakarta.persistence.*;
import lombok.Getter;

//@Entity // Every persistent entity class must have at least the @Entity annotation.
// Hibernate maps this class to a table called FurniturePiece.
// Optional argument for passing table name.
// Anyway it is not necessary here since this is annotated with @MappedSuperclass

//@Table also non required because @MappedSuperclass

@Getter
@MappedSuperclass // this will make Hibernate create one db table for each
// derived class ( Implicit Polymorphism )
public class FurniturePiece {
	@Id // Every persistent entity class must have an identifier attribute annotated with @Id.
// Hibernate maps this attribute to a column named ID.
	@GeneratedValue // for automatic generation of IDs.
//strategy=AUTO: Hibernate picks an appropriate strategy,
//according to the SQL dialect of the configured database.
//This is equivalent to @GeneratedValue() without any settings.
//SEQUENCE: Hibernate expects (and creates, optionally) a sequence named HIBERNATE_SEQUENCE
//in the database.
//The sequence will be called separately before every INSERT, producing sequential numeric values.
//IDENTITY: Hibernate expects (and creates in table DDL) a special auto-incremented primary key
//column that automatically generates a numeric value on INSERT, in the database.
//TABLE: Hibernate will use an extra table in your database schema that holds the next numeric
//primary key value, one row for each entity class.
//This table will be read and updated accordingly, before INSERTs.
//The default table name is HIBERNATE_SEQUENCES with columns SEQUENCE_NAME and SEQUENCE_NEXT_HI_VALUE.
	@Column private Long Id;


	@Column private String name;
	@Column private int legs;
}
