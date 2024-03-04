package com.davidauz.hbrnt.entities.ImplicitPolymorphism;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureChair extends FurnitureCanSitOn{
	public enum ChairMaterials
	{	WOOD
	,	PLASTIC
	,	METAL
	;
	}

	{ accomodating_people=1; } // instance initializer block
	@Column private ChairMaterials material;
}
