package com.chaipoint.dppojos;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cp_order_cancel_reasons")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CancelReasons {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "reason")
	private String reason;

	@Column(name = "active")
	private char active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

}
