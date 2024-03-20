package com.davidauz.hibrntTests.entities.JoinedUnions;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
