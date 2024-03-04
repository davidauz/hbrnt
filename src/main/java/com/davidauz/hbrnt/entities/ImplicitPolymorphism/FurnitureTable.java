package com.davidauz.hbrnt.entities.ImplicitPolymorphism;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureTable extends FurniturePiece {
	public enum TableShapes {
		SQUARE
		,	ROUND
		,	IRREGULAR
		;
	}

	@Column public TableShapes shape;
}
