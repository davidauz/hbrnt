package com.davidauz.sprngBootTests.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cetacean extends Mammal {
	public enum Teeth
	{	REAL_TEETH
	,	BALEEN
	}
	public Cetacean(){environ=MammalEnvironment.WATER;}
	public Teeth teeth;
}
