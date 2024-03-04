package com.davidauz.hbrnt.entities.JoinedUnions;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Mammal {
	public enum MammalEnvironment
	{	WATER
	,	LAND
	;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long Id;

	@Column public MammalEnvironment environ;
}
