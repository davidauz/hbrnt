package com.davidauz.sprngBootTests.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Whale extends Cetacean {
	public Whale(){teeth=Teeth.BALEEN;}
	public int krill_eaten_in_a_day;
}
