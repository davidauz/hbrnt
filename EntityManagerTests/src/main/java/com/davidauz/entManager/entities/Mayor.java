package com.davidauz.entManager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Mayor {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column public String firstName;
	@Column public String familyName;
	@Column public int bornYear;

	@OneToOne
	@JoinColumn(name= "city_id")
	private City assignedCity;

	public String toString(){
		return "ID="+id+": "+firstName+" "+familyName+" ("+ bornYear +")";
	}
}
