package com.kh.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

// Crew vo
public class Crew implements Serializable {
	private static final long serialVersionUID = -2973040292986558673L;

	private String crewName; // 크루명
//	private User crewMaster; // 크루장 
	private String crewMasterName; // 크루장 일단 String으로처리 (크루장은 User이름)
	private ArrayList<User> userList; // 크루원 리스트
	private boolean isAccept; // 크루 만들기 승인 여부 관리자가 승인 기본값 false

	public Crew() {
		userList = new ArrayList<User>();
	}

	public Crew(String crewName, String crewMasterName) {
		this();
		this.crewName = crewName;
		this.crewMasterName = crewMasterName;
//		this.isAccept = false; // 기본값은 승인이 안된 상태 
	}

	public String getCrewName() {
		return crewName;
	}

	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}

	public String getCrewMasterName() {
		return crewMasterName;
	}

	public void setCrewMaster(String crewMasterName) {
		this.crewMasterName = crewMasterName;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Crew [crewName=" + crewName + ", crewMasterName=" + crewMasterName + ", userList=" + userList + ", isAccept="
				+ isAccept + "]";
	}

}