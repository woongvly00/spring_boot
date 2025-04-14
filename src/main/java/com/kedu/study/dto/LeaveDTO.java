package com.kedu.study.dto;

// 휴가 (만들어야함.)
public class LeaveDTO {
	private String empName;   // 직원 이름
    private String date;      // 휴가 날짜
    private String type;      // 휴가 종류 (연차, 공가 등)
    public LeaveDTO() {}
	public LeaveDTO(String empName, String date, String type) {
		super();
		this.empName = empName;
		this.date = date;
		this.type = type;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
