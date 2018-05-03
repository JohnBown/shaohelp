package com.etc.jump.entity;

public class City {

	private int id;
	private String name;
	private int keys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKeys() {
		return keys;
	}
	public void setKeys(int keys) {
		this.keys = keys;
	}

	public City(int id, String name, int keys) {
		this.id = id;
		this.name = name;
		this.keys = keys;
	}

	public City() {
	}
}
