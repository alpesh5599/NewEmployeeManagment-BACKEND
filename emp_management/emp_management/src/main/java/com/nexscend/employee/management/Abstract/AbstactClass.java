package com.nexscend.employee.management.Abstract;

import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "abstract")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstactClass {
	
	@EmbeddedId
	private Integer id;
	// Record Created Date
	private Date createdDate;
	
	// Record Updated Date
	private Date modifiedDate;
	
	private Integer createBy;
	private Integer modifiedBy;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "AbstactClass [r_id=" + id + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", createBy=" + createBy + ", modifiedBy=" + modifiedBy + "]";
	}
}
