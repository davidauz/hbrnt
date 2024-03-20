package com.davidauz.sprngBootTests.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feline extends Mammal {
	public Feline(){environ=MammalEnvironment.LAND;}
	public boolean is_cuddly;
	public String get_cuddleness(){
		if(is_cuddly)
			return "cuddly";
		else
			return "NOT cuddly";
	}
}
