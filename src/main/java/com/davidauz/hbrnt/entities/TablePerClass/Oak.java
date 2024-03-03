package com.davidauz.hbrnt.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("OK")
public class Oak extends Tree {
	@Column
	public int n_acorns;
}
