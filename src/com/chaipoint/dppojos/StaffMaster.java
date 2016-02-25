package com.chaipoint.dppojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STAFF_MASTER")
public class StaffMaster {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE")
	private String staffCode;

	@Column(name = "NAME")
	private String staffName;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PHONE_1")
	private String primaryPhone;

	@Column(name = "PHONE_2")
	private String secondaryPhone;

	@Column(name = "SOURCING_CHANNEL")
	private int sourcingChannel;

	@Column(name = "TITLE_ID")
	private int titleId;

	@Column(name = "LOCATION_ID")
	private int locationId;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "DOJ")
	private Date doj;

	@Column(name = "DOR")
	private Date dor;

	@Column(name = "PHOTO")
	private String photo;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "GRADE")
	private boolean grade;

	@Column(name = "SOURCING_CHANNEL_NAME")
	private String sourcingChannelName;

	@Column(name = "SCOPE")
	private int scope;

	@Column(name = "INTERVIEWER_COMMENT")
	private String interviewerComment;

	@Column(name = "LAST_LOGIN")
	private Date lastLogin;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDTAED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private String updatedDate;

	@Column(name = "IS_LOGIN")
	private boolean isLogin;

	@Column(name = "IS_PHONE")
	private boolean isPhone;

	@Column(name = "RD_PHONE")
	private int rdPhone;

	@Column(name = "IS_UNIFORM")
	private boolean isUniform;

	@Column(name = "RD_UNIFORM")
	private int rdUniform;

	@Column(name = "IS_QUARTERS")
	private boolean isQuarters;

	@Column(name = "UNIFORM_ID")
	private int uniformId;

	@Column(name = "QUARTERS_RENT")
	private int quartersRent;

	@Column(name = "DA")
	private int da;

	@Column(name = "HRA")
	private int hra;

	@Column(name = "FOOD_ALLOWANCE")
	private int foodAllowance;

	@Column(name = "SPECIAL_ALLOWANCE")
	private int specialAllowance;

	@Column(name = "TRAVEL_CONVEYANCE")
	private int travelConveyance;

	@Column(name = "PF_EMPLOYERS_CONTRIBUTION")
	private int pfEmployersContribution;

	@Column(name = "ESI_EMPLOYERS_CONTRIBUTION_DEDUCTION")
	private int esiEmployersContributionDeduction;

	@Column(name = "PF_EMPLOYEE_CONTRIBUTION")
	private String pfEmployeeContribution;

	@Column(name = "ESI_EMPLOYEE_CONTRIBUTION")
	private String esiEmployeeContribution;

	@Column(name = "PT")
	private String pt;

	@Column(name = "LAST_PASSWORD_CHANGE")
	private Date lastPasswordChange;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public int getSourcingChannel() {
		return sourcingChannel;
	}

	public void setSourcingChannel(int sourcingChannel) {
		this.sourcingChannel = sourcingChannel;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDor() {
		return dor;
	}

	public void setDor(Date dor) {
		this.dor = dor;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isGrade() {
		return grade;
	}

	public void setGrade(boolean grade) {
		this.grade = grade;
	}

	public String getSourcingChannelName() {
		return sourcingChannelName;
	}

	public void setSourcingChannelName(String sourcingChannelName) {
		this.sourcingChannelName = sourcingChannelName;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public String getInterviewerComment() {
		return interviewerComment;
	}

	public void setInterviewerComment(String interviewerComment) {
		this.interviewerComment = interviewerComment;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public boolean isPhone() {
		return isPhone;
	}

	public void setPhone(boolean isPhone) {
		this.isPhone = isPhone;
	}

	public int getRdPhone() {
		return rdPhone;
	}

	public void setRdPhone(int rdPhone) {
		this.rdPhone = rdPhone;
	}

	public boolean isUniform() {
		return isUniform;
	}

	public void setUniform(boolean isUniform) {
		this.isUniform = isUniform;
	}

	public int getRdUniform() {
		return rdUniform;
	}

	public void setRdUniform(int rdUniform) {
		this.rdUniform = rdUniform;
	}

	public boolean isQuarters() {
		return isQuarters;
	}

	public void setQuarters(boolean isQuarters) {
		this.isQuarters = isQuarters;
	}

	public int getUniformId() {
		return uniformId;
	}

	public void setUniformId(int uniformId) {
		this.uniformId = uniformId;
	}

	public int getQuartersRent() {
		return quartersRent;
	}

	public void setQuartersRent(int quartersRent) {
		this.quartersRent = quartersRent;
	}

	public int getDa() {
		return da;
	}

	public void setDa(int da) {
		this.da = da;
	}

	public int getHra() {
		return hra;
	}

	public void setHra(int hra) {
		this.hra = hra;
	}

	public int getFoodAllowance() {
		return foodAllowance;
	}

	public void setFoodAllowance(int foodAllowance) {
		this.foodAllowance = foodAllowance;
	}

	public int getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(int specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public int getTravelConveyance() {
		return travelConveyance;
	}

	public void setTravelConveyance(int travelConveyance) {
		this.travelConveyance = travelConveyance;
	}

	public int getPfEmployersContribution() {
		return pfEmployersContribution;
	}

	public void setPfEmployersContribution(int pfEmployersContribution) {
		this.pfEmployersContribution = pfEmployersContribution;
	}

	public int getEsiEmployersContributionDeduction() {
		return esiEmployersContributionDeduction;
	}

	public void setEsiEmployersContributionDeduction(int esiEmployersContributionDeduction) {
		this.esiEmployersContributionDeduction = esiEmployersContributionDeduction;
	}

	public String getPfEmployeeContribution() {
		return pfEmployeeContribution;
	}

	public void setPfEmployeeContribution(String pfEmployeeContribution) {
		this.pfEmployeeContribution = pfEmployeeContribution;
	}

	public String getEsiEmployeeContribution() {
		return esiEmployeeContribution;
	}

	public void setEsiEmployeeContribution(String esiEmployeeContribution) {
		this.esiEmployeeContribution = esiEmployeeContribution;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

}
