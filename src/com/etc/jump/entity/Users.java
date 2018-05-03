package com.etc.jump.entity;

import java.sql.Blob;

public class Users {

	private int u_id;
	private String u_name;
	private String u_pwd;
	private String u_school=null;
	private Blob u_icon=null;
	private int u_city;
	private String u_address=null;
	private String u_phone;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_school() {
		return u_school;
	}
	public void setU_school(String u_school) {
		this.u_school = u_school;
	}
	public Blob isU_icon() {
		return u_icon;
	}
	public void setU_icon(Blob u_icon) {
		this.u_icon = u_icon;
	}
	public int getU_city() {
		return u_city;
	}
	public void setU_city(int u_city) {
		this.u_city = u_city;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		this.u_address = u_address;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	@Override
	public String toString() {
		return "Users{" +
				"u_id=" + u_id +
				", u_name='" + u_name + '\'' +
				", u_pwd='" + u_pwd + '\'' +
				", u_school='" + u_school + '\'' +
				", u_icon=" + u_icon +
				", u_city=" + u_city +
				", u_address='" + u_address + '\'' +
				", u_phone='" + u_phone + '\'' +
				'}';
	}

	public Users() {
	}

	public Users(int u_id, String u_name, String u_pwd, String u_school, Blob u_icon, int u_city, String u_address, String u_phone) {
		this.u_id = u_id;
		this.u_name = u_name;
		this.u_pwd = u_pwd;
		this.u_school = u_school;
		this.u_icon = u_icon;
		this.u_city = u_city;
		this.u_address = u_address;
		this.u_phone = u_phone;
	}
}
