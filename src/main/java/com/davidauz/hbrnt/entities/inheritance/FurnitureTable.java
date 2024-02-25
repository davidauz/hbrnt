package com.davidauz.hbrnt.entities.inheritance;

import com.davidauz.hbrnt.enums.TableShapes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class FurnitureTable extends FurniturePiece {
	@Column public TableShapes shape;
}
