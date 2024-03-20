package com.davidauz.hibrntTests.entities.JoinedUnions;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dolphin extends Cetacean{
	public Dolphin(){teeth=Teeth.REAL_TEETH;}
	public int fish_eaten_in_a_day;
	public String toString(){
		return "I am a nice dolphin, I have "+getTeeth()+" and I eat "+fish_eaten_in_a_day+" kg of fish each day.";
	}
}
