package com.davidauz.hbrnt.entities.JoinedUnions;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feline extends Mammal {
	public Feline(){environ=MammalEnvironment.LAND;}
	public boolean is_cuddly;
}
