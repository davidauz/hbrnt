package com.davidauz.hbrnt.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("OR")
public class Oregano extends Herb{
	@Column
	public OreganoType oreganoType;
	public enum OreganoType{
		Italian, Turkish, Mexican, Syrian, Golden, Hot_Spicy, Cuban, Greek;
	}
	Oregano(){
		herb_type=HerbType.Culinary;
	}
}
