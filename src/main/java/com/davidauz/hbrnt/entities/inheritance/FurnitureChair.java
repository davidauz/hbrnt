package com.davidauz.hbrnt.entities.inheritance;

import com.davidauz.hbrnt.enums.ChairMaterials;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureChair extends FurnitureCanSitOn{
	{ accomodating_people=1; } // instance initializer block
	@Column private ChairMaterials material;
}
