package com.davidauz.hibrntTests.entities.TablePerClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("MA")
public class Maple extends Tree {
	@Column public int syrup_liters;
}
