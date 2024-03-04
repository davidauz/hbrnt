package com.davidauz.hbrnt.entities.JoinedUnions;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cat extends Feline{
	public enum catVariety
	{	SIAMESE
	,	ANGORA
	,	MANX
	}
	public Cat(){is_cuddly=true;}
	private catVariety variety;
	private int tail_length;
}
