package com.example.tigercard.beans;

import java.sql.Time;

public class CardEntry {

	private String day;
	private Time swipeTime;
	private int fromZone;
	private int toZone;
	private int fareCalc;
	private String info;

	public CardEntry(String day, Time swipeTime, int fromZone, int toZone, int fareCalc, String info) {
		super();
		this.day = day;
		this.swipeTime = swipeTime;
		this.fromZone = fromZone;
		this.toZone = toZone;
		this.fareCalc = fareCalc;
		this.info = info;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Time getSwipeTime() {
		return swipeTime;
	}

	public void setSwipeTime(Time swipeTime) {
		this.swipeTime = swipeTime;
	}

	public int getFromZone() {
		return fromZone;
	}

	public void setFromZone(int fromZone) {
		this.fromZone = fromZone;
	}

	public int getToZone() {
		return toZone;
	}

	public void setToZone(int toZone) {
		this.toZone = toZone;
	}

	public int getFareCalc() {
		return fareCalc;
	}

	public void setFareCalc(int fareCalc) {
		this.fareCalc = fareCalc;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%d\t%d\t%d\t%s", day, swipeTime, fromZone, toZone, fareCalc, info);
	}
}
