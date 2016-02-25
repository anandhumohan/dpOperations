package com.chaipoint.dppojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_VS_LOGIN")
public class RoleVsLogin {

	@Id
	private int id;

	@Column(name = "NINJA")
	private String ninja;

	@Column(name = "DP")
	private String dp;

	@Column(name = "SUPERVISOR")
	private String superVisor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNinja() {
		return ninja;
	}

	public void setNinja(String ninja) {
		this.ninja = ninja;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

	public String getSuperVisor() {
		return superVisor;
	}

	public void setSuperVisor(String superVisor) {
		this.superVisor = superVisor;
	}

}
