package com.davidauz.hibrntTests.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TR")
public class Tree extends Plant {
	@Column public double trunk_height;
}
