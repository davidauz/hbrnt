package com.davidauz.hibrntTests.entities.ImplicitPolymorphism;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureSofa extends FurnitureCanSitOn{

	public enum SofaMaterials
	{	LEATHER
	,	FABRIC
	,	PLASTIC
	;
	}


	@Column private SofaMaterials material;
}
