package com.davidauz.hibrntTests.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("RS")
public class Rose extends Shrub{


	public enum RoseColor {
		RED
		,WHITE
		,BLUE
		,BLACK
		;
	}
	@Column public RoseColor color;

	public Rose(){
		has_thorns=false;
	}
}
