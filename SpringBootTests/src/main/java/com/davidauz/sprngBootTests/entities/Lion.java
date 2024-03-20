package com.davidauz.sprngBootTests.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lion extends Feline {
	public Lion(){is_cuddly=false;}
	private int pride_size;
}
