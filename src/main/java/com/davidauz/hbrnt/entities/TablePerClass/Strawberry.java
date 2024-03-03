package com.davidauz.hbrnt.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("SB")
public class Strawberry extends Shrub{
	@Column
	public int n_of_strawberries;
}
