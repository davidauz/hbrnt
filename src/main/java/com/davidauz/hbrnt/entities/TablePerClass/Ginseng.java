package com.davidauz.hbrnt.entities.TablePerClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("GS")
public class Ginseng extends Herb{
	public enum GinsengOrigin{
		KOREAN
		,CHINESE
		,HIMALAYAN
		;
	}

	@Column
	private GinsengOrigin ginseng_origin;
	public Ginseng(){
		herb_type=HerbType.Medicinal;
	}
}
