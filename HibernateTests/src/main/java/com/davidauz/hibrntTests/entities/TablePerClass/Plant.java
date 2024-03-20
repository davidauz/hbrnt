package com.davidauz.hibrntTests.entities.TablePerClass;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PLANT_TYPE")
public abstract class Plant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long Id;

	@Column
	public int bornYear;
}
