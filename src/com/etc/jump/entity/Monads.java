package com.etc.jump.entity;

import java.time.LocalDateTime;

public class Monads {
    private int m_id;
	private int m_uid;
	private int m_goods;
	private String m_goodinfo;
	private LocalDateTime m_order_datetime;
	private LocalDateTime m_time_at;
	private String m_address_from;
	private String m_address_to;
	private LocalDateTime m_time_from;
	private LocalDateTime m_time_to;
	private LocalDateTime m_failure_time;
	private int m_status;

	public Monads() {
	}

	public Monads(int m_id,
				  int m_uid,
				  int m_goods,
				  String m_goodinfo,
				  LocalDateTime m_order_datetime,
				  LocalDateTime m_time_at,
				  String m_address_from,
				  String m_address_to,
				  LocalDateTime m_time_from,
				  LocalDateTime m_time_to,
				  LocalDateTime m_failure_time,
				  int m_status) {
		this.m_id = m_id;
		this.m_uid = m_uid;
		this.m_goods = m_goods;
		this.m_goodinfo = m_goodinfo;
		this.m_order_datetime = m_order_datetime;
		this.m_time_at = m_time_at;
		this.m_address_from = m_address_from;
		this.m_address_to = m_address_to;
		this.m_time_from = m_time_from;
		this.m_time_to = m_time_to;
		this.m_failure_time = m_failure_time;
		this.m_status = m_status;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public int getM_uid() {
		return m_uid;
	}

	public void setM_uid(int m_uid) {
		this.m_uid = m_uid;
	}

	public int getM_goods() {
		return m_goods;
	}

	public void setM_goods(int m_goods) {
		this.m_goods = m_goods;
	}

	public String getM_goodinfo() {
		return m_goodinfo;
	}

	public void setM_goodinfo(String m_goodinfo) {
		this.m_goodinfo = m_goodinfo;
	}

	public LocalDateTime getM_order_datetime() {
		return m_order_datetime;
	}

	public void setM_order_datetime(LocalDateTime m_order_datetime) {
		this.m_order_datetime = m_order_datetime;
	}

	public LocalDateTime getM_time_at() {
		return m_time_at;
	}

	public void setM_time_at(LocalDateTime m_time_at) {
		this.m_time_at = m_time_at;
	}

	public String getM_address_from() {
		return m_address_from;
	}

	public void setM_address_from(String m_address_from) {
		this.m_address_from = m_address_from;
	}

	public String getM_address_to() {
		return m_address_to;
	}

	public void setM_address_to(String m_address_to) {
		this.m_address_to = m_address_to;
	}

	public LocalDateTime getM_time_from() {
		return m_time_from;
	}

	public void setM_time_from(LocalDateTime m_time_from) {
		this.m_time_from = m_time_from;
	}

	public LocalDateTime getM_time_to() {
		return m_time_to;
	}

	public void setM_time_to(LocalDateTime m_time_to) {
		this.m_time_to = m_time_to;
	}

	public LocalDateTime getM_failure_time() {
		return m_failure_time;
	}

	public void setM_failure_time(LocalDateTime m_failure_time) {
		this.m_failure_time = m_failure_time;
	}

	public int getM_status() {
		return m_status;
	}

	public void setM_status(int m_status) {
		this.m_status = m_status;
	}

	@Override
	public String toString() {
		return "Monads{" +
				"m_id=" + m_id +
				", m_uid=" + m_uid +
				", m_goods=" + m_goods +
				", m_goodinfo='" + m_goodinfo + '\'' +
				", m_order_datetime=" + m_order_datetime +
				", m_time_at=" + m_time_at +
				", m_address_from='" + m_address_from + '\'' +
				", m_address_to='" + m_address_to + '\'' +
				", m_time_from=" + m_time_from +
				", m_time_to=" + m_time_to +
				", m_failure_time=" + m_failure_time +
				", m_status=" + m_status +
				'}';
	}


}
