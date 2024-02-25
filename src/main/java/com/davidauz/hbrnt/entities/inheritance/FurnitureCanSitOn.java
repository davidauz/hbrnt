package com.davidauz.hbrnt.entities.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
