package com.davidauz.hibrntTests.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@OneToOne
	(	mappedBy = "assignedCity"
	,	cascade = CascadeType.ALL
	,	orphanRemoval = true
	)
	private Mayor cityMayor;

	@Column private String name;
	@Column private int zipcode;
	@Column private String motto;

	public String toString(){
		return "City:"+name+", id="+id+", zip="+zipcode+", motto '"+motto+"', Mayor "+ (null==cityMayor?"none!":cityMayor.toString());
	};
}
