package com.davidauz.hbrnt.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SH")
public class Shrub extends Plant{
	@Column public boolean has_thorns;
}
