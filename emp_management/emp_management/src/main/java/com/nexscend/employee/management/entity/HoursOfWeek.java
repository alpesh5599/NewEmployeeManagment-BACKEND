package com.nexscend.employee.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hours")
public class HoursOfWeek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer monHours;
	private Integer tueHours;
	private Integer wedHours;
	private Integer thuHours;
	private Integer friHours;
	private Integer satHours;
	private Integer sunHours;

	@ManyToOne
	@JoinColumn(name = "sheet_id")
	private TimesheetDashboard sheetId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public TimesheetDashboard getSheetId() {
//		return sheetId;
//	}
//
//	public void setSheetId(TimesheetDashboard sheetId) {
//		this.sheetId = sheetId;
//	}

	public Integer getMonHours() {
		return monHours;
	}

	public void setMonHours(Integer monHours) {
		this.monHours = monHours;
	}

	public Integer getTueHours() {
		return tueHours;
	}

	public void setTueHours(Integer tueHours) {
		this.tueHours = tueHours;
	}

	public Integer getWedHours() {
		return wedHours;
	}

	public void setWedHours(Integer wedHours) {
		this.wedHours = wedHours;
	}

	public Integer getThuHours() {
		return thuHours;
	}

	public void setThuHours(Integer thuHours) {
		this.thuHours = thuHours;
	}

	public Integer getFriHours() {
		return friHours;
	}

	public void setFriHours(Integer friHours) {
		this.friHours = friHours;
	}

	public Integer getSatHours() {
		return satHours;
	}

	public void setSatHours(Integer satHours) {
		this.satHours = satHours;
	}

	public Integer getSunHours() {
		return sunHours;
	}

	public void setSunHours(Integer sunHours) {
		this.sunHours = sunHours;
	}

	@Override
	public String toString() {
		return "HoursOfWeek [id=" + id + ", monHours=" + monHours + ", tueHours=" + tueHours + ", wedHours=" + wedHours
				+ ", thuHours=" + thuHours + ", friHours=" + friHours + ", satHours=" + satHours + ", sunHours="
				+ sunHours + "]";
	}

}