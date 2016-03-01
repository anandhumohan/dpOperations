package com.chaipoint.helperclasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION_MASTER")
public class RegionId {
	@Column(name = "name")
	private String name;

	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
