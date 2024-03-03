package com.davidauz.hbrnt.entities.ImplicitPolymorphism;

import com.davidauz.hbrnt.enums.SofaMaterials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureSofa extends FurnitureCanSitOn{
	@Column private SofaMaterials material;
}
