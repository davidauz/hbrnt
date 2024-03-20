package com.davidauz.hibrntTests.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HB")
public class Herb extends Plant {
	@Column
	public HerbType herb_type;
}
