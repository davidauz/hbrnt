package com.davidauz.hbrnt.entities.JoinedUnions;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lion extends Feline{
	public Lion(){is_cuddly=false;}
	private int pride_size;
}
