package com.davidauz.hibrntTests.entities.ImplicitPolymorphism;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class FurnitureCanSitOn extends FurniturePiece{
	@Column protected int comfy_grade = 0;
	@Column protected int accomodating_people = 0;
}
