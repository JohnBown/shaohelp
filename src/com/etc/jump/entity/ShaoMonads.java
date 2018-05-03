package com.etc.jump.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class ShaoMonads {

	private int s_id;
	private int s_monads_id;
	private int s_user_id;
	private LocalDateTime s_order_datetime;
	public int getS_monads_id() {
		return s_monads_id;
	}
	public void setS_monads_id(int s_monads_id) {
		this.s_monads_id = s_monads_id;
	}
	public int getS_user_id() {
		return s_user_id;
	}
	public void setS_user_id(int s_user_id) {
		this.s_user_id = s_user_id;
	}
	public LocalDateTime getS_order_datetime() {
		return s_order_datetime;
	}
	public void setS_order_datetime(LocalDateTime s_order_datetime) {
		this.s_order_datetime = s_order_datetime;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	@Override
	public String toString() {
		return "ShaoMonads{" +
				"s_id=" + s_id +
				", s_monads_id=" + s_monads_id +
				", s_user_id=" + s_user_id +
				", s_order_datetime=" + s_order_datetime +
				'}';
	}
}
