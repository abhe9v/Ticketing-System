package com.example.tigercard.beans;

import java.sql.Time;

public class PeakHourSetting {
	private String day;
	private Time fromTime;
	private Time toTime;

	public PeakHourSetting(String day, Time fromTime, Time toTime) {
		super();
		this.day = day;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	public boolean isPeakHourEntry(CardEntry entry) {

		if (day.equals(entry.getDay())) {
			if ((entry.getSwipeTime().after(fromTime) && entry.getSwipeTime().before(toTime))
					|| entry.getSwipeTime().equals(fromTime) || entry.getSwipeTime().equals(toTime)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s", day, fromTime, toTime);
	}

}
